package nl.ordina.recruitmentexperience.core.video;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.core.DocumentService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class VideoRenderingService {


    private static final FrameData[] frameData;

    private final DocumentService documentService;

    private static final int cores;

    static {
        cores = Runtime.getRuntime().availableProcessors();

        try {
            final File frameDataFile = ResourceUtils.getFile("classpath:framedata.json");
            final String frameDataJson;
            frameDataJson = new String(Files.readAllBytes(frameDataFile.toPath()));
            final ObjectMapper om = new ObjectMapper();
            frameData = om.readValue(frameDataJson, FrameData[].class);
        } catch (IOException e) {
            throw new IllegalStateException("Something went wrong parsing the frame json", e);
        }
    }

    public void renderVideo(final String cvId) {
        System.out.println("Start rendering");
        final ExecutorService executorService = Executors.newFixedThreadPool(cores < 2 ? 1 : cores - 1);

        try {
            final String cvImagePath = renderCvImageForApplication(cvId);


            for(int i = 0; i < frameData.length; i++) {
                final int frameNr = i + 1;
                executorService.execute(() -> {
                    try {
                        renderCvImageIntoFrame(cvId, cvImagePath, frameNr);
                    } catch (IOException e) {
                        throw new IllegalStateException("Processing of frames failed", e);
                    }
                });
            }
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.HOURS);

            System.out.println("frames processed");

            renderFramesIntoSingleVideo(cvId);
            concatenateVideos(cvId);
            System.out.println(String.format("Rendering video for %s done", cvId));
            deleteProcessFiles(cvId);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void deleteProcessFiles(final String cvId) {
        final File processedFolder = new File("processed");
        final File[] files = processedFolder.listFiles((dir, name) -> name.matches(String.format("%s\\*", cvId)));
        for(final File file : files) {
            file.delete();
        }
    }

    private void concatenateVideos(final String cvId) {
        final ProcessBuilder concatenateVideosProcess = new ProcessBuilder("ffmpeg", "-y", "-i", "vid.mp4", "-i", String.format("processed/%srendered.mp4", cvId), "-filter_complex", "[0:v] [1:v] concat=n=2:v=1:a=0 [v]", "-map", "[v]", String.format("videos/%s.mp4", cvId));

        try {
            concatenateVideosProcess.start().waitFor();
        } catch (InterruptedException | IOException e) {
            throw new IllegalStateException("Concatenation of videos failed", e);
        }
    }

    private void renderFramesIntoSingleVideo(final String cvId) {
        final String frameName = String.format("processed/%sframe", cvId) +
                "%2d.png";
        final ProcessBuilder renderFramesIntoSingleVideoProcess = new ProcessBuilder("ffmpeg", "-y", "-r", "24", "-start_number", "1", "-i", frameName, "-c:v", "libx264", "-vf", "fps=25,format=yuv420p", String.format("processed/%srendered.mp4", cvId));

        try {
            renderFramesIntoSingleVideoProcess.start().waitFor();
        } catch (InterruptedException | IOException e) {
            throw new IllegalStateException("Rendering video from frames failed", e);
        }
    }

    private void renderCvImageIntoFrame(final String cvId, final String cvImagePath, final int frameNr) throws IOException {
        final String frameNrWithLeadingZero = frameNr < 10 ? "0" + frameNr : Integer.toString(frameNr);
        final File frameImageFile = ResourceUtils.getFile(String.format("classpath:frames/frame%s.jpg", frameNrWithLeadingZero));
        final File cvImageFile = new File(cvImagePath);
        final BufferedImage bufferedCvImage = ImageIO.read(cvImageFile);

        final String coordinates = calculateCoordinatesFromFrameDataAndImage(bufferedCvImage, frameData[frameNr - 1]);

        final String processedCvImagePath = String.format("processed/%sprocessedCv%s.png", cvId, frameNrWithLeadingZero);
        final ProcessBuilder translateCvToCoordinatesProcess = new ProcessBuilder("convert", cvImagePath, "-virtual-pixel", "transparent", "-background", "none", "-distort", "Perspective", coordinates, "-channel", "a", "-evaluate", "multiply", "0.5", "+channel", "-brightness-contrast", "0x10", "-fill", "gray", "-colorize", "10%", processedCvImagePath);
        final ProcessBuilder renderProcessedCvIntoFrameProcess = new ProcessBuilder("convert", frameImageFile.getPath(), processedCvImagePath, "-composite", String.format("processed/%sframe%s.png", cvId, frameNrWithLeadingZero));

        try {
            translateCvToCoordinatesProcess.start().waitFor();
            renderProcessedCvIntoFrameProcess.start().waitFor();
        } catch (InterruptedException e) {
            throw new IllegalStateException("rendering of CV into Frame failed", e);
        }
    }

    private String calculateCoordinatesFromFrameDataAndImage(final BufferedImage bim, final FrameData current) {
        final FrameCoord tl = current.getTopLeft();
        final FrameCoord tr = current.getTopRight();
        final FrameCoord bl = current.getBottomLeft();
        final FrameCoord br = current.getBottomRight();

        int w = bim.getWidth();
        int h = bim.getHeight();
        final StringBuilder b = new StringBuilder();
        b.append("0,0");
        b.append(" ");
        b.append(tl.toString());
        b.append(" ");
        b.append(String.format("%d,0", w));
        b.append(" ");
        b.append(tr.toString());
        b.append(" ");
        b.append(String.format("0,%d", h));
        b.append(" ");
        b.append(bl.toString());
        b.append(" ");
        b.append(String.format("%d,%d", w, h));
        b.append(" ");
        b.append(br.toString());

        return b.toString();
    }

    private String renderCvImageForApplication(final String cvId) throws IOException {
        final UUID documentId = UUID.fromString(cvId);
        final Resource cvResource = documentService.getDocument(documentId);
        final File cvFile = cvResource.getFile();

        final PDDocument cvPDDoc = PDDocument.load(cvFile);
        final PDFRenderer pdfRenderer = new PDFRenderer(cvPDDoc);

        final BufferedImage cv = pdfRenderer.renderImageWithDPI(0, 300);

        cvPDDoc.close();
        final String cvImagePath = String.format("processed/%scv.png", documentId.toString());

        ImageIOUtil.writeImage(cv, cvImagePath, 300);

        final ProcessBuilder processBuilder = new ProcessBuilder("convert", cvImagePath, "-channel", "A", "-evaluate", "Multiply", "0.10", "+channel", cvImagePath);

        try {
            processBuilder.start().waitFor();
        } catch (InterruptedException e) {
            throw new IllegalStateException("Rendering CV to image failed", e);
        }

        return cvImagePath;
    }
}

package nl.ordina.recruitmentexperience.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PdfToImage implements CommandLineRunner {

    private FrameData[] frameDatas;

    @Override
    public void run(String... strings) {
        try {
            frameDatas = jsonToFrameData();
            BufferedImage bufferedImage = renderPdfToImage();
            int cores = Runtime.getRuntime().availableProcessors();
            ExecutorService es = Executors.newFixedThreadPool(cores < 2 ? 1 : cores -1);
            for(int i = 0; i < 53; i++) {
                int finalI = i;
                es.execute(() -> {
                    try {
                        System.out.println(String.format("Starting frame %d", finalI+1));
                        renderImageIntoImage(bufferedImage, finalI + 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
            es.shutdown();
            es.awaitTermination(1, TimeUnit.HOURS);
            concatVideos();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private FrameData[] jsonToFrameData() throws IOException {
        File file = ResourceUtils.getFile("classpath:frames/framedata.json");
        String json = new String(Files.readAllBytes(file.toPath()));

        ObjectMapper mapper = new ObjectMapper();
        FrameData[] frameDatas = mapper.readValue(json, FrameData[].class);
        return frameDatas;
    }

    private BufferedImage renderPdfToImage() throws IOException {
        File file = ResourceUtils.getFile("classpath:testcv.pdf");

        PDDocument document = PDDocument.load(file);
        PDFRenderer pdfRenderer = new PDFRenderer(document);

        BufferedImage bim = pdfRenderer.renderImageWithDPI(0, 300);
        document.close();

        ImageIOUtil.writeImage(bim, "processed/cv.png", 300);

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("convert", "processed/cv.png", "-channel", "A", "-evaluate", "Multiply", "0.10", "+channel", "processed/cv.png");
            Process start = processBuilder.start();
            start.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return bim;
    }

    private void renderImageIntoImage(BufferedImage bim, final int frameNumber) throws IOException {
        String number = frameNumber < 10 ? "0"+frameNumber : Integer.toString(frameNumber);
        File backgroundFile = ResourceUtils.getFile(String.format("classpath:frames/thumb%s.jpg", number));

        FrameData current = frameDatas[frameNumber-1];
        FrameCoord tl = current.getTopLeft();
        FrameCoord tr = current.getTopRight();
        FrameCoord bl = current.getBottomLeft();
        FrameCoord br = current.getBottomRight();

        int w = bim.getWidth();
        int h = bim.getHeight();
        StringBuilder b = new StringBuilder();
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

        String coords = b.toString();
        ProcessBuilder processBuilder = new ProcessBuilder("convert", "processed/cv.png", "-virtual-pixel", "transparent", "-background", "none", "-distort", "Perspective", coords, "-channel", "a", "-evaluate", "multiply", "0.5", "+channel", "-brightness-contrast", "0x10", "-fill", "gray", "-colorize", "10%", String.format("processed/image_converted%s.png", number));
        ProcessBuilder processBuilder1 = new ProcessBuilder("convert", backgroundFile.getPath(), String.format("processed/image_converted%s.png", number), "-composite", String.format("processed/frame%s.png", number));


        try {
            Process start = processBuilder.start();
            start.waitFor();
            Process start1 = processBuilder1.start();
            start1.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void concatVideos() {
        ProcessBuilder vidFromImages = new ProcessBuilder("ffmpeg", "-y", "-r", "24", "-start_number", "1", "-i", "processed/frame%2d.png", "-c:v", "libx264", "-vf", "fps=25,format=yuv420p", "out.mp4");
        ProcessBuilder concatVid = new ProcessBuilder("ffmpeg", "-y", "-i", "vid.mp4", "-i", "out.mp4", "-filter_complex", "[0:v] [1:v] concat=n=2:v=1:a=0 [v]", "-map", "[v]", "output.mp4");
        try {
            System.out.println("concat videos");

            Process vidFromImagesProcess = vidFromImages.start();

            vidFromImagesProcess.waitFor();

            Process concatProcess = concatVid.start();

            concatProcess.waitFor();
            System.out.println(readOutput(concatProcess.getErrorStream()));
            System.out.println(readOutput(concatProcess.getInputStream()));


            System.out.println("klaar");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private List<String> readOutput(InputStream inputStream) throws IOException {
        try (BufferedReader output = new BufferedReader(new InputStreamReader(inputStream))) {
            return output.lines()
                    .collect(Collectors.toList());
        }
    }
}


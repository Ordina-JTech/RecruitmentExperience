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
            ExecutorService es = Executors.newCachedThreadPool();
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

        ImageIOUtil.writeImage(bim, "processed/cv.jpg", 300);

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
        ProcessBuilder processBuilder = new ProcessBuilder("convert", "processed/cv.jpg", "-virtual-pixel", "transparent", "-background", "none", "-distort", "Perspective", coords, String.format("processed/image_converted%s.png", number));
        ProcessBuilder processBuilder1 = new ProcessBuilder("convert", backgroundFile.getPath(), String.format("processed/image_converted%s.png", number), "-composite", String.format("processed/frame%s.png", number));


        try {
            Process start = processBuilder.start();
            start.waitFor();
            Process start1 = processBuilder1.start();
            start1.waitFor();

//            generateVideoFromFrame(number);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void generateVideoFromFrame(String number) {
        ProcessBuilder createVid = new ProcessBuilder("ffmpeg", "-start_number", "1", "-t", "1", "-i", String.format("processed/frame%s.jpg", number), String.format("processed/vid%s.mp4", number));
        try {

            System.out.println(String.format("Creating vid %s", number));
            Process createProcess = createVid.start();
            createProcess.waitFor();

            System.out.println(readOutput(createProcess.getErrorStream()));
            System.out.println(readOutput(createProcess.getInputStream()));

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void concatVideos() {
        ProcessBuilder concatVid = new ProcessBuilder("ffmpeg", "-f", "concat", "-i", "list.txt", "-auto_convert", "1", "-c", "copy", "final.mp4");
        try {
            System.out.println("concat videos");
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


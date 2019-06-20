package nl.ordina.recruitmentexperience.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.List;
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
            for(int i = 0; i < 53; i++) {
                renderImageIntoImage(bufferedImage, i + 1);
            }
        } catch (IOException e) {
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

        return bim;
    }

    private void renderImageIntoImage(BufferedImage bim, final int frameNumber) throws IOException {
        String number = frameNumber < 10 ? "0"+frameNumber : Integer.toString(frameNumber);
        File backgroundFile = ResourceUtils.getFile(String.format("classpath:frames/thumb%s.jpg", number));
        BufferedImage background = ImageIO.read(backgroundFile);

        FrameData current = frameDatas[frameNumber-1];
        FrameCoord tl = current.getTopLeft();
        FrameCoord tr = current.getTopRight();
        FrameCoord bl = current.getBottomLeft();
        FrameCoord br = current.getBottomRight();

        Graphics2D g = background.createGraphics();

        Polygon polygon = new Polygon();
        polygon.addPoint(tl.getX(), tl.getY());
        polygon.addPoint(tr.getX(), tr.getY());
        polygon.addPoint(br.getX(), br.getY());
        polygon.addPoint(bl.getX(), bl.getY());


        g.clip(polygon);

        AffineTransform affineTransform = new AffineTransform();



        g.draw(affineTransform.createTransformedShape(polygon));

        g.drawImage(bim, affineTransform, null);

        g.dispose();

        ImageIOUtil.writeImage(background, String.format("processed/frame%s.jpg", number), 300);

//        generateVideoFromFrame();
    }

    private void generateVideoFromFrame() {
        ProcessBuilder createVid = new ProcessBuilder("ffmpeg", "-start_number", "1", "-t", "1", "-i", "processed/frame%02d.jpg", "processed.mp4");
        try {
            Process createProcess = createVid.start();

            System.out.println(readOutput(createProcess.getErrorStream()));
            System.out.println(readOutput(createProcess.getInputStream()));

//            concatVideos();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void concatVideos() {
        ProcessBuilder concatVid = new ProcessBuilder("ffmpeg", "-f", "concat", "-i", "list.txt", "-auto_convert", "1", "-c", "copy", "final.mp4");
        try {
            Process concatProcess = concatVid.start();

            System.out.println(readOutput(concatProcess.getErrorStream()));
            System.out.println(readOutput(concatProcess.getInputStream()));


            System.out.println("klaar");
        } catch (IOException e) {
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


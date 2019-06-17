package nl.ordina.recruitmentexperience.app;

import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class PdfToImage implements CommandLineRunner {

    @Override
    public void run(String... strings) {
        try {
            renderPdfToImage();
        } catch (IOException e) {
            //intentionally left empty
        }
    }

    private void renderPdfToImage() throws IOException {
        File file = ResourceUtils.getFile("classpath:testcv.pdf");

        PDDocument document = PDDocument.load(file);
        PDFRenderer pdfRenderer = new PDFRenderer(document);

        BufferedImage bim = pdfRenderer.renderImageWithDPI(0, 300);
        document.close();

        renderImageIntoImage(bim);
    }

    private void renderImageIntoImage(BufferedImage bim) throws IOException {
        File backgroundFile = ResourceUtils.getFile("classpath:background.jpg");
        BufferedImage background = ImageIO.read(backgroundFile);

        Graphics2D g = background.createGraphics();
        g.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        g.setRenderingHints(new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
        g.setRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g.rotate(Math.toRadians(-2.9));
        g.drawImage(bim, 395,
                167, 498, 755, null);
        g.dispose();

        ImageIOUtil.writeImage(background, "final.jpg", 300);
    }
}


package com.zhang.practice.thread.pdf;

import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author : zzh
 * create at:  2022/8/2
 * @description:
 */
public class PdfToJpg {

    public static void pdf2Pic(String pdfPath, String path, String fileName) throws Exception {
        Document document = new Document();
        document.setFile(pdfPath);
        //缩放比例
        float scale = 2.5f;
        //旋转角度
        float rotation = 0f;
        for (int i = 0; i < document.getNumberOfPages(); i++) {
            BufferedImage image = (BufferedImage)
                    document.getPageImage(i, GraphicsRenderingHints.SCREEN, org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
            RenderedImage rendImage = image;
            try {
                File fileDir = new File(path);
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
                String imgName = fileName + i + ".png";
                File file = new File(path + imgName);
                ImageIO.write(rendImage, "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            image.flush();
        }
        document.dispose();
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            long start = System.currentTimeMillis();
            String filePath = "/Users/edison/Desktop/tmp/template.pdf";
            pdf2Pic(filePath, "/Users/edison/Desktop/tmp/", "templatePic");
            System.out.println(System.currentTimeMillis() - start);
            Thread.sleep(500);
        }
    }



}

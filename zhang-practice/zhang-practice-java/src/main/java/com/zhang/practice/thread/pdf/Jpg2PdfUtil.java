package com.zhang.practice.thread.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;

/**
 * @author : zzh
 * create at:  2022/8/2
 * @description:
 */
public class Jpg2PdfUtil {

    /*public static byte[] pdf2Jpg(String sourceFile) throws Exception {
        File file = new File(sourceFile);
        FileInputStream input = new FileInputStream(file);
        byte[] bytes = new byte[input.available()];
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            //A4旋转90度
            Rectangle rotate = PageSize.A4.rotate();
            //边框设置为0
            Document document = new Document(rotate, 0, 0, 0, 0);
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            FontFactoryImp ffi = new FontFactoryImp();
            ffi.registerDirectories();
            document.open();
            for (int i = 0; i < bytes.length; i++) {
                if (i != 0) {
                    document.newPage();
                }
                //图片缩小到A4大小，原始图片生成时，最好为A4的2倍以上，显示时清晰度才高
                Image image = Image.getInstance(bytes[i]);
                image.scaleAbsolute(842, 595);
                document.add(Jpeg.getInstance(image));
            }
            document.close();
            writer.close();
            outputStream.flush();
            return outputStream.toByteArray();
        } finally {
            outputStream.close();
        }
    }*/

    private static void generatePdfFile(String sourceFilePath, String destPath) throws IOException, DocumentException {

        String pdfFileName = destPath + "ttt.pdf";

        Image image = Image.getInstance(sourceFilePath);
//        Document doc = new Document(PageSize.A4);
        Document doc = new Document(null, 0, 0, 0, 0);
//        Document doc = new Document(new RectangleReadOnly(image.getWidth(), image.getHeight()));
        doc.setPageSize(new Rectangle(image.getWidth(), image.getHeight()));
        PdfWriter.getInstance(doc, new FileOutputStream(pdfFileName));
        doc.open();
        doc.newPage();

        float height = image.getHeight();
        float width = image.getWidth();
        //int percent = getPercent(height, width);

        image.setAlignment(Image.MIDDLE);
        //image.scalePercent(percent);
        doc.add(image);
        doc.close();

        //File pdfFile = new File(pdfFileName);
        //return pdfFile;
    }


    public static void main(String[] args) throws Exception {
        //for (int i = 0; i < 10; i++) {
        long start = System.currentTimeMillis();
        String filePath = "/Users/edison/Desktop/tmp/templateOut.png";
        generatePdfFile(filePath, "/Users/edison/Desktop/tmp/");
        System.out.println(System.currentTimeMillis() - start);
        Thread.sleep(500);
        //}
    }


}

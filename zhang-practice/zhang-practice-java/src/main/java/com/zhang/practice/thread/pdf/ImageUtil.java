package com.zhang.practice.thread.pdf;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author : zzh
 * create at:  2022/8/2
 * @description:
 */
public class ImageUtil {

    public void create(String sourceFile, String targetPath){
        try {
            String name = "张小荣";
            // 读取原图片信息
            File srcImgFile = new File(sourceFile);//得到文件
            Image srcImg = ImageIO.read(srcImgFile);//文件转化为图片
            int srcImgWidth = srcImg.getWidth(null);//获取图片的宽
            int srcImgHeight = srcImg.getHeight(null);//获取图片的高
            File file = new File(targetPath);

            System.out.println(srcImgWidth);
            System.out.println(srcImgHeight);

            BufferedImage bi = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = bi.createGraphics();
            g2.setBackground(Color.WHITE);
            g2.clearRect(0, 0, srcImgWidth, srcImgHeight);
            // 这里减去25是为了防止字和图重合
            //g2.drawImage(srcImg, 0, 0, srcImgWidth - 25, srcImgHeight - 25, null);
            g2.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);

            /** 防止生成的文字带有锯齿 * */
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            genName(g2, srcImgWidth, srcImgHeight, name);
            genDate(g2, srcImgWidth, srcImgHeight, "2022年10月");
            genCerDate(g2, srcImgWidth, srcImgHeight, "2022年10月");
            genCerNumber(g2, srcImgWidth, srcImgHeight, "914401202210674091");

            ImageIO.write(bi, "png", file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void genCerNumber(Graphics2D g2, int srcImgWidth, int srcImgHeight, String number) throws IOException, FontFormatException {
        Font font = Font.createFont(Font.TRUETYPE_FONT, new File("/Users/edison/Desktop/tmp/思源黑体/SourceHanSansCN-Regular.otf"))
                .deriveFont(Font.PLAIN, 50.5f);
        g2.setFont(font);
        g2.setPaint(Color.GRAY);
        g2.drawString(number, (float) (srcImgWidth * 0.496), (float) (srcImgHeight * 0.95));
    }

    public void genCerDate(Graphics2D g2, int srcImgWidth, int srcImgHeight, String date) throws IOException, FontFormatException {
        Font font = Font.createFont(Font.TRUETYPE_FONT, new File("/Users/edison/Desktop/tmp/思源黑体/SourceHanSansCN-Regular.otf"))
                .deriveFont(Font.PLAIN, 71f);
        g2.setFont(font);
        new Color(0, 0, 0);

        g2.setPaint(Color.BLACK);
        g2.drawString(date, (float) (srcImgWidth * 0.27), (float) (srcImgHeight * 0.79));
    }

    public void genDate(Graphics2D g2, int srcImgWidth, int srcImgHeight, String date) throws IOException, FontFormatException {
        Font font = Font.createFont(Font.TRUETYPE_FONT, new File("/Users/edison/Desktop/tmp/思源黑体/SourceHanSansCN-Regular.otf"))
                .deriveFont(Font.PLAIN, 87.5f);
        g2.setFont(font);
        g2.setPaint(Color.BLACK);

        /** 在图片上生成文字 * */
        g2.drawString(date, (float) (srcImgWidth * 0.177), (float) (srcImgHeight * 0.533));
    }

    public void genName(Graphics2D g2, int srcImgWidth, int srcImgHeight, String name) throws IOException, FontFormatException {
        Font font = Font.createFont(Font.TRUETYPE_FONT, new File("/Users/edison/Desktop/tmp/思源黑体/SourceHanSansCN-Bold.ttf"))
                .deriveFont(Font.PLAIN, 140f);
        g2.setFont(font);
        g2.setPaint(Color.BLACK);

        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(name, context);
        System.out.println("x2:" + bounds.getWidth());
        System.out.println("y2:" + bounds.getHeight());

        Double x = (srcImgWidth - bounds.getWidth()) / 2;

        //double x = (srcImgWidth - bounds.getWidth()) / 2;
        //double y = (height - bounds.getHeight()) / 2; //Y轴居中
        //double y = (srcImgHeight - bounds.getHeight());
        //double ascent = -bounds.getY();
        //double baseY = y + ascent;


        /** 在图片上生成文字 * */
        g2.drawString(name, x.floatValue(), 1568);
    }



    public static void main(String[] args) {
        //for(int i = 0; i<10; i++) {
            try {
                long start = System.currentTimeMillis();
                ImageUtil img = new ImageUtil();
                img.create("/Users/edison/Desktop/tmp/template.png", "/Users/edison/Desktop/tmp/templateOut.png");
                System.out.println((System.currentTimeMillis() - start));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
       // }

    }
}

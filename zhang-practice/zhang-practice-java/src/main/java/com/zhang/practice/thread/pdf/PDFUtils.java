package com.zhang.practice.thread.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : zzh
 * create at:  2022/8/2
 * @description:
 */
public class PDFUtils {

    /**
     * 模板路径
     */
    private static final String TEMPLATE_PATH = "/Users/edison/Desktop/tmp/template.pdf";

    /**
     * 生成的新文件路径
     */
    private static final String NEW_PDF_PATH = "/Users/edison/Desktop/tmp/templateOut.pdf";

    /**
     * 利用模板生成pdf
     *
     * @param sourceMap 数据源Map
     */
    public static void pdfout(Map<String, Object> sourceMap) {
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            // 设置字体
//            BaseFont bf = BaseFont.createFont("D:\\bb2360\\simsun.ttf" , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//            Font fontChinese = new Font(bf, 3, Font.NORMAL);
            out = new FileOutputStream(NEW_PDF_PATH);
            reader = new PdfReader(TEMPLATE_PATH);
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields fields = stamper.getAcroFields();

            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            BaseFont bf1 = BaseFont.createFont("/Users/edison/Desktop/tmp/思源黑体/SourceHanSansCN-Bold.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            BaseFont bf2 = BaseFont.createFont("/Users/edison/Desktop/tmp/思源黑体/SourceHanSansCN-Regular.ttf", BaseFont.IDENTITY_V, BaseFont.NOT_EMBEDDED);


            fields.addSubstitutionFont(bfChinese);

            //文字类的内容处理
            Map<String, String> dataMap = (Map<String, String>) sourceMap.get("dataMap");

            fields.setFieldProperty("name", "textsize", 33f, null);
            fields.setFieldProperty("name","textfont", bf1,null);
            fields.setField("name", "张晓峰");

            fields.setFieldProperty("date", "textsize", 20.7f, null);
            fields.setFieldProperty("date","textfont", bf2,null);
            fields.setField("date", "2022年10月");

            fields.setFieldProperty("cer_date", "textsize", 17f, null);
            fields.setFieldProperty("date","textfont", bf2,null);
            fields.setField("cer_date", "2022年10月");

            fields.setFieldProperty("cer_number", "textsize", 12f, null);
            fields.setFieldProperty("cer_number","textfont", bf2,null);
            fields.setField("cer_number", "914401202210674091");

            /*for (String key : dataMap.keySet()) {
                String value = dataMap.get(key);
                fields.setFieldProperty(key,"textfont", bfChinese,null);
                if (key.equals("name")) {
                    fields.setFieldProperty(key, "textsize", 33f, null);
                }

                if (key.equals("date")) {
                    fields.setFieldProperty(key,"textsize",20f,null);
                }
                if (key.equals("cer_date")) {
                    fields.setFieldProperty(key,"textsize",20f,null);
                }
                if (key.equals("cer_number")) {
                    fields.setFieldProperty(key,"textsize",15f,null);
                }
                fields.setField(key, value);
            }*/

            // 如果为false，生成的PDF文件可以编辑，如果为true，生成的PDF文件不可以编辑
            stamper.setFormFlattening(true);
            stamper.close();
            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();

        } catch (IOException | DocumentException e) {
            System.out.println(e);
        }

    }

    public static void setFieldAndFont(BaseFont bf, PdfStamper stamp, AcroFields form, String chunkStr, float fontSize, float fontPosition, String property) {
        try {
            Font font = new Font(bf, fontSize, -1, new BaseColor(0, 0, 0));
            List<AcroFields.FieldPosition> list = form.getFieldPositions(property);

            int page = list.get(0).page;
            PdfContentByte pdfContentByte = stamp.getOverContent(page);
            ColumnText columnText = new ColumnText(pdfContentByte);
            Rectangle rectangle = list.get(0).position;
            columnText.setSimpleColumn(rectangle);
            Chunk chunk = null;
            chunk = new Chunk(chunkStr);
            Paragraph paragraph = new Paragraph(fontPosition, chunk);
            columnText.addText(paragraph);
            paragraph.setFont(font);
            columnText.addElement(paragraph);
            columnText.go();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("date", "2022年10月");
        map.put("cer_date", "2022年10月");
        map.put("cer_number", "914401202210674091");
        Map<String, Object> o = new HashMap<>();
        o.put("dataMap", map);
        pdfout(o);
        System.out.println((System.currentTimeMillis() - start));
    }
}

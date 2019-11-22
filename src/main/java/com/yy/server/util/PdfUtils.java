package com.yy.server.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangpeng on 2018/02/01.
 */
public class PdfUtils {
    private static Logger logger = LoggerFactory.getLogger(PdfUtils.class);

    // 利用模板生成pdf  
    public static void pdfout(String templatePath, String newPDFPath, Map<String, Object> o) {
        // 模板路径  E:\work\idea\mmy-customer-server\src\main\resources\template\plan_demo.pdf
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            BaseFont bf = BaseFont.createFont("e://seguisym.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font FontChinese = new Font(bf, 5, Font.NORMAL);
            out = new FileOutputStream(newPDFPath);// 输出流
            reader = new PdfReader(templatePath);// 读取pdf模板  
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
            //文字类的内容处理
            Map<String, String> datemap = (Map<String, String>) o.get("datamap");
            form.addSubstitutionFont(BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED));
            //form.addSubstitutionFont(bf);
            for (String key : datemap.keySet()) {
                String value = datemap.get(key);
                form.setField(key, value);
            }
            //图片类的内容处理
            Map<String, String> imgmap = (Map<String, String>) o.get("imgmap");
            if (imgmap != null && imgmap.size() > 0) {
                for (String key : imgmap.keySet()) {
                    String value = imgmap.get(key);
                    String imgpath = value;
                    int pageNo = form.getFieldPositions(key).get(0).page;
                    Rectangle signRect = form.getFieldPositions(key).get(0).position;
                    float x = signRect.getLeft();
                    float y = signRect.getBottom();
                    //根据路径读取图片
                    Image image = Image.getInstance(imgpath);
                    //获取图片页面
                    PdfContentByte under = stamper.getOverContent(pageNo);
                    //图片大小自适应
                    image.scaleToFit(signRect.getWidth(), signRect.getHeight());
                    //添加图片
                    image.setAbsolutePosition(x, y);
                    under.addImage(image);
                }
            }

            stamper.setFormFlattening(true);// 如果为false，生成的PDF文件可以编辑，如果为true，生成的PDF文件不可以编辑
            stamper.close();
//            out.close();
//            reader.close();
            Document doc = new Document();
            Font font = new Font(bf, 32);
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 3);
            copy.addPage(importPage);
            doc.close();
        } catch (IOException e) {
            System.out.println(e);
        } catch (DocumentException e) {
            System.out.println(e);
        }
    }

    // 利用模板生成pdf
    public static void pdfToResponse(String templatePath, HttpServletResponse response, Map<String, Object> o) {
        // 模板路径  E:\work\idea\mmy-customer-server\src\main\resources\template\plan_demo.pdf
        PdfReader reader;
        //FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            //BaseFont bf = BaseFont.createFont("c://windows//fonts//simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            //Font fontChinese = new Font(bf, 5, Font.NORMAL);
            //out = new FileOutputStream(newPDFPath);// 输出流
            reader = new PdfReader(templatePath);// 读取pdf模板
            //document.add(new Paragraph("☑",font));

            bos = new ByteArrayOutputStream();// 存储每页生成的PDF流
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
            //文字类的内容处理
            Map<String, String> datemap = (Map<String, String>) o.get("datamap");
            form.addSubstitutionFont(BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED));
            //form.addSubstitutionFont(bf);
            for (String key : datemap.keySet()) {
                String value = datemap.get(key);
                form.setField(key, value);
            }
            //图片类的内容处理
            Map<String, String> imgmap = (Map<String, String>) o.get("imgmap");
            if (imgmap != null && imgmap.size() > 0) {
                for (String key : imgmap.keySet()) {
                    String value = imgmap.get(key);
                    String imgpath = value;
                    int pageNo = form.getFieldPositions(key).get(0).page;
                    Rectangle signRect = form.getFieldPositions(key).get(0).position;
                    float x = signRect.getLeft();
                    float y = signRect.getBottom();
                    //根据路径读取图片
                    Image image = Image.getInstance(imgpath);
                    //获取图片页面
                    PdfContentByte under = stamper.getOverContent(pageNo);
                    //图片大小自适应
                    image.scaleToFit(signRect.getWidth(), signRect.getHeight());
                    //添加图片
                    image.setAbsolutePosition(x, y);
                    under.addImage(image);
                }
            }

            stamper.setFormFlattening(true);// 如果为false，生成的PDF文件可以编辑，如果为true，生成的PDF文件不可以编辑
            stamper.close();

		/*	OutputStream fos = new FileOutputStream("D:/contract2.pdf");
			fos.write(ba.toByteArray());*/

            response.setContentType("application/pdf");
            response.setContentLength(bos.size());
            ServletOutputStream servletOut = response.getOutputStream();
//            Document doc = new Document();
//            Font font = new Font(bf, 32);
//            PdfCopy copy = new PdfCopy(doc, servletOut);
//            doc.open();
//            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
//            copy.addPage(importPage);
//            doc.close();
            servletOut.write(bos.toByteArray());
            servletOut.flush();
            servletOut.close();

        } catch (IOException e) {
            logger.error("转换文件异常，e=",e);
            System.out.println(e);
        } catch (DocumentException e) {
            logger.error("转换文件异常，e=",e);
        }

    }

    public static void main(String[] args) {
        String templatePath = "E:/manageplan.pdf";
        // 生成的新文件路径
        String newPDFPath = "E:/manageplan_out55.pdf";
        Map<String, String> map = new HashMap();
        map.put("nowdate", "2018年1月1日");
        map.put("name", "张三");
        map.put("sex", "男");
        map.put("phone", "18700457099");
        map.put("lanmu1", "你的基础项目");
        map.put("lanmu_value1", "皮肤炫白");

        map.put("lanmu2", "针对问题");
        map.put("lanmu_value2", "皮肤不白了");

        map.put("guanlishi", "王五");
        map.put("zishenguanlishi", "张三");
        map.put("remark", "王五......");

//        map.put("creatdate","2018年1月1日");
        Map<String, String> map2 = new HashMap();
        map2.put("img1", "C:/Users/riyun/Desktop/1551603817.jpg");
        map2.put("img2", "http://39.96.178.133:8000/TEMP/PDT/15521212385092437.jpg");
        Map<String, Object> o = new HashMap();
        o.put("datamap", map);
        o.put("imgmap",map2);
        pdfout(templatePath, newPDFPath, o);
    }
}
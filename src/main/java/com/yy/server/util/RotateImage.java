package com.yy.server.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RotateImage {
    private static Logger logger = LoggerFactory.getLogger(RotateImage.class);


    public static BufferedImage Rotate(Image src, int angel) {
        int src_width = src.getWidth(null);
        int src_height = src.getHeight(null);
        // calculate the new image size  
        Rectangle rect_des = CalcRotatedSize(new Rectangle(new Dimension(
                src_width, src_height)), angel);

        BufferedImage res = null;
        res = new BufferedImage(rect_des.width, rect_des.height,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g2 = res.createGraphics();
        //System.out.println(g2);
        // transform  
        g2.translate((rect_des.width - src_width) / 2,
                (rect_des.height - src_height) / 2);
        g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);

        g2.drawImage(src, null, null);
        return res;
    }

    public static Rectangle CalcRotatedSize(Rectangle src, int angel) {
        // if angel is greater than 90 degree, we need to do some conversion  
        if (angel >= 90) {
            if (angel / 90 % 2 == 1) {
                int temp = src.height;
                src.height = src.width;
                src.width = temp;
            }
            angel = angel % 90;
        }

        double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
        double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
        double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
        double angel_dalta_width = Math.atan((double) src.height / src.width);
        double angel_dalta_height = Math.atan((double) src.width / src.height);

        int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha
                - angel_dalta_width));
        int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha
                - angel_dalta_height));
        int des_width = src.width + len_dalta_width * 2;
        int des_height = src.height + len_dalta_height * 2;
        return new Rectangle(new Dimension(des_width, des_height));
    }

    /**
     * 根据上传的图片，判断该图片需要旋转的角度
     *
     * @return
     * @throws IOException
     */
    public static int readPictureDegree(String localPath) throws IOException {
        int angle = 0;
        File jpegFile = new File(localPath);
        //WSEndpointReference.Metadata metadata;

//    	try {
//    		//metadata = JpegMetadataReader.readMetadata(jpegFile);
//    		metadata= ImageMetadataReader.readMetadata(jpegFile);
//    		Directory directory = metadata.getDirectory(ExifDirectory.class);
//			if(directory != null ){
//				int orientation =0;
//				 if (directory.containsTag(ExifIFD0Directory.TAG_ORIENTATION)) {
//					orientation = directory.getInt(ExifDirectory.TAG_ORIENTATION);
//					logger.info("方向==》"+orientation);
//				 }
//				if(6 == orientation ){
//					//6旋转90
//					angle = 90;
//				}else if( 3 == orientation){
//					//3旋转180
//					angle = 180;
//				}else if( 8 == orientation){
//					//8旋转90
//					angle = 270;
//				}
//			}
//			}catch (MetadataException e) {
//				logger.error("Unable to read metadata for image:-->"+e);
//			} catch (ImageProcessingException ex) {
//				logger.error("Unable to read metadata for image:-->"+ex);
//			}
        return angle;
    }

    /**
     * 上传的图片进行旋转
     *
     * @param templocalPath
     * @param imgName
     * @return
     * @throws Exception
     */
    public static String rotateImagePath(String templocalPath, String imgName) throws Exception {
        //long startTime = System.nanoTime();
        String imagePath = "";
        int swidth = 0; // 旋转后的宽度
        int sheight = 0; // 旋转后的高度
        int x; // 原点横坐标
        int y; // 原点纵坐标

        try {
            int degree = readPictureDegree(templocalPath); //根据上传的图片，判断该图片需要旋转的角度
            //int degree= 90; //根据上传的图片，判断该图片需要旋转的角度
            if (degree != 0) {
//            	logger.info("uploadImg4,1 takeTimeMs="
//    					+ (System.nanoTime() - startTime) / (1000 * 1000) + "ms");
                File file = new File(templocalPath);
                if (!file.isFile()) {
                    throw new Exception("ImageDeal>>>" + file + " 不是一个图片文件!");
                }

                BufferedImage bi = ImageIO.read(file); // 读取该图片
                // 处理角度--确定旋转弧度
                degree = degree % 360;
                if (degree < 0)
                    degree = 360 + degree;// 将角度转换到0-360度之间
                double theta = Math.toRadians(degree);// 将角度转为弧度

                // 确定旋转后的宽和高
                if (degree == 180 || degree == 0 || degree == 360) {
                    swidth = bi.getWidth();
                    sheight = bi.getHeight();
                } else if (degree == 90 || degree == 270) {
                    sheight = bi.getWidth();
                    swidth = bi.getHeight();
                } else {
                    swidth = (int) (Math.sqrt(bi.getWidth() * bi.getWidth()
                            + bi.getHeight() * bi.getHeight()));
                    sheight = (int) (Math.sqrt(bi.getWidth() * bi.getWidth()
                            + bi.getHeight() * bi.getHeight()));
                }

                x = (swidth / 2) - (bi.getWidth() / 2);// 确定原点坐标
                y = (sheight / 2) - (bi.getHeight() / 2);
                BufferedImage spinImage = new BufferedImage(swidth, sheight,
                        bi.getType());
                // 设置图片背景颜色
                Graphics2D gs = (Graphics2D) spinImage.getGraphics();
                gs.setColor(Color.white);
                gs.fillRect(0, 0, swidth, sheight);// 以给定颜色绘制旋转后图片的背景

                AffineTransform at = new AffineTransform();
                at.rotate(theta, swidth / 2, sheight / 2);// 旋转图象
                at.translate(x, y);
                AffineTransformOp op = new AffineTransformOp(at,
                        AffineTransformOp.TYPE_BICUBIC);
                spinImage = op.filter(bi, spinImage);

//         		logger.info("uploadImg4,2 takeTimeMs="
//    					+ (System.nanoTime() - startTime) / (1000 * 1000) + "ms");
                imagePath = templocalPath.substring(0, templocalPath.lastIndexOf(File.separator));
                // 本地判断文件夹是否存在
         		/*File tempFile = new File(imagePath);
         		if (!tempFile.exists()) {
         			tempFile.mkdirs();
         		}*/
                File sf = new File(imagePath, imgName);
                ImageIO.write(spinImage, "gif", sf); // 保存图片
                //System.out.println("保存图片:"+sf);
//                 logger.info("uploadImg4,3 takeTimeMs="
//     					+ (System.nanoTime() - startTime) / (1000 * 1000) + "ms");
            }

        } catch (Exception e) {
            logger.error("上传的图片进行旋转操作出错,e=", e);
            //System.out.println("error Exception ......" + e);
            throw new RuntimeException();
        }
        return imagePath;
    }
}

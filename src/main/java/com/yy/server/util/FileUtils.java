package com.yy.server.util;

import com.yy.server.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;


public class FileUtils {
    private static final int BUFFER_SIZE = 4 * 1024;//
    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private static synchronized String genrateFileName() {
        return System.currentTimeMillis() + getFourRandom();
    }


//	/**
//	 * @param prifix
//	 * @param file
//	 * @return
//	 * @throws FileNotFoundException
//	 */
//	public static String uploadFile(String prifix, File file, String fileName, String type, String userId)
//			throws FileNotFoundException {
//		long fileLen = file.length();
//		if (fileLen > 1L * 1024 * 512) {
//			return Constants.IMAGES_STATUS_OVERSIZE;
//		}
//		InputStream in = new FileInputStream(file);
//		if (type.equals(Constants.PRIVILAGE_FLAG_BRH) && userId == "") {
//			userId = "BRANCH";
//		} else if (type.equals(Constants.PRIVILAGE_FLAG_USR) && userId == "") {
//			userId = "USER";
//		}
//		return uploadFileR(prifix, in, fileName, type, userId);
//	}

    /**
     * @param prifix
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    public static String uploadFiles(String prifix, File file, InputStream in, String fileName, String type, String userId)
            throws FileNotFoundException {
        return uploadFileRs(prifix, in, fileName, type);
    }
//	/**
//	 * @throws
//	 * @Description: 机构图片上传
//	 * @date 2015-8-14 下午4:51:56
//	 * @param prifix
//	 *            根路径 *ETCP
//	 * @param inputStream
//	 *            //上传文件流
//	 * @param fileName
//	 *            //图片名称
//	 * @param type
//	 *            //类型 BRH/USR
//	 * @param userId
//	 *            // 来源ID
//	 * @return String 返回类型
//	 */
//	public static String uploadFileR(String prifix, InputStream inputStream, String fileName, String type, String userId) {
//
//		String targetFileName = getExtention(fileName);
//		long now = System.currentTimeMillis();
//		// 文件名
//		String relative = now + "_big" + targetFileName; // 大图
//		String smallRelative = now + "_small" + targetFileName; // 小图
//		String realRelative = now + targetFileName; // 原图
//		// 本地传残路径
//		String filePath = prifix + File.separator + type + File.separator + userId;
//		if (type.equals(Constants.PRIVILAGE_FLAG_BRH) && !"BRANCH".equals(userId)) {
//			filePath = prifix + "/" + type + "/BRANCH";
//			String n = fileName.substring(0, fileName.lastIndexOf("."));
//			relative = n + "_big" + targetFileName;
//			smallRelative = n + "_small" + targetFileName; // 小图
//			realRelative = n + targetFileName; // 原图
//		} else if (type.equals(Constants.PRIVILAGE_FLAG_USR) && !"USER".equals(userId)) {
//			filePath = prifix + "/" + type + "/USER";
//			String n = fileName.substring(0, fileName.lastIndexOf("."));
//			relative = n + "_big" + targetFileName;
//			smallRelative = n + "_small" + targetFileName; // 小图
//			realRelative = n + targetFileName; // 原图
//		}
//		System.out.println(filePath);
//
//		// 本地判断文件夹是否存在
//		File tempFile = new File(filePath);
//		if (!tempFile.exists()) {
//			tempFile.mkdirs();
//		}
//		// 创建文件
//		String _big =filePath + File.separator + relative;//大图
//		String _small = filePath + File.separator + smallRelative;//小图
//		String _real = filePath + File.separator + realRelative;//原图
//		
//		File imageFile = new File(_big);
//		File smllImageFile = new File(_small);
//		File realImageFile = new File(_real);
//		
//		System.out.println("机构大图路径-->"+imageFile);
//		System.out.println("机构小图路径-->"+smllImageFile);
//		System.out.println("机构原图路径-->"+realImageFile);
//		
//		// 获取第一个图片得名字，返回展示
//		String realRelativePath = Constants.IMAGES_TEMP_PATH + "/" + type + "/" + userId + "/" + smallRelative;
//		System.out.println("获取第一个图片得名字，返回展示-->"+realRelativePath);
//		
//		String path = Constants.IMAGES_TEMP_PATH + "/" + type +"/" + userId;
//		System.out.println("上传文件路径-->"+path);
//		
//		try {
//			// 文件流 缩放
//			if ("BRANCH".equals(userId) || "USER".equals(userId)) {
//				ByteArrayOutputStream bos = new ByteArrayOutputStream();
//				FileInputStream fis = (FileInputStream) inputStream;
//				int len = 0;
//				
//				byte[] b = new byte[BUFFER_SIZE];
//				while ((len = fis.read(b)) != -1) {
//					bos.write(b, 0, len);
//				}
//				byte[] byteArr = bos.toByteArray();
//				saveImage(realImageFile, byteArr, path, realRelative); // 原图保存
//				try {
//					//将原图进行旋转，旋转后写入服务器路径下
//					String bigImg = RotateImage.rotateImagePath(_real,realRelative);
//					byte[] byteArrAfter = reloadPic(_real);//重新读取该路径下的图片
//					
//					
//					compressPic(byteArrAfter, path, relative, imageFile, 400, 300);// 大图，放大保存
//					compressPicSmall(byteArrAfter, path, smallRelative, smllImageFile, 180, 120);// 小图，缩小保存
//					
//					
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			} else {
//				ftpUpload(path, realRelative, realImageFile);
//				ftpUpload(path, relative, imageFile);
//				ftpUpload(path, smallRelative, smllImageFile);
//			}
//		} catch (IOException e) {
//			e.getMessage();
//		}
//		
//		return realRelativePath;
//	}

    /**
     * 上传客户图片
     *
     * @param prifix
     * @param inputStream
     * @param fileName
     * @param type
     * @return
     * @throws IOException
     */
    public static String uploadFileRs(String prifix, InputStream inputStream, String fileName, String type) {
        long startTime = System.nanoTime();

        String targetFileName = getExtention(fileName);
        String newFileName = genrateFileName();
        // 文件名
        String relative = newFileName + "_big" + targetFileName; // 大图
        String smallRelative = newFileName + "_small" + targetFileName; // 小图
        String realRelative = newFileName + targetFileName; // 原图

        // 本地保存路径
        String filePath = prifix + Constants.IMAGES_TEMP_PATH + File.separator + type;
        // 本地判断文件夹是否存在
        File tempFile = new File(filePath);
        if (!tempFile.exists()) {
            tempFile.mkdirs();
        }

        // 创建文件
        String _big = filePath + File.separator + relative; //大图
        String _small = filePath + File.separator + smallRelative;//小图
        String _real = filePath + File.separator + realRelative;//原图

        File imageFile = new File(_big);
        File smllImageFile = new File(_small);
        File realImageFile = new File(_real);

        //logger.info("uploadImg takeTimeMs=" + (System.nanoTime() - startTime) / (1000 * 1000) + "ms");

        logger.info("大图路径   imageFile=" + imageFile + " 小图路径   smllImageFile=" + smllImageFile + " 原图路径    realImageFile=" + realImageFile);

        // 获取第一个图片得名字，返回展示
        String realRelativePath = Constants.IMAGES_TEMP_PATH + "/" + type + "/" + realRelative;

        String path = Constants.IMAGES_TEMP_PATH + "/" + type;
        ByteArrayOutputStream output = null;
        try {
            output = new ByteArrayOutputStream();
            byte[] buffer = new byte[BUFFER_SIZE];
            int n = 0;
            while (-1 != (n = inputStream.read(buffer))) {
                output.write(buffer, 0, n);
            }
//			logger.info("uploadImg2 takeTimeMs="
//					+ (System.nanoTime() - startTime) / (1000 * 1000) + "ms");

            byte[] byteArr = output.toByteArray();
            saveImage(realImageFile, byteArr, path, realRelative); // 原图保存
//			logger.info("uploadImg3 takeTimeMs="
//					+ (System.nanoTime() - startTime) / (1000 * 1000) + "ms");
            String realImg = RotateImage.rotateImagePath(_real, realRelative);
//			logger.info("uploadImg4 takeTimeMs="
//					+ (System.nanoTime() - startTime) / (1000 * 1000) + "ms");

            byte[] byteArrAfter = reloadPic(_real);
            compressPic(byteArrAfter, path, relative, imageFile, 400, 300);// 大图，放大保存

//			logger.info("uploadImg5 takeTimeMs="
//					+ (System.nanoTime() - startTime) / (1000 * 1000) + "ms");

            compressPicSmall(byteArrAfter, path, smallRelative, smllImageFile,
                    180, 120);// 小图，缩小保存
        } catch (Exception e) {
            logger.error("生成文件出错,e=", e);
            realRelativePath = null;
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long takeTimeMs = (System.nanoTime() - startTime) / (1000 * 1000);
        logger.info("返回路径  realRelativePath=" + realRelativePath + " uploadImg takeTimeMs=" + takeTimeMs + "ms");
        return realRelativePath;
    }


    /**
     * 获取4位随机数字
     *
     * @return
     */
    private static String getFourRandom() {
        int seq = new Random().nextInt(10000);
        String seqStr = String.valueOf(seq);
        if (seqStr.length() < 4) {
            seqStr = "0000" + seqStr;
        }
        return seqStr.substring(seqStr.length() - 4);
    }


    static private byte[] reloadPic(String fileName) {
        byte[] buf = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        FileInputStream fis = null;
        try {
            File file = new File(fileName);
            fis = new FileInputStream(file);
            int len = 0;
            byte[] b = new byte[BUFFER_SIZE];
            while ((len = fis.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            buf = bos.toByteArray();
            bos.close();
            fis.close();
        } catch (IOException e) {
            e.getMessage();
        }
        return buf;
    }

    /**
     * 获取文件名后缀
     *
     * @param fileName
     * @return
     */
    private static String getExtention(String fileName) {
        int pos = fileName.lastIndexOf(".");
        if (pos < 0) {
            return "";
        }
        return fileName.substring(pos);
    }

    /**
     * @return boolean 返回类型
     * @throws
     * @Description:
     * @date 2015-8-12 下午5:58:31
     */

    public static boolean compressPic(byte[] arr, String path, String name, File dst, int width, int height)
            throws IOException {
        return compressPic(arr, path, name, dst, width, height, true);
    }

    public static boolean compressPicSmall(byte[] arr, String path, String name, File dst, int width, int height)
            throws IOException {
        return compressPicSmall(arr, path, name, dst, width, height, true);
    }

    /**
     * @return boolean 返回类型
     * @throws
     * @Description:
     * @date 2015-8-12 下午5:58:31
     */
    public static boolean compressPic(byte[] arr, String path, String name, File dst, int width, int height,
                                      boolean isScale) {
        // 图片流
        InputStream bufin = null;
        FileOutputStream out = null;
        boolean b = false;
        try {
            bufin = new ByteArrayInputStream(arr);
            // 图片流
            BufferedImage img = ImageIO.read(bufin);
            int srcWidth = img.getWidth(null);
            int srcHeight = img.getHeight(null);
            if ((srcHeight > height) || (srcWidth > width)) {
                // 判断是否是等比缩放
                if (isScale) {
                    double rate = Math.max((double) srcWidth / width + 0.5, (double) srcHeight / height) + 0.5;
                    width = (int) (srcWidth / rate);
                    height = (int) (srcHeight / rate);
                }
                BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                Image image = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                tag.getGraphics().drawImage(image, 0, 0, null);
                out = new FileOutputStream(dst);
                ImageIO.write(tag, "png", out);
                out.flush();
            } else {
                saveImage(dst, arr, path, name);
            }
            b = true;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (bufin != null) {
                    bufin.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return b;
    }

    /**
     * @return boolean 返回类型
     * @throws
     * @Description:
     * @date 2016-9-05 下午5:58:31
     */
    public static boolean compressPicSmall(byte[] arr, String path, String name, File dst, int width, int height,
                                           boolean isScale) {
        // 图片流
        InputStream bufin = new ByteArrayInputStream(arr);
        FileOutputStream out = null;
        boolean b = false;
        try {
            // 图片流
            BufferedImage img = ImageIO.read(bufin);
            int srcWidth = img.getWidth(null);
            int srcHeight = img.getHeight(null);
            if ((srcHeight > height) || (srcWidth > width)) {
                // 判断是否是等比缩放
                if (isScale) {
                    double rate = Math.max((double) srcWidth / width + 1.8, (double) srcHeight / height) + 1.8;
                    width = (int) (srcWidth / rate);
                    height = (int) (srcHeight / rate);
                }
                BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                Image image = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                tag.getGraphics().drawImage(image, 0, 0, null);
                out = new FileOutputStream(dst);
                ImageIO.write(tag, "png", out);
                out.flush();
            } else {
                saveImage(dst, arr, path, name);
            }
            b = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return b;
    }

    private static boolean saveImage(File dst, byte[] arr, String path, String name) {
        OutputStream os = null;
        boolean flag = false;
        try {
            os = new FileOutputStream(dst);
            os.write(arr);
            os.flush();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStream(os);
        }
        return flag;
    }

    private static void copy(File src, File dst) {
        InputStream fis = null;
        OutputStream fos = null;

        try {
            fis = new BufferedInputStream(new FileInputStream(src));
            fos = new BufferedOutputStream(new FileOutputStream(dst));
            byte[] buf = new byte[BUFFER_SIZE];
            int i = 0;
            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStream(fis, fos);
        }
    }

    /**
     * 关闭流
     *
     * @param cs
     */
    private static void closeStream(Closeable... cs) {
        for (Closeable c : cs) {
            if (c != null) {
                try {
                    c.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        String path = Constants.IMAGES_TEMP_PATH;
        String type = "USR";

        String filename = "QQ截图20161018171240.png";

        try {
            File file = new File("C:\\Users\\admin\\Desktop\\11111\\QQ截图20161024105431.png");
            InputStream input = new FileInputStream(file);
            String IMGS_SOURCE_PATH = "E:\\server\\apache-tomcat-7.0.65_2\\webapps\\easyexchange-trade\\TEMP";
            long startTime = System.nanoTime();
            String result = FileUtils.uploadFiles(IMGS_SOURCE_PATH, file, input, filename, type, "");
            logger.info("uploadImg takeTimeMs=" + (System.nanoTime() - startTime) / (1000 * 1000) + "ms");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
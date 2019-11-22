package com.yy.server.util;


public class ImgSuffix {
    //图片后缀
    static String imgeArray[] =
            {"bmp", "dib", "gif", "jfif", "jpe", "jpeg", "jpg", "png", "tif", "tiff", "ico"};
    //文件后缀
	/*static String fileArray [] = 
			{"doc","html","bak","zip","pdf","xls","txt","wps","rtf","rar","arj"}; */

    /**
     * 根据后缀（String imgeArray []）
     *
     * @param ImgName
     * @return boolean
     * @throws
     */
    public static boolean ChackImgSuffix(String ImgName) {
        boolean flag = true;
        for (int i = 0; i < imgeArray.length; i++) {
            String imgSuffix = ImgName.substring(ImgName.lastIndexOf(".") + 1);
            if (imgeArray[i].equals(imgSuffix.toLowerCase())) {
                flag = true;
                break;
            } else {
                flag = false;
            }
        }
        return flag;

    }
    /**
     * 根据后缀（String fileArray []）
     *
     * @param FileName
     *
     * @return boolean
     * @throws
     *//*
	public static boolean ChackFileSuffix(String FileName){
		
		boolean flag=true;
		for(int i=0;i<fileArray.length;i++)
		{
			if(fileArray[i].equals(FileName.substring(FileName.lastIndexOf(".")+1)))
			{
				flag=true;
				break;
			}
			else{
				flag=false;
			}
		}
		
		return flag;
		
	}*/

    /**
     * 测试方法
     */
    public static void main(String[] args) {

        //boolean chackSuffix = ChackFileSuffix("qwqwqw.doc");
        //System.err.println(chackSuffix);
    }

}

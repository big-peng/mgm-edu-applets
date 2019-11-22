package com.yy.server.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static boolean isBlank(String str) {
        if (null == str) {
            return true;
        }
        if ("".equals(str.trim())) {
            return true;
        }
        return false;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static String toString(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    public static String restrictLength(String strSrc, int iMaxLength) {
        if (strSrc == null) {
            return null;
        }
        if (iMaxLength <= 0) {
            return strSrc;
        }
        String strResult = strSrc;
        byte[] b = null;
        int iLength = strSrc.length();
        if (iLength > iMaxLength) {
            strResult = strResult.substring(0, iMaxLength);
            iLength = iMaxLength;
        }
        while (true) {
            b = strResult.getBytes();
            if (b.length <= iMaxLength) {
                break;
            }
            iLength--;
            strResult = strResult.substring(0, iLength);
        }
        return strResult;
    }

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < length; i++) {
            int num = random.nextInt(str.length());
            buf.append(str.charAt(num));
        }

        return buf.toString();
    }

    /**
     * 左补齐
     *
     * @param target 目标字符串
     * @param fix    补齐字符
     * @param length 目标长度
     * @return
     */
    public static String lPad(String target, String fix, int length) {
        if (target == null || fix == null || !(target.length() < length))
            return target;
        StringBuffer newStr = new StringBuffer();
        for (int i = 0; i < length - target.length(); i++) {
            newStr.append(fix);
        }
        return newStr.append(target).toString();
    }

    /**
     * 右补齐
     *
     * @param target 目标字符串
     * @param fix    补齐字符
     * @param length 目标长度
     * @return
     */
    public static String rPad(String target, String fix, int length) {
        if (target == null || fix == null || !(target.length() < length))
            return target;
        StringBuffer newStr = new StringBuffer();
        newStr.append(target);
        for (int i = 0; i < length - target.length(); i++) {
            newStr.append(fix);
        }
        return newStr.toString();
    }

    /**
     * 字符串数据join操作
     *
     * @param strs
     * @param spi
     * @return
     * @author zhoubo
     */
    public static String join(String[] strs, String spi) {
        StringBuffer buf = new StringBuffer();
        int step = 0;
        for (String str : strs) {
            buf.append(str);
            if (step++ < strs.length - 1)
                buf.append(spi);
        }
        return buf.toString();
    }

    //默认值为无
    public static String toString2(Object obj) {
        if (obj == null) {
            return "无";
        }
        return obj.toString();
    }
    /*public static void main(String[] args){
        System.out.println(StringUtil.getRandomString(10));
    }*/

    /**
     * 固网号码去除 区号-号码 中间的横杠
     * 010-88018802
     *
     * @param str
     * @return
     * @author mayt
     */
    public static String replaceServiceNumBar(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("-");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }


    /**
     * 文件名加后缀
     *
     * @param filePathString
     * @param addString
     * @return
     */
    public String addForFilePath(String filePathString, String addString) {
        int dotaIndex = filePathString.lastIndexOf(".");
        if (dotaIndex != -1) {
            return filePathString.substring(0, dotaIndex) + addString
                    + filePathString.substring(dotaIndex);
        } else {
            return filePathString + addString;
        }
    }

    public static String getCapitalize(String srcString, String addString) {
        return addString + (srcString.charAt(0) + "").toUpperCase() + srcString.substring(1);
    }

    public static String getNoCapitalize(String srcString, String addString) {
        return addString + (srcString.charAt(0) + "").toLowerCase() + srcString.substring(1);
    }

    public static String addWhenUpper(String srcString, String addString) {
        StringBuilder returnStringBuilder = new StringBuilder();
        for (int i = 0; i < srcString.length(); i++) {
            char c = srcString.charAt(i);
            if (Character.isUpperCase(c)) {
                returnStringBuilder.append(addString + c);
            } else {
                returnStringBuilder.append(c);
            }
        }
        return returnStringBuilder.toString();
    }

    /**
     * 获取一个字符串首字母大写的格式 例如：输入adminInfo将返回AdminInfo
     *
     * @param srcString
     * @return
     */
    public static String getFirstUp(String srcString) {
        return (srcString.charAt(0) + "").toUpperCase() + srcString.substring(1);
    }

    /**
     * 获取一个字符串首字母小写的格式 例如：输入AdminInfo将返回adminInfo
     *
     * @param srcString
     * @return
     */
    public static String getFirstLower(String srcString) {
        return (srcString.charAt(0) + "").toLowerCase() + srcString.substring(1);
    }


    /**
     * 该方法主要用于把key1=value&key2=value2字符串转化成map
     *
     * @param param
     * @return
     */
    public static Map<String, String> transformParam(String param) {
        Map<String, String> paramMap = new HashMap<String, String>();
        try {
            if (!isBlank(param)) {
                String[] arr = param.split("&");
                for (int i = 0; i < arr.length; i++) {
                    String[] array = arr[i].split("=");
                    if (array.length > 0) {
                        if (array.length == 2) {
                            paramMap.put(java.net.URLDecoder.decode(array[0], "utf-8"),
                                    java.net.URLDecoder.decode(array[1], "utf-8"));
                        } else {
                            paramMap.put(java.net.URLDecoder.decode(array[0], "utf-8"), "");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paramMap;
    }


    /**
     * 转换编码 - 处理乱码问题
     *
     * @return str为空、转换出错返回null
     */
    public static String decode(String str, String fromCode, String toCode) {
        if (isBlank(str)) {
            return null;
        } else {
            try {
                return new String(str.getBytes(fromCode), toCode);

            } catch (UnsupportedEncodingException e) {
                return null;
            }
        }
    }


    /**
     * 去除字符串前后空格
     *
     * @param str 目标字符串
     * @return 去除空格后的字符串
     * @since 2014-2-27
     */
    public static String trimBlank(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
            sb.deleteCharAt(0);
        }
        while (sb.length() > 0 && Character.isWhitespace(sb.charAt(sb.length() - 1))) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 转换空字符串为null，并去掉字符串前后空格
     *
     * @param str 目标字符串
     * @return null或者"去除空格的字符串"
     * @since 2014-2-27
     */
    public static String parseNull(String str) {
        str = trimBlank(str);
        if (str == null || str.length() == 0) {
            return null;
        }
        return str;
    }

    /**
     * 转换null为""，并去掉字符串前后空格
     *
     * @param str 目标字符串
     * @return ""或者"去除空格的字符串"
     * @since 2014-2-27
     */
    public static String parseEmpty(String str) {
        str = trimBlank(str);
        if (str == null || str.length() == 0) {
            return "";
        }
        return str;
    }

    public static void main(String[] args) {
//        System.out.println(replaceServiceNumBar("010-33883833"));
//        System.out.println(Long.valueOf("3709020000"));
        //System.out.println(lPad(1 + "", "0", 3));

        int min = 1, max = 9;
        int length = String.valueOf(max + "").length();
        int fenjihaomaIndex = -1;
        for (int i = min; i <= max; i++) {
//            addDto.setFangjianbianhao(params.getFangjianbianhaoqianzhui() + i);
//            if (fenjihaomaIndex > -1 && params.getFenjihaomaqianzhui() != null) {
//                if (fenjihaomaIndex <= params.getFenjihaomajieshu()) {
//                    addDto.setFenjihaoma(params.getFenjihaomaqianzhui() + (fenjihaomaIndex++));
//                } else {
//                    addDto.setFenjihaoma("无");
//                }
//            }

            String num = i + "";
            if (String.valueOf(i + "").length() < length) {
                num = lPad(i + "", "0", length);
            }
            System.out.println(num);
        }
    }
}

package com.yy.server.controller.edu.common;

import com.yy.server.common.Constants;
import com.yy.server.controller.back.BaseController;
import com.yy.server.util.FileUtils;
import com.yy.server.util.ImgSuffix;
import com.yy.server.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value = "/file")
@Slf4j
public class FileController extends BaseController {
    @Value("${tmp.upload.path}")
    private String uploadTempPath;

    @Value("${formal.upload.path}")
    private String uploadFormalPath;

    @Value("${app_ip}")
    private String appIp;

    @ResponseBody
    @RequestMapping(value = "/upload/image")
    public Map upload(@RequestParam(value = "file", required = false) MultipartFile myFile, HttpServletRequest request) {
        log.info("开始上传图片");
        Map map = new HashMap();
        String imgPath = "";
        //默认上传成功
        String imageUploadFlag = "0";
        InputStream in = null;
        try {
            // 单个文件上传
            // 转化上传图片流--file
            log.info("file={}", (myFile != null ? myFile.getOriginalFilename() : null));
            if (myFile != null && myFile.getInputStream().available() != 0) {
                CommonsMultipartFile cf = (CommonsMultipartFile) myFile;
                DiskFileItem fi = (DiskFileItem) cf.getFileItem();
                File f = fi.getStoreLocation();
                in = myFile.getInputStream();
                //String IMGS_SOURCE_PATH = request.getSession().getServletContext().getRealPath(uploadTempPath);
                String imageSourcePath = uploadTempPath;

//				 FTP/ETCP/{TYPE}/关联ID/XXX.JPG
                String type = Constants.PRIVILAGE_FLAG_PRODUCT;
                if (ImgSuffix.ChackImgSuffix(myFile.getOriginalFilename()) == true) {
                    long fileLen = f.length();
                    if (fileLen > 5L * 1024 * 1024) {
                        log.error("上传文件超过1M");
                        imageUploadFlag = Constants.IMAGES_STATUS_OVERSIZE;
                        //return Constants.IMAGES_STATUS_OVERSIZE;
                    } else {
                        imgPath = FileUtils.uploadFiles(imageSourcePath, f, in, myFile.getOriginalFilename(), type, "");
                        if (StringUtils.isBlank(imgPath)) {
                            log.info("上传图片失败, imgPath={}", imgPath);
                            imageUploadFlag = Constants.IMAGES_STATUS_LLEGAL;
                        } else {
                            imgPath = appIp + imgPath;
                            log.info("上传图片成功, imgPath={}", imgPath);
                        }
                    }
                } else {
                    imageUploadFlag = Constants.IMAGES_STATUS_LLEGAL;// 上传文件不合法
                }
            } else {
                log.info("上传图片失败");
                imageUploadFlag = Constants.IMAGES_STATUS_FILE;// 上传失败
            }
        } catch (IOException e) {
            log.error("文件流异常：e=", e);
            imageUploadFlag = Constants.IMAGES_STATUS_LLEGAL;// 上传文件不合法
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        map.put("url", imgPath);
        map.put("uploadFlag", imageUploadFlag);
        map.put("ok", myFile.getName());
        return JsonUtil.toJsonSuccess("成功", map);
    }

}

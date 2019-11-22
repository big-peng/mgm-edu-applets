package com.yy.server.controller.customer;

import com.yy.server.common.Constants;
import com.yy.server.controller.customer.base.CusBaseController;
import com.yy.server.model.view.PropertieVO;
import com.yy.server.service.CountryService;
import com.yy.server.service.CustomerService;
import com.yy.server.service.PropertieTabService;
import com.yy.server.util.FileUtils;
import com.yy.server.util.ImgSuffix;
import com.yy.server.util.JsonUtil;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

@CrossOrigin
@Controller
@RequestMapping("/common")
public class CommonController extends CusBaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${tmp.upload.path}")
    private String uploadTempPath;
    @Value("${formal.upload.path}")
    private String uploadFormalPath;
    @Value("${app_ip}")
    private String appIp;

    @Autowired
    private CountryService countryService;

    @Autowired
    private CustomerService customerService;


    @Autowired
    private PropertieTabService propertieTabService;


    @ResponseBody
    @RequestMapping(value = "/countries.json", method = RequestMethod.POST)
    public Map getcountries() {
        return countryService.getAll();
    }


    @ResponseBody
    @RequestMapping(value = "/getSyscode.json", method = RequestMethod.POST)
    public Map getSyscode(@RequestBody PropertieVO vo) {
//        if (StringUtils.isEmpty(vo.getAppId())) {
//            setGlobalCustomAppId(vo);
//        }
        return JsonUtil.toJsonSuccess("查询成功", propertieTabService.getPropertiesMap(vo));
    }

    /**
     * 查询省份列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/provinces.json", method = RequestMethod.POST)
    public Map getprovinces() {
        return JsonUtil.toJsonSuccess("查询成功", customerService.getprovinces());

    }

    /**
     * 查询城市列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cities.json", method = RequestMethod.POST)
    public Map getcities(@RequestBody Map<String, String> map) {
        return JsonUtil.toJsonSuccess("查询成功", customerService.getcities(map.get("provinceId")));
    }

    @ResponseBody
    @RequestMapping(value = "/uploadOpionImg.json")
    public Map upload(@RequestParam(value = "myFile", required = false) MultipartFile file, HttpServletRequest request) {
        logger.info("app端 开始上传图片");
        Map map = new HashMap();

        String imgPath = "";
        //默认上传成功
        String imageUploadFlag = "0";
        InputStream in = null;
        try {
            logger.info("file={}", (file != null ? file.getOriginalFilename() : null));
            if (file != null && file.getInputStream().available() != 0) {
                // 单个文件上传
                // 转化上传图片流--file
                CommonsMultipartFile cf = (CommonsMultipartFile) file;
                DiskFileItem fi = (DiskFileItem) cf.getFileItem();
                File f = fi.getStoreLocation();
                in = file.getInputStream();
                //String IMGS_SOURCE_PATH = request.getSession().getServletContext().getRealPath(uploadTempPath);
                String imageSourcePath = uploadTempPath;

//				 FTP/ETCP/{TYPE}/关联ID/XXX.JPG
                String type = Constants.PRIVILAGE_FLAG_PRODUCT;
                if (ImgSuffix.ChackImgSuffix(file.getOriginalFilename()) == true) {
                    long fileLen = f.length();
                    if (fileLen > 5L * 1024 * 1024) {
                        logger.error("上传文件超过5M, fileSize={}", fileLen);
                        imageUploadFlag = Constants.IMAGES_STATUS_OVERSIZE;
                        //return Constants.IMAGES_STATUS_OVERSIZE;
                    } else {
                        imgPath = FileUtils.uploadFiles(imageSourcePath, f, in, file.getOriginalFilename(), type, "");
                        if (org.apache.commons.lang3.StringUtils.isBlank(imgPath)) {
                            logger.info("上传图片失败, imgPath={}", imgPath);
                            imageUploadFlag = Constants.IMAGES_STATUS_LLEGAL;
                        } else {
                            imgPath = appIp + imgPath;
                            logger.info("上传图片成功, imgPath={} fileSize={}", imgPath, fileLen);
                        }
                    }
                } else {
                    // 上传文件不合法
                    imageUploadFlag = Constants.IMAGES_STATUS_LLEGAL;
                }
            } else {
                // 上传失败
                logger.info("上传图片失败");
                imageUploadFlag = Constants.IMAGES_STATUS_FILE;
            }
        } catch (IOException e) {
            logger.error("文件流异常：e=", e);
            // 上传文件不合法
            imageUploadFlag = Constants.IMAGES_STATUS_LLEGAL;
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
        map.put("ok", file.getName());
        return JsonUtil.toJsonSuccess("成功", map);
    }

}

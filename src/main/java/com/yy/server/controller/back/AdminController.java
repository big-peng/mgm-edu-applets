package com.yy.server.controller.back;

import com.alibaba.fastjson.JSONObject;
import com.yy.server.common.Constants;
import com.yy.server.common.ErrorEnum;
import com.yy.server.common.LoginEnum;
import com.yy.server.common.shiro.CustomerAuthenticationToken;
import com.yy.server.model.Admin;
import com.yy.server.model.view.AdminRegVO;
import com.yy.server.service.AdminService;
import com.yy.server.util.FileUtils;
import com.yy.server.util.ImgSuffix;
import com.yy.server.util.JsonUtil;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value = "/console")
public class AdminController extends BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${tmp.upload.path}")
    private String uploadTempPath;

    @Value("${formal.upload.path}")
    private String uploadFormalPath;

    @Value("${app_ip}")
    private String appIp;


    @Autowired
    private AdminService adminService;

    @ResponseBody
    @RequestMapping(value = "/login.json", method = RequestMethod.POST)
    public Map loginPost(@RequestBody @Valid AdminRegVO admin, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_37006);
        }
        String username = admin.getUsername();
        CustomerAuthenticationToken token = new CustomerAuthenticationToken(admin.getUsername(), admin.getPasswd(), admin.getAppId(), true);
        token.setLoginType(LoginEnum.ADMIN.toString());
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        Admin byUsername = adminService.findByUsername(admin.getUsername(), admin.getAppId());
        if (byUsername != null) {
            if (byUsername.getState() == null || byUsername.getState() == false) {
                return JsonUtil.toJsonError(ErrorEnum.ERROR_21013);
            }
        }
        try {
            logger.info("对用户[" + username + "]进行登录验证..验证开始");
            currentUser.login(token);
            logger.info("对用户[" + username + "]进行登录验证..验证通过");
        } catch (UnknownAccountException uae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            return JsonUtil.toJsonError(ErrorEnum.ERROR_21011);
        } catch (IncorrectCredentialsException ice) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            return JsonUtil.toJsonError(ErrorEnum.ERROR_21015);
        } catch (LockedAccountException lae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            return JsonUtil.toJsonError(ErrorEnum.ERROR_21013);
        } catch (ExcessiveAttemptsException eae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            return JsonUtil.toJsonError(ErrorEnum.ERROR_21014);
        } catch (AuthenticationException ae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            return JsonUtil.toJsonError(ErrorEnum.ERROR_21012);
        }

        //验证是否登录成功
        if (currentUser.isAuthenticated()) {
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("loginType", LoginEnum.ADMIN.toString());
            logger.info("用户[" + username + "]登录认证通过");
            Admin user = adminService.selectByUsername(admin.getUsername());
            user.setPassword("helloworld,mmy!");
            user.setSalt("helloworld,mmy!");
            return JsonUtil.toJsonSuccess("成功", user);
        } else {
            token.clear();
            return JsonUtil.toJsonError(ErrorEnum.ERROR_21016);
        }
    }

    @RequiresPermissions("system:admin")
    @ResponseBody
    @RequestMapping(value = "/sys.json")
    public Map sys() {
        return JsonUtil.toJsonSuccess("ok", null);
    }


    /**
     * 退出
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/logout.json")
    public Map logout() {
        try {
            if (SecurityUtils.getSubject() != null)
                SecurityUtils.getSubject().logout();
        } catch (Exception e) {
            logger.error("登出错误", e);
            return JsonUtil.toJsonError(ErrorEnum.ERROR_21016);
        }
        return JsonUtil.toJsonSuccess("登出成功", null);
    }


    @ResponseBody
    @RequestMapping(value = "/accounterr")
    public Map accounterr() {
        return JsonUtil.toJsonError(ErrorEnum.ERROR_21099);
    }


    @ResponseBody
    @RequestMapping(value = "/upload.json")
    public Map upload(@RequestParam(value = "file", required = false) MultipartFile myFile, HttpServletRequest request) {
        logger.info("开始上传图片");
        Map map = new HashMap();
        String imgPath = "";
        //默认上传成功
        String imageUploadFlag = "0";
        InputStream in = null;
        try {
            // 单个文件上传
            // 转化上传图片流--file
            logger.info("file={}", (myFile != null ? myFile.getOriginalFilename() : null));
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
                        logger.error("上传文件超过1M");
                        imageUploadFlag = Constants.IMAGES_STATUS_OVERSIZE;
                        //return Constants.IMAGES_STATUS_OVERSIZE;
                    } else {
                        imgPath = FileUtils.uploadFiles(imageSourcePath, f, in, myFile.getOriginalFilename(), type, "");
                        if (StringUtils.isBlank(imgPath)) {
                            logger.info("上传图片失败, imgPath={}", imgPath);
                            imageUploadFlag = Constants.IMAGES_STATUS_LLEGAL;
                        } else {
                            imgPath = appIp + imgPath;
                            logger.info("上传图片成功, imgPath={}", imgPath);
                        }
                    }
                } else {
                    imageUploadFlag = Constants.IMAGES_STATUS_LLEGAL;// 上传文件不合法
                }
            } else {
                logger.info("上传图片失败");
                imageUploadFlag = Constants.IMAGES_STATUS_FILE;// 上传失败
            }
        } catch (IOException e) {
            logger.error("文件流异常：e=", e);
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

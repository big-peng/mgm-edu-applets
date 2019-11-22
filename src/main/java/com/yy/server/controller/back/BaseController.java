package com.yy.server.controller.back;


import com.yy.server.model.Admin;
import com.yy.server.model.IAppIdModel;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;

public class BaseController {


    @Value("${app.id}")
    private String appId;


    public void setGlobalAdminAppId(IAppIdModel appIdModel) {
        setGlobalAdminAppId(appIdModel, false);
    }



    public void setGlobalAdminAppId(IAppIdModel appIdModel, boolean needAllDataIfAdmin) {
        Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
        if (!(admin instanceof Admin)) {
            throw new RuntimeException("用户没有登录");
        }


        if (admin.getAppId().equals(appId) && admin.getIsSystem()) { //超级管理员
            if (needAllDataIfAdmin) {
                appIdModel.setAppId(null);
            }
        } else {  //普通用户
            appIdModel.setAppId(admin.getAppId());
        }
    }


}

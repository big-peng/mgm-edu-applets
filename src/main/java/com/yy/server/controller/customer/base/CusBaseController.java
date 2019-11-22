package com.yy.server.controller.customer.base;

import com.yy.server.model.Customer;
import com.yy.server.model.IAppIdModel;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;

public class CusBaseController {

    @Value("${app.id}")
    private String appId;

    /**
     * 设置android ios appId
     *
     * @param appIdModel
     */
    public void setGlobalCustomAppId(IAppIdModel appIdModel) {
        Customer customer = ((Customer) SecurityUtils.getSubject().getPrincipal());
        if (!(customer instanceof Customer)) {
            throw new RuntimeException("用户没有登录");
        }
        appIdModel.setAppId(customer.getAppId());
    }

    /**
     * 设置android ios appId
     *
     */
    public void checkLogin() {
        Customer customer = ((Customer) SecurityUtils.getSubject().getPrincipal());
        if (!(customer instanceof Customer)) {
            throw new RuntimeException("用户没有登录");
        }
    }

}

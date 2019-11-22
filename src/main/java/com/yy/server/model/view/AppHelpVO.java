package com.yy.server.model.view;

import com.yy.server.model.AppHelp;

public class AppHelpVO extends AppHelp {

    private String appType;
    private String appHelpId;
    private String appId;

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }


    public String getAppHelpId() {
        return appHelpId;
    }

    public void setAppHelpId(String appHelpId) {
        this.appHelpId = appHelpId;
    }

    @Override
    public String getAppId() {
        return appId;
    }

    @Override
    public void setAppId(String appId) {
        this.appId = appId;
    }
}

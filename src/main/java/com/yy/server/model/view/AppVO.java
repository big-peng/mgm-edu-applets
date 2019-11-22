package com.yy.server.model.view;

import com.yy.server.model.IAppIdModel;

public class AppVO implements IAppIdModel {
    private String type;
    /**
     * app
     */
    private String appId;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

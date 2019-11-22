package com.yy.server.model.view;

import com.yy.server.model.IAppIdModel;

public class PropertieVO implements IAppIdModel {

    private String gName;
    private String gKey;
    private String appId;


    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public String getgKey() {
        return gKey;
    }

    public void setgKey(String gKey) {
        this.gKey = gKey;
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

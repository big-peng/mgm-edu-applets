package com.yy.server.model.view;

import com.yy.server.model.IAppIdModel;

public class AdminSelVO extends PageVO implements IAppIdModel {

    private Integer id;
    private String adminId;
    private String username;
    private Boolean state;
    private Boolean isEmployee;
    private String remark;
    private Boolean isSystem;
    private Integer careerType;
    private String realName;
    private Boolean isShowPad;
    private String appId;

    private String roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }


    public Boolean getSystem() {
        return isSystem;
    }

    public void setSystem(Boolean system) {
        isSystem = system;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String getAppId() {
        return appId;
    }

    @Override
    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Integer getCareerType() {
        return careerType;
    }

    public void setCareerType(Integer careerType) {
        this.careerType = careerType;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getEmployee() {
        return isEmployee;
    }

    public void setEmployee(Boolean employee) {
        this.isEmployee = employee;
    }

    public Boolean getShowPad() {
        return isShowPad;
    }

    public void setShowPad(Boolean showPad) {
        isShowPad = showPad;
    }
}

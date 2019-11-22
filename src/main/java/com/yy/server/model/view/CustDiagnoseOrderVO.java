package com.yy.server.model.view;

import com.yy.server.model.IAppIdModel;

import java.util.Date;

public class CustDiagnoseOrderVO extends PageVO implements IAppIdModel {

    private Integer rowNum;

    private Integer id;

    private Integer custId;

    private String account;

    private String userName;

    private String name;

    private Integer planId;
    private String planName;


    private String faceDiagnoseId;
    private String faceDiagnoseName;

    private String physicianId;
    private String physicianName;

    private String assistantId;
    private String assistantName;

    private Date createTime;

    private String appId;

    private String aimQuestions;

    private String updateFlag;

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }


    public String getFaceDiagnoseName() {
        return faceDiagnoseName;
    }

    public void setFaceDiagnoseName(String faceDiagnoseName) {
        this.faceDiagnoseName = faceDiagnoseName;
    }



    public String getPhysicianName() {
        return physicianName;
    }

    public void setPhysicianName(String physicianName) {
        this.physicianName = physicianName;
    }



    public String getAssistantName() {
        return assistantName;
    }

    public void setAssistantName(String assistantName) {
        this.assistantName = assistantName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String getAppId() {
        return appId;
    }

    @Override
    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAimQuestions() {
        return aimQuestions;
    }

    public void setAimQuestions(String aimQuestions) {
        this.aimQuestions = aimQuestions;
    }

    public String getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }


    public String getFaceDiagnoseId() {
        return faceDiagnoseId;
    }

    public void setFaceDiagnoseId(String faceDiagnoseId) {
        this.faceDiagnoseId = faceDiagnoseId;
    }

    public String getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(String physicianId) {
        this.physicianId = physicianId;
    }

    public String getAssistantId() {
        return assistantId;
    }

    public void setAssistantId(String assistantId) {
        this.assistantId = assistantId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

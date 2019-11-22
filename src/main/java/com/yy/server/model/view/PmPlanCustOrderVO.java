package com.yy.server.model.view;

import com.yy.server.model.IAppIdModel;
import com.yy.server.model.PmPlanCustOrderDetail;
import com.yy.server.model.PmPlanCustOrderDetailPh;

import java.util.Date;
import java.util.List;

public class PmPlanCustOrderVO extends PageVO implements IAppIdModel {
    private Integer id;

    /**
     * 用户ID
     */
    private Integer custId;

    /**
     * 针对问题
     */
    private String aimQuestions;

    private String name;


    /**
     * 方案id
     */
    private Integer planId;

    private String planName;


    /**
     * 面诊师id
     */
    private String faceDiagnoseId;


    /**
     * 面诊师名称
     */
    private String faceDiagnoseName;

    /**
     * 医师id
     */
    private String physicianId;

    /**
     * 医师名称
     */
    private String physicianName;


    /**
     * 助理id
     */
    private String assistantId;

    /**
     * 助理id
     */
    private String assistantName;

    /**
     * 诊断备注
     */
    private String diagnoseRemark;

    private String appId;

    private Date createTime;


    private String flag;


    private List<PmPlanCustOrderDetail> planCustOrderDetailList;

    private List<PmPlanCustOrderDetailPh> planCustOrderDetailPhList;

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

    public String getAimQuestions() {
        return aimQuestions;
    }

    public void setAimQuestions(String aimQuestions) {
        this.aimQuestions = aimQuestions;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
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

    public String getDiagnoseRemark() {
        return diagnoseRemark;
    }

    public void setDiagnoseRemark(String diagnoseRemark) {
        this.diagnoseRemark = diagnoseRemark;
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

    public List<PmPlanCustOrderDetail> getPlanCustOrderDetailList() {
        return planCustOrderDetailList;
    }

    public void setPlanCustOrderDetailList(List<PmPlanCustOrderDetail> planCustOrderDetailList) {
        this.planCustOrderDetailList = planCustOrderDetailList;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
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

    public List<PmPlanCustOrderDetailPh> getPlanCustOrderDetailPhList() {
        return planCustOrderDetailPhList;
    }

    public void setPlanCustOrderDetailPhList(List<PmPlanCustOrderDetailPh> planCustOrderDetailPhList) {
        this.planCustOrderDetailPhList = planCustOrderDetailPhList;
    }
}

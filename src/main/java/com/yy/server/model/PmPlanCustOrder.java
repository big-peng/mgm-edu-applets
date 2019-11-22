package com.yy.server.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "pm_plan_cust_order")
public class PmPlanCustOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "cust_id")
    private Integer custId;

    /**
     * 针对问题
     */
    @Column(name = "aim_questions")
    private String aimQuestions;


    /**
     * 项目名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 方案id
     */
    @Column(name = "plan_id")
    private Integer planId;

    /**
     * 方案名称
     */
    @Column(name = "plan_name")
    private String planName;


    /**
     * 面诊师id
     */
    @Column(name = "face_diagnose_id")
    private String faceDiagnoseId;

    /**
     * 医师id
     */
    @Column(name = "physician_id")
    private String physicianId;

    /**
     * 助理id
     */
    @Column(name = "assistant_id")
    private String assistantId;


    /**
     * 诊断备注
     */
    @Column(name = "diagnose_remark")
    private String diagnoseRemark;

    /**
     * 删除标识:Y删除：N未删除
     */
    @Column(name = "del_flag")
    private String delFlag;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "app_id")
    private String appId;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户ID
     *
     * @return cust_id - 用户ID
     */
    public Integer getCustId() {
        return custId;
    }

    /**
     * 设置用户ID
     *
     * @param custId 用户ID
     */
    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    /**
     * 获取针对问题
     *
     * @return aim_questions - 针对问题
     */
    public String getAimQuestions() {
        return aimQuestions;
    }

    /**
     * 设置针对问题
     *
     * @param aimQuestions 针对问题
     */
    public void setAimQuestions(String aimQuestions) {
        this.aimQuestions = aimQuestions;
    }

    /**
     * 获取方案id
     *
     * @return plan_id - 方案id
     */
    public Integer getPlanId() {
        return planId;
    }

    /**
     * 设置方案id
     *
     * @param planId 方案id
     */
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

    /**
     * 获取删除标识:Y删除：N未删除
     *
     * @return del_flag - 删除标识:Y删除：N未删除
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 设置删除标识:Y删除：N未删除
     *
     * @param delFlag 删除标识:Y删除：N未删除
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return app_id
     */
    public String getAppId() {
        return appId;
    }

    /**
     * @param appId
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDiagnoseRemark() {
        return diagnoseRemark;
    }

    public void setDiagnoseRemark(String diagnoseRemark) {
        this.diagnoseRemark = diagnoseRemark;
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
}
package com.yy.server.model.view;

import com.yy.server.model.IAppIdModel;
import com.yy.server.model.PmPlanFactorValue;

import java.util.Date;
import java.util.List;

public class PmPlanFactorVO extends PageVO implements IAppIdModel {

    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 名称类型：1下拉框2多选框3单选框radio组建4普通文本5时间框
     */
    private String comType;

    /**
     * 名称提示语
     */
    private String tipsDesc;

    /**
     * 是否存在备注输入框
     */
    private String isRemarkInput;

    /**
     * 备注输入框内容
     */
    private String remarkInputStr;

    /**
     * 删除标识:Y删除：N未删除
     */
    private String delFlag;

    /**
     * 描述状态 1:有效，0:无效
     */
    private Byte state;

    private Date createTime;

    private Date updateTime;

    private String appId;

    /**
     * 序号
     */
    private Integer orderId;

    /**
     * 方案id
     */
    private Integer planId;

    private List<PmPlanFactorValue> planFactorValueList;

    @Override
    public String getAppId() {
        return appId;
    }

    @Override
    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComType() {
        return comType;
    }

    public void setComType(String comType) {
        this.comType = comType;
    }

    public String getTipsDesc() {
        return tipsDesc;
    }

    public void setTipsDesc(String tipsDesc) {
        this.tipsDesc = tipsDesc;
    }

    public String getIsRemarkInput() {
        return isRemarkInput;
    }

    public void setIsRemarkInput(String isRemarkInput) {
        this.isRemarkInput = isRemarkInput;
    }

    public String getRemarkInputStr() {
        return remarkInputStr;
    }

    public void setRemarkInputStr(String remarkInputStr) {
        this.remarkInputStr = remarkInputStr;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public List<PmPlanFactorValue> getPlanFactorValueList() {
        return planFactorValueList;
    }

    public void setPlanFactorValueList(List<PmPlanFactorValue> planFactorValueList) {
        this.planFactorValueList = planFactorValueList;
    }
}

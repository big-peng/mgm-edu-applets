package com.yy.server.model.view;

import com.yy.server.model.IAppIdModel;

import java.util.Date;

public class PmPlanFactorValueVO extends PageVO implements IAppIdModel {

    private Integer id;

    /**
     * 因子展示标签
     */
    private String showLabel;

    /**
     * 因子值
     */
    private String showValue;

    /**
     * 因子id
     */
    private Integer factorId;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShowLabel() {
        return showLabel;
    }

    public void setShowLabel(String showLabel) {
        this.showLabel = showLabel;
    }

    public String getShowValue() {
        return showValue;
    }

    public void setShowValue(String showValue) {
        this.showValue = showValue;
    }

    public Integer getFactorId() {
        return factorId;
    }

    public void setFactorId(Integer factorId) {
        this.factorId = factorId;
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

    @Override
    public String getAppId() {
        return appId;
    }

    @Override
    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}

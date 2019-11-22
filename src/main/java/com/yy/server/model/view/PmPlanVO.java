package com.yy.server.model.view;

import com.yy.server.model.IAppIdModel;
import com.yy.server.model.PmPlanCustOrderDetailPh;

import java.util.Date;
import java.util.List;

public class PmPlanVO extends PageVO implements IAppIdModel {

    private Integer id;

    private String name;

    /**
     * 父项目id
     */
    private Integer parentId;

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

    private List<PmPlanFactorVO> planFactorVoList;

    private List<PmPlanCustOrderDetailPh> planCustOrderDetailPhList;

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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

    public List<PmPlanFactorVO> getPlanFactorVoList() {
        return planFactorVoList;
    }

    public void setPlanFactorVoList(List<PmPlanFactorVO> planFactorVoList) {
        this.planFactorVoList = planFactorVoList;
    }

    public List<PmPlanCustOrderDetailPh> getPlanCustOrderDetailPhList() {
        return planCustOrderDetailPhList;
    }

    public void setPlanCustOrderDetailPhList(List<PmPlanCustOrderDetailPh> planCustOrderDetailPhList) {
        this.planCustOrderDetailPhList = planCustOrderDetailPhList;
    }
}

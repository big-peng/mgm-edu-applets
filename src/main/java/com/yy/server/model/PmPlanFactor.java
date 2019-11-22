package com.yy.server.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "pm_plan_factor")
public class PmPlanFactor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 名称类型：1下拉框2多选框3单选框radio组建4普通文本5时间框
     */
    @Column(name = "com_type")
    private String comType;

    /**
     * 名称提示语
     */
    @Column(name = "tips_desc")
    private String tipsDesc;

    /**
     * 是否存在备注输入框
     */
    @Column(name = "is_remark_input")
    private String isRemarkInput;

    /**
     * 备注输入框内容
     */
    @Column(name = "remark_input_str")
    private String remarkInputStr;

    /**
     * 删除标识:Y删除：N未删除
     */
    @Column(name = "del_flag")
    private String delFlag;

    /**
     * 描述状态 1:有效，0:无效
     */
    private Byte state;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "app_id")
    private String appId;

    /**
     * 序号
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 方案id
     */
    @Column(name = "plan_id")
    private Integer planId;

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
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取名称类型：1下拉框2多选框3单选框radio组建4普通文本5时间框
     *
     * @return com_type - 名称类型：1下拉框2多选框3单选框radio组建4普通文本5时间框
     */
    public String getComType() {
        return comType;
    }

    /**
     * 设置名称类型：1下拉框2多选框3单选框radio组建4普通文本5时间框
     *
     * @param comType 名称类型：1下拉框2多选框3单选框radio组建4普通文本5时间框
     */
    public void setComType(String comType) {
        this.comType = comType;
    }

    /**
     * 获取名称提示语
     *
     * @return tips_desc - 名称提示语
     */
    public String getTipsDesc() {
        return tipsDesc;
    }

    /**
     * 设置名称提示语
     *
     * @param tipsDesc 名称提示语
     */
    public void setTipsDesc(String tipsDesc) {
        this.tipsDesc = tipsDesc;
    }

    /**
     * 获取是否存在备注输入框
     *
     * @return is_remark_input - 是否存在备注输入框
     */
    public String getIsRemarkInput() {
        return isRemarkInput;
    }

    /**
     * 设置是否存在备注输入框
     *
     * @param isRemarkInput 是否存在备注输入框
     */
    public void setIsRemarkInput(String isRemarkInput) {
        this.isRemarkInput = isRemarkInput;
    }

    /**
     * 获取备注输入框内容
     *
     * @return remark_input_str - 备注输入框内容
     */
    public String getRemarkInputStr() {
        return remarkInputStr;
    }

    /**
     * 设置备注输入框内容
     *
     * @param remarkInputStr 备注输入框内容
     */
    public void setRemarkInputStr(String remarkInputStr) {
        this.remarkInputStr = remarkInputStr;
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
     * 获取描述状态 1:有效，0:无效
     *
     * @return state - 描述状态 1:有效，0:无效
     */
    public Byte getState() {
        return state;
    }

    /**
     * 设置描述状态 1:有效，0:无效
     *
     * @param state 描述状态 1:有效，0:无效
     */
    public void setState(Byte state) {
        this.state = state;
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

    /**
     * 获取序号
     *
     * @return order_id - 序号
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置序号
     *
     * @param orderId 序号
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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
}
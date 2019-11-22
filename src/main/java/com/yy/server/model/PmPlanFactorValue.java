package com.yy.server.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "pm_plan_factor_value")
public class PmPlanFactorValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 因子展示标签
     */
    @Column(name = "show_label")
    private String showLabel;

    /**
     * 因子值
     */
    @Column(name = "show_value")
    private String showValue;

    /**
     * 展示值类别：1正常取值；2图片上传地址
     */
    @Column(name = "show_value_type")
    private Byte showValueType;

    /**
     * 默认值：1存在0不存在
     */
    @Column(name = "is_have_default_value")
    private Byte isHaveDefaultValue;

    /**
     * 默认值类别：1图片URL,2文本
     */
    @Column(name = "default_value_type")
    private Byte defaultValueType;

    /**
     * 因子id
     */
    @Column(name = "factor_id")
    private Integer factorId;

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
     * 获取因子展示标签
     *
     * @return show_label - 因子展示标签
     */
    public String getShowLabel() {
        return showLabel;
    }

    /**
     * 设置因子展示标签
     *
     * @param showLabel 因子展示标签
     */
    public void setShowLabel(String showLabel) {
        this.showLabel = showLabel;
    }

    /**
     * 获取因子值
     *
     * @return show_value - 因子值
     */
    public String getShowValue() {
        return showValue;
    }

    /**
     * 设置因子值
     *
     * @param showValue 因子值
     */
    public void setShowValue(String showValue) {
        this.showValue = showValue;
    }

    /**
     * 获取展示值类别：1正常取值；2图片上传地址
     *
     * @return show_value_type - 展示值类别：1正常取值；2图片上传地址
     */
    public Byte getShowValueType() {
        return showValueType;
    }

    /**
     * 设置展示值类别：1正常取值；2图片上传地址
     *
     * @param showValueType 展示值类别：1正常取值；2图片上传地址
     */
    public void setShowValueType(Byte showValueType) {
        this.showValueType = showValueType;
    }

    /**
     * 获取默认值：1存在0不存在
     *
     * @return is_have_default_value - 默认值：1存在0不存在
     */
    public Byte getIsHaveDefaultValue() {
        return isHaveDefaultValue;
    }

    /**
     * 设置默认值：1存在0不存在
     *
     * @param isHaveDefaultValue 默认值：1存在0不存在
     */
    public void setIsHaveDefaultValue(Byte isHaveDefaultValue) {
        this.isHaveDefaultValue = isHaveDefaultValue;
    }

    /**
     * 获取默认值类别：1图片URL,2文本
     *
     * @return default_value_type - 默认值类别：1图片URL,2文本
     */
    public Byte getDefaultValueType() {
        return defaultValueType;
    }

    /**
     * 设置默认值类别：1图片URL,2文本
     *
     * @param defaultValueType 默认值类别：1图片URL,2文本
     */
    public void setDefaultValueType(Byte defaultValueType) {
        this.defaultValueType = defaultValueType;
    }

    /**
     * 获取因子id
     *
     * @return factor_id - 因子id
     */
    public Integer getFactorId() {
        return factorId;
    }

    /**
     * 设置因子id
     *
     * @param factorId 因子id
     */
    public void setFactorId(Integer factorId) {
        this.factorId = factorId;
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
}
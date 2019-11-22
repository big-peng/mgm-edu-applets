package com.yy.server.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "pm_plan_cust_order_detail_ph")
public class PmPlanCustOrderDetailPh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 类型：1额上；2额下；3左眼下；4左脸颊；5右眼下；6右脸颊；7下颌
     */
    private Byte type;

    /**
     * ph当前值
     */
    @Column(name = "current_value")
    private BigDecimal currentValue;

    /**
     * ph历史值
     */
    @Column(name = "last_value")
    private BigDecimal lastValue;

    /**
     * 删除标识:Y删除：N未删除
     */
    @Column(name = "del_flag")
    private String delFlag;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 备注输入框
     */
    private String remark;

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
     * 获取订单ID
     *
     * @return order_id - 订单ID
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单ID
     *
     * @param orderId 订单ID
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取类型：1额上；2额上；3左眼下；4左脸颊；5右眼下；6右脸颊；7下颌
     *
     * @return type - 类型：1额上；2额上；3左眼下；4左脸颊；5右眼下；6右脸颊；7下颌
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置类型：1额上；2额上；3左眼下；4左脸颊；5右眼下；6右脸颊；7下颌
     *
     * @param type 类型：1额上；2额上；3左眼下；4左脸颊；5右眼下；6右脸颊；7下颌
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 获取ph当前值
     *
     * @return current_value - ph当前值
     */
    public BigDecimal getCurrentValue() {
        return currentValue;
    }

    /**
     * 设置ph当前值
     *
     * @param currentValue ph当前值
     */
    public void setCurrentValue(BigDecimal currentValue) {
        this.currentValue = currentValue;
    }

    /**
     * 获取ph历史值
     *
     * @return last_value - ph历史值
     */
    public BigDecimal getLastValue() {
        return lastValue;
    }

    /**
     * 设置ph历史值
     *
     * @param lastValue ph历史值
     */
    public void setLastValue(BigDecimal lastValue) {
        this.lastValue = lastValue;
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
     * 获取备注输入框
     *
     * @return remark - 备注输入框
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注输入框
     *
     * @param remark 备注输入框
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
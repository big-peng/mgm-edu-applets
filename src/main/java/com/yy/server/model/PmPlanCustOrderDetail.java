package com.yy.server.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "pm_plan_cust_order_detail")
public class PmPlanCustOrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 因子id
     */
    @Column(name = "factor_id")
    private Integer factorId;

    /**
     * 因子值，多取值的话，以“，”分割
     */
    @Column(name = "factor_value")
    private String factorValue;

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
     * 因子的备注输入框
     */
    @Column(name = "factor_remark_value")
    private String factorRemarkValue;

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
     * 获取因子值，多取值的话，以“，”分割
     *
     * @return factor_value - 因子值，多取值的话，以“，”分割
     */
    public String getFactorValue() {
        return factorValue;
    }

    /**
     * 设置因子值，多取值的话，以“，”分割
     *
     * @param factorValue 因子值，多取值的话，以“，”分割
     */
    public void setFactorValue(String factorValue) {
        this.factorValue = factorValue;
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
     * 获取因子的备注输入框
     *
     * @return factor_remark_value - 因子的备注输入框
     */
    public String getFactorRemarkValue() {
        return factorRemarkValue;
    }

    /**
     * 设置因子的备注输入框
     *
     * @param factorRemarkValue 因子的备注输入框
     */
    public void setFactorRemarkValue(String factorRemarkValue) {
        this.factorRemarkValue = factorRemarkValue;
    }
}
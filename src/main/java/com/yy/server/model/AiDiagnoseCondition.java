package com.yy.server.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "ai_diagnose_condition")
public class AiDiagnoseCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "upper_id")
    private Integer upperId;

    /**
     * 名称
     */
    private String name;

    /**
     * 是否通过：Y通过；N不通过
     */
    @Column(name = "is_true")
    private String isTrue;

    /**
     * 是否结束：1结束，0未结束
     */
    @Column(name = "is_end")
    private String isEnd;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 删除标识:Y删除：N未删除
     */
    @Column(name = "del_flag")
    private String delFlag;

    /**
     * 备注
     */
    private String remark;

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
     * @return upper_id
     */
    public Integer getUpperId() {
        return upperId;
    }

    /**
     * @param upperId
     */
    public void setUpperId(Integer upperId) {
        this.upperId = upperId;
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
     * 获取是否通过：Y通过；N不通过
     *
     * @return is_true - 是否通过：Y通过；N不通过
     */
    public String getIsTrue() {
        return isTrue;
    }

    /**
     * 设置是否通过：Y通过；N不通过
     *
     * @param isTrue 是否通过：Y通过；N不通过
     */
    public void setIsTrue(String isTrue) {
        this.isTrue = isTrue;
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
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(String isEnd) {
        this.isEnd = isEnd;
    }
}
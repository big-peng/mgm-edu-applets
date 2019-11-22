package com.yy.server.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "ai_diagnose_cust_result")
public class AiDiagnoseCustResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名称
     */
    @Column(name = "cust_id")
    private Integer custId;

    @Column(name = "result_id")
    private Integer resultId;

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
     * @return cust_id - 名称
     */
    public Integer getCustId() {
        return custId;
    }

    /**
     * 设置名称
     *
     * @param custId 名称
     */
    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    /**
     * @return result_id
     */
    public Integer getResultId() {
        return resultId;
    }

    /**
     * @param resultId
     */
    public void setResultId(Integer resultId) {
        this.resultId = resultId;
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
}
package com.yy.server.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "ai_diagnose_template_result_match")
public class AiDiagnoseTemplateResultMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名称
     */
    @Column(name = "result_id")
    private Integer resultId;

    /**
     * 条件项
     */
    @Column(name = "have_conditions")
    private String haveConditions;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 删除标识:Y删除：N未删除
     */
    @Column(name = "del_flag")
    private String delFlag;

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
     * @return result_id - 名称
     */
    public Integer getResultId() {
        return resultId;
    }

    /**
     * 设置名称
     *
     * @param resultId 名称
     */
    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    /**
     * 获取条件项
     *
     * @return have_conditions - 条件项
     */
    public String getHaveConditions() {
        return haveConditions;
    }

    /**
     * 设置条件项
     *
     * @param haveConditions 条件项
     */
    public void setHaveConditions(String haveConditions) {
        this.haveConditions = haveConditions;
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
}
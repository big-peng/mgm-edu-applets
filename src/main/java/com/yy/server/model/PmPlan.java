package com.yy.server.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "pm_plan")
public class PmPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 父项目id
     */
    @Column(name = "parent_id")
    private Integer parentId;

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
     * 获取项目名称
     *
     * @return name - 项目名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置项目名称
     *
     * @param name 项目名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取父项目id
     *
     * @return parent_id - 父项目id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父项目id
     *
     * @param parentId 父项目id
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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
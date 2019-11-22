package com.yy.server.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "app_show_skin")
public class AppShowSkin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型
     */
    private String type;

    /**
     * 位置示例URL
     */
    @Column(name = "place_eg_url")
    private String placeEgUrl;

    /**
     * 推荐尺寸
     */
    @Column(name = "recommend_size")
    private String recommendSize;

    /**
     * 图片URL
     */
    @Column(name = "img_url")
    private String imgUrl;

    /**
     * 描述
     */
    private String remark;

    /**
     * 描述状态 1:有效，0:无效
     */
    private Boolean state;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "app_id")
    private String appId;

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
     * 获取位置示例URL
     *
     * @return place_eg_url - 位置示例URL
     */
    public String getPlaceEgUrl() {
        return placeEgUrl;
    }

    /**
     * 设置位置示例URL
     *
     * @param placeEgUrl 位置示例URL
     */
    public void setPlaceEgUrl(String placeEgUrl) {
        this.placeEgUrl = placeEgUrl;
    }

    /**
     * 获取推荐尺寸
     *
     * @return recommend_size - 推荐尺寸
     */
    public String getRecommendSize() {
        return recommendSize;
    }

    /**
     * 设置推荐尺寸
     *
     * @param recommendSize 推荐尺寸
     */
    public void setRecommendSize(String recommendSize) {
        this.recommendSize = recommendSize;
    }

    /**
     * 获取图片URL
     *
     * @return img_url - 图片URL
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置图片URL
     *
     * @param imgUrl 图片URL
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * 获取描述
     *
     * @return remark - 描述
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置描述
     *
     * @param remark 描述
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取描述状态 1:有效，0:无效
     *
     * @return state - 描述状态 1:有效，0:无效
     */
    public Boolean getState() {
        return state;
    }

    /**
     * 设置描述状态 1:有效，0:无效
     *
     * @param state 描述状态 1:有效，0:无效
     */
    public void setState(Boolean state) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
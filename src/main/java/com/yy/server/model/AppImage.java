package com.yy.server.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "app_image")
public class AppImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 来源id
     */
    @Column(name = "from_id")
    private Integer fromId;

    /**
     * 序号
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 图片
     */
    @Column(name = "img_path")
    private String imgPath;

    /**
     * 类型
     */
    private String type;

    /**
     * 描述
     */
    @Column(name = "del_flag")
    private String delFlag;

    /**
     * 上传时间
     */
    @Column(name = "upload_time")
    private Date uploadTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
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
     * 获取来源id
     *
     * @return from_id - 来源id
     */
    public Integer getFromId() {
        return fromId;
    }

    /**
     * 设置来源id
     *
     * @param fromId 来源id
     */
    public void setFromId(Integer fromId) {
        this.fromId = fromId;
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
     * 获取图片
     *
     * @return img_path - 图片
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * 设置图片
     *
     * @param imgPath 图片
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    /**
     * 获取类型
     *
     * @return type - 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型
     *
     * @param type 类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取描述
     *
     * @return del_flag - 描述
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 设置描述
     *
     * @param delFlag 描述
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 获取上传时间
     *
     * @return upload_time - 上传时间
     */
    public Date getUploadTime() {
        return uploadTime;
    }

    /**
     * 设置上传时间
     *
     * @param uploadTime 上传时间
     */
    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
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
}
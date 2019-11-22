package com.yy.server.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "app_product")
public class AppProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 图片URL
     */
    @Column(name = "img_url")
    private String imgUrl;

    /**
     * 轮播顺序
     */
    @Column(name = "order_id")
    private String orderId;

    /**
     * 产品类别ID
     */
    @Column(name = "product_type_id")
    private Integer productTypeId;

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
     * 获取轮播顺序
     *
     * @return order_id - 轮播顺序
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置轮播顺序
     *
     * @param orderId 轮播顺序
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取产品类别ID
     *
     * @return product_type_id - 产品类别ID
     */
    public Integer getProductTypeId() {
        return productTypeId;
    }

    /**
     * 设置产品类别ID
     *
     * @param productTypeId 产品类别ID
     */
    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
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


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
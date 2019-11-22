package com.yy.server.model.view;

import com.yy.server.model.IAppIdModel;

import java.math.BigDecimal;


public class AiProductResultVO extends PageVO implements IAppIdModel {
    private Integer id;

    /**
     * 诊断结果id
     */
    private Integer aiResultId;

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
    private String imgUrl;

    /**
     * 轮播顺序
     */
    private String orderId;

    /**
     * 产品类别ID
     */
    private Integer productTypeId;

    /**
     * 描述状态 1:有效，0:无效
     */
    private Boolean state;

    /**
     * 描述
     */
    private String remark;


    private String appId;


    @Override
    public String getAppId() {
        return appId;
    }

    @Override
    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Integer getAiResultId() {
        return aiResultId;
    }

    public void setAiResultId(Integer aiResultId) {
        this.aiResultId = aiResultId;
    }
}

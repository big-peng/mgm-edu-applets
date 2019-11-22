package com.yy.server.model.view;

import com.yy.server.model.IAppIdModel;

import java.util.List;
import java.util.Map;

public class AppProductTypeVO implements IAppIdModel {
    private Integer id;

    /**
     * 名称
     */
    private String name;


    private String imgUrl;


    private String orderId;

    /**
     * 描述
     */
    private String remark;


    private String appId;

    private List<AppProductVO> productList;

    private List<Map> products;

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
     * @return app_id
     */
    @Override
    public String getAppId() {
        return appId;
    }

    /**
     * @param appId
     */
    @Override
    public void setAppId(String appId) {
        this.appId = appId;
    }

    public List<AppProductVO> getProductList() {
        return productList;
    }

    public void setProductList(List<AppProductVO> productList) {
        this.productList = productList;
    }

    public List<Map> getProducts() {
        return products;
    }

    public void setProducts(List<Map> products) {
        this.products = products;
    }
}
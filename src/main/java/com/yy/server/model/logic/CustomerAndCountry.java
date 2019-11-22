package com.yy.server.model.logic;


import com.yy.server.model.AppShop;
import com.yy.server.model.Country;
import com.yy.server.model.Customer;

import java.io.Serializable;

public class CustomerAndCountry extends Customer implements Serializable {

    private Country country;

    private AppShop appShop;

    private String shopName;

    private String shopLogo;

    private Integer resultId;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public AppShop getAppShop() {
        return appShop;
    }

    public void setAppShop(AppShop appShop) {
        this.appShop = appShop;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }
}
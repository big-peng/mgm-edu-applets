package com.yy.server.model.logic;

import com.yy.server.model.Admin;

public class AdminVO extends Admin {
    private String shopName;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}

/*
 * Copyright (c) 2017 <l_iupeiyu@qq.com> All rights reserved.
 */

package com.yy.server.common;


public enum LoginEnum {

    CUSTOMER("1"), ADMIN("2"), DEVICE("3");

    private String type;

    private LoginEnum(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type.toString();
    }
}

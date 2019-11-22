/*
 * Copyright (c) 2017 <l_iupeiyu@qq.com> All rights reserved.
 */

package com.yy.server.common;


public enum SMSEnum {
    //   注册        忘记密码          绑定设备     导出        转账
    REGIST("0"), FORGET("1"),MODPASS("2"),BINDDEVICE("3"),EXPORT("4"),TRANSFERACCOUNTS("5");

    private String type;

    private SMSEnum(String type){
        this.type = type;
    }

    @Override
    public  String toString(){
        return this.type.toString();
    }
}

package com.yy.server.mapper;

import com.yy.server.model.Admin;
import com.yy.server.model.AppShop;
import com.yy.server.model.view.AppShopVO;
import com.yy.server.model.view.PropertieVO;
import com.yy.server.util.MyMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AppShopMapper extends MyMapper<AppShop> {

    List<AppShopVO> selectAppShopList(AppShopVO vo);

    @Select("select zid,shop_name from app_shop")
    @Results({
            @Result(property = "zid", column = "zid"),
            @Result(property = "shopName", column = "shop_name")
    })
    List<HashMap<String, Object>> selectShopName(Admin admin);

    @Select("select sms_reg_code,sms_validation_code,mail_title, must_invite,show_financing,show_auto_invest from app_wallet where zid = #{appId}")
    @Results({
            @Result(property = "smsRegCode", column = "sms_reg_code"),
            @Result(property = "smsValidationCode", column = "sms_validation_code"),
            @Result(property = "mailTitle", column = "mail_title"),
            @Result(property = "mustInvite", column = "must_invite", javaType = Integer.class),
            @Result(property = "group_value", column = "show_financing", javaType = Integer.class),
            @Result(property = "showAutoInvest", column = "show_auto_invest", javaType = Integer.class)
    })
    List<Map<String, Object>> getPropertiesMap(PropertieVO vo);

    List<AppShop> selectByZid(String appId);

    String getProtocolMap(String appId);

}
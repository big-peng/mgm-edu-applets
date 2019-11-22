package com.yy.server.mapper;

import com.yy.server.model.BasicCustomerInfo;
import com.yy.server.model.Country;
import com.yy.server.model.Customer;
import com.yy.server.model.logic.CustomerAndCountry;
import com.yy.server.model.view.CustSelVO;
import com.yy.server.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface CustomerMapper extends MyMapper<Customer> {
    List<CustomerAndCountry> getCustomerList(CustSelVO custSelVO);

    List<CustomerAndCountry> getCusts(CustSelVO custSelVO);

    Country selectById(String admin_id);

    BasicCustomerInfo getBasicCustomerInfo(Map map);

    void updCustomerInfo(Map map);


    //根据邀请码和appId查询用户
    Customer getCustByCode(String code, String appId);

    //根据邀请码查询用户
    Customer getTheCustByCode(String code);

    //根据邀请码查询被邀请的用户列表
    List<Customer> getInvitedCustomers(String code);

    //根据邀请码查询被邀请的用户列表
    List<CustomerAndCountry> getInvitors(CustSelVO custSelVO);

    CustomerAndCountry getCustomerInfo(int id);

    Customer selectByUsername(@Param("account") String account, @Param("appId") String appId);

    Integer selectCounts(Customer cus);
}
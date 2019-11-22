package com.yy.server.controller.back;

import com.yy.server.common.ErrorEnum;
import com.yy.server.model.logic.CustomerAndCountry;
import com.yy.server.model.view.CustSelVO;
import com.yy.server.service.CustomerService;
import com.yy.server.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller("customer")
@RequestMapping(value = "/console")
public class CustomerController extends BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomerService customerService;

    /**
     * 获取用户列表
     *
     * @param
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "/getCustomers.json", method = RequestMethod.POST)
    public Map getCustomers(@RequestBody CustSelVO custSelVO) {
        if (StringUtils.isEmpty(custSelVO.getAppId())) {
            setGlobalAdminAppId(custSelVO);
        }
        custSelVO.setState(true);
        HashMap<String, Object> map = customerService.getCusts(custSelVO);
        return JsonUtil.toJsonSuccess("获取列表成功", map);
    }

    @ResponseBody
    @RequestMapping(value = "/customerInfo.json", method = RequestMethod.POST)
    public Map getBasicCustomerInfo(@RequestBody Map map) {
        if (map.isEmpty()) {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_21101);
        }
        Object idObj = map.get("id");
        if (idObj != null) {
            CustomerAndCountry custInfo = customerService.getBasicCustomerInfo(Integer.parseInt(idObj.toString()));
            return JsonUtil.toJsonSuccess("获取用户基本信息", custInfo);
        }
        return JsonUtil.toJsonError(ErrorEnum.ERROR_31423);
    }

    @ResponseBody
    @RequestMapping(value = "/customerUpd.json", method = RequestMethod.POST)
    public Map updCustomerInfo(@RequestBody Map map) {
        if (map.isEmpty()) {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_21101);
        }
        customerService.updCustomerInfo(map);
        return JsonUtil.toJsonSuccess("修改用户基本信息", null);
    }


    @ResponseBody
    @RequestMapping(value = "/batchUpdateMemer.json", method = RequestMethod.POST)
    public Map updateMember(@RequestBody Map map) {
        if (map.isEmpty()) {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_21101);
        }
        Object idsObj = map.get("ids");
        if (idsObj == null) {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_21101);
        }
        List<Integer> ids = (List<Integer>) idsObj;
        customerService.batchUpdateCustomerInfo(ids);
        return JsonUtil.toJsonSuccess("修改成功");
    }

}
package com.yy.server.controller.customer;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yy.server.common.ErrorEnum;
import com.yy.server.common.LoginEnum;
import com.yy.server.common.shiro.CustomerAuthenticationToken;
import com.yy.server.controller.customer.base.CusBaseController;
import com.yy.server.model.*;
import com.yy.server.model.logic.CustomerAndCountry;
import com.yy.server.model.view.*;
import com.yy.server.service.*;
import com.yy.server.util.JsonUtil;
import com.yy.server.util.SessionUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yy.server.common.ErrorEnum.*;

@RestController
@RequestMapping("/customer")
public class CustomerController extends CusBaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomerService customerService;


    @Autowired
    private AdminService adminService;

    /************************登录**********************************************/

    @ResponseBody
    @RequestMapping(value = "/reg.json", method = RequestMethod.POST)
    public Map doReg(@RequestBody CustRegVO vo) {
        try {
            if (customerService.reg(vo)) {
                return JsonUtil.toJsonSuccess("登录成功");
            } else {
                return JsonUtil.toJsonError(ERROR_21001);
            }
        } catch (Exception e) {
            String message = e.getMessage();
            logger.error("REG CUSTER ERROR:", e);
            if (ERROR_21003.getCode().toString().equals(message)) {
                return JsonUtil.toJsonError(ERROR_21003);
            } else if (ERROR_21002.getCode().toString().equals(message)) {
                return JsonUtil.toJsonError(ERROR_21002);
            } else if (ERROR_21004.getCode().toString().equals(message)) {
                return JsonUtil.toJsonError(ERROR_21004);
            }

            return JsonUtil.toJsonError(ERROR_21001);
        }
    }


    /**
     * 功能说明:app用户登录
     *
     * @param vo
     * @param bindingResult
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login.json", method = RequestMethod.POST)
    public Map doLogin(@RequestBody @Valid CustRegVO vo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_37006);
        }
        //先做登出
        if (SecurityUtils.getSubject() != null)
            SecurityUtils.getSubject().logout();

        String account = vo.getAccount();
        CustomerAuthenticationToken token = new CustomerAuthenticationToken(vo.getAccount(), vo.getPasswd(), false);
        token.setLoginType(LoginEnum.CUSTOMER.toString());
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            logger.info("对用户[" + account + "]进行登录验证..验证开始");
            currentUser.login(token);
            logger.info("对用户[" + account + "]进行登录验证..验证通过");
        } catch (UnknownAccountException uae) {
            logger.info("对用户[" + account + "]进行登录验证..验证未通过,未知账户");
            return JsonUtil.toJsonError(ErrorEnum.ERROR_21011);
        } catch (IncorrectCredentialsException ice) {
            logger.info("对用户[" + account + "]进行登录验证..验证未通过,错误的凭证");
            return JsonUtil.toJsonError(ErrorEnum.ERROR_21012);
        } catch (LockedAccountException lae) {
            logger.info("对用户[" + account + "]进行登录验证..验证未通过,账户已锁定");
            return JsonUtil.toJsonError(ErrorEnum.ERROR_21013);
        } catch (ExcessiveAttemptsException eae) {
            logger.info("对用户[" + account + "]进行登录验证..验证未通过,错误次数过多");
            return JsonUtil.toJsonError(ErrorEnum.ERROR_21014);
        } catch (AuthenticationException ae) {
            logger.info("对用户[" + account + "]进行登录验证..验证未通过,堆栈轨迹如下");
            return JsonUtil.toJsonError(ErrorEnum.ERROR_21015);
        } catch (Exception e) {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_21015);
        }
        //验证是否登录成功
        if (currentUser.isAuthenticated()) {
            Session session = currentUser.getSession();
            session.setAttribute("loginType", LoginEnum.CUSTOMER.toString());
            logger.info("前台用户[" + account + "]登录认证通过");
            //验证是否更换设备
            Customer customer = (Customer) SessionUtil.getUserInfo();
            HashMap<String, Object> map = customerService.checkDevice(customer.getId() + "", vo.getDeviceId(), vo.getAccount(), vo.getAppId());
            return JsonUtil.toJsonSuccess("成功", map);
        } else {
            token.clear();
            return JsonUtil.toJsonError(ErrorEnum.ERROR_21016);
        }
    }


    /**
     * app设备登录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deviceLogin.json", method = RequestMethod.POST)
    public Map deviceInfoLogin(@RequestBody Map<String, String> map, BindingResult bindingResult) {
        //String deviceUuid = map.get("deviceUuid");
        String deviceName = map.get("deviceName");
        String devicePwd = map.get("devicePwd");
        //先做登出
        if (SecurityUtils.getSubject() != null)
            SecurityUtils.getSubject().logout();

        String account = deviceName;
        //String account = deviceUuid;
        CustomerAuthenticationToken token = new CustomerAuthenticationToken(account, devicePwd, false);
        token.setLoginType(LoginEnum.DEVICE.toString());
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            logger.info("对用户[" + account + "]进行登录验证..验证开始");
            currentUser.login(token);
            logger.info("对用户[" + account + "]进行登录验证..验证通过");
        } catch (UnknownAccountException uae) {
            logger.info("对用户[" + account + "]进行登录验证..验证未通过,未知账户");
            return JsonUtil.toJsonError(ErrorEnum.ERROR_22011);
        } catch (IncorrectCredentialsException ice) {
            logger.info("对用户[" + account + "]进行登录验证..验证未通过,错误的凭证");
            return JsonUtil.toJsonError(ErrorEnum.ERROR_22012);
        } catch (LockedAccountException lae) {
            logger.info("对用户[" + account + "]进行登录验证..验证未通过,账户已锁定");
            return JsonUtil.toJsonError(ErrorEnum.ERROR_22013);
        } catch (ExcessiveAttemptsException eae) {
            logger.info("对用户[" + account + "]进行登录验证..验证未通过,错误次数过多");
            return JsonUtil.toJsonError(ErrorEnum.ERROR_22014);
        } catch (AuthenticationException ae) {
            logger.info("对用户[" + account + "]进行登录验证..验证未通过,堆栈轨迹如下");
            return JsonUtil.toJsonError(ErrorEnum.ERROR_22015);
        } catch (Exception e) {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_22015);
        }
        //验证是否登录成功
        if (currentUser.isAuthenticated()) {
            Session session = currentUser.getSession();
            session.setAttribute("loginType", LoginEnum.DEVICE.toString());
            logger.info("前台用户[" + account + "]登录认证通过");
            DeviceInfo deviceInfo = (DeviceInfo) SessionUtil.getUserInfo();
            HashMap<String, String> respMap = Maps.newHashMap();
            respMap.put("repalceFlag", 0 + "");
            respMap.put("account", deviceInfo.getDeviceName());
            respMap.put("appId", deviceInfo.getAppId());
            return JsonUtil.toJsonSuccess("成功", respMap);
        } else {
            token.clear();
            return JsonUtil.toJsonError(ErrorEnum.ERROR_21016);
        }
    }


    @ResponseBody
    @RequestMapping(value = "/accounterr")
    public Map accounterr() {
        return JsonUtil.toJsonError(ErrorEnum.ERROR_21099);
    }

    /**
     * 退出
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/logout.json")
    public Map logout() {
        try {
            if (SecurityUtils.getSubject() != null)
                SecurityUtils.getSubject().logout();
        } catch (Exception e) {
            logger.error("登出错误", e);
            return JsonUtil.toJsonError(ErrorEnum.ERROR_21021);
        }
        return JsonUtil.toJsonSuccess("登出成功", null);
    }

    /**
     * 功能说明:用戶信息新增
     *
     * @param vo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addCustInfo.json", method = RequestMethod.POST)
    public Map add11CustInfo(@RequestBody CustRegVO vo) {
        try {
            if (customerService.saveCustomer(vo)) {
                return JsonUtil.toJsonSuccess("新增成功");
            } else {
                return JsonUtil.toJsonError(ErrorEnum.ERROR_21001);
            }
        } catch (Exception e) {
            String message = e.getMessage();
            logger.error("REG CUSTER ERROR:", e);
            if (ErrorEnum.ERROR_21003.getCode().toString().equals(message)) {
                return JsonUtil.toJsonError(ErrorEnum.ERROR_21003);
            } else if (ErrorEnum.ERROR_21002.getCode().toString().equals(message)) {
                return JsonUtil.toJsonError(ErrorEnum.ERROR_21002);
            } else if (ErrorEnum.ERROR_21004.getCode().toString().equals(message)) {
                return JsonUtil.toJsonError(ErrorEnum.ERROR_21004);
            }
            return JsonUtil.toJsonError(ErrorEnum.ERROR_21001);
        }
    }


    /**
     * 忘记密码
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/forget.json")
    public Map forget(@RequestBody CustRegVO vo) {
        try {
            if (customerService.forget(vo)) {
                return JsonUtil.toJsonSuccess("修改成功", null);
            } else {
                return JsonUtil.toJsonError(ERROR_21031);
            }
        } catch (Exception e) {
            String message = e.getMessage();
            logger.error("REG CUSTER ERROR:", e);
            if (ERROR_21003.getCode().toString().equals(message)) {
                return JsonUtil.toJsonError(ERROR_21003);
            } else if (ERROR_21002.getCode().toString().equals(message)) {
                return JsonUtil.toJsonError(ERROR_21002);
            } else if (ERROR_21005.getCode().toString().equals(message)) {
                return JsonUtil.toJsonError(ERROR_21005);
            }
            return JsonUtil.toJsonError(ERROR_21031);
        }
    }

}

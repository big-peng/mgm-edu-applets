package com.yy.server.controller.back;

import com.alibaba.fastjson.JSONObject;
import com.yy.server.common.ErrorEnum;
import com.yy.server.model.Admin;
import com.yy.server.model.view.AdminCreateVO;
import com.yy.server.model.view.AdminSelVO;
import com.yy.server.service.AdminService;
import com.yy.server.util.JsonUtil;
import com.yy.server.util.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value = "/console")
public class MemberController extends BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminService adminService;

    /**
     * 获取用户列表
     *
     * @param
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "/getMembers.json", method = RequestMethod.POST)
    public Map getCustomers(@RequestBody AdminSelVO adminSelVO) {
        Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
        if ("10086".equals(admin.getAppId())) {
            adminSelVO.setAppId(null);
        } else {
            adminSelVO.setAppId(admin.getAppId());
        }
        adminSelVO.setState(true);
        HashMap<String, Object> map = adminService.getMembers(adminSelVO);
        return JsonUtil.toJsonSuccess("获用户列表成功", map);
    }

    /**
     * 获取单个用户
     *
     * @param
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "/getMember.json", method = RequestMethod.POST)
    public Map getMember(@RequestBody AdminCreateVO admin) {
        if (StringUtils.isEmpty(admin.getAdmin_id()))
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31400);
        HashMap<String, Object> map = adminService.getMemberById(admin.getAdmin_id());
        return JsonUtil.toJsonSuccess("获用户成功", map);
    }

    /**
     * 启用或冻结用户
     *
     * @param
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "/memberState.json", method = RequestMethod.POST)
    public Map memberState(@RequestBody AdminCreateVO adminCreateVO) {
        if (StringUtils.isEmpty(adminCreateVO.getAdmin_id()) || adminCreateVO.getState() == null) {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_21101);
        }
        int i = adminService.delMember(adminCreateVO);
        if (i < 0) {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31414);
        } else {
            return JsonUtil.toJsonSuccess("修改状态成功", i);
        }
    }

    /**
     * 检查用户名是否存在
     *
     * @param
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "/checkusername.json", method = RequestMethod.POST)
    public Map checkusername(@RequestBody AdminCreateVO adminCreateVO) {
        if (StringUtils.isEmpty(adminCreateVO.getUsername())) {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_21101);
        }
        Admin admin = adminService.findByUsernameIn(adminCreateVO);
        if (admin == null) {
            return JsonUtil.toJsonSuccess("用户名可用");
        } else {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_21102);
        }
    }

    /**
     * 创建用户
     *
     * @param adminvo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/createMember.json", method = RequestMethod.POST)
    public Map createMember(@RequestBody AdminCreateVO adminvo) {
        logger.info("获取新增用户信息: reqParam={}", JSONObject.toJSONString(adminvo));
        if (!StringUtils.isEmpty(adminvo.getUsername())) {
            Map checkusername = checkusername(adminvo);
            Object code = checkusername.get("code");
            if (code.equals("0")) {
                return JsonUtil.toJsonError(ErrorEnum.ERROR_21102);
            }
        }
        if(adminvo.getEmployee() != null && !adminvo.getEmployee()){
            String passwd = adminvo.getPasswd();
            if (passwd == null || "".equals(passwd)) {
                return JsonUtil.toJsonError(ErrorEnum.ERROR_21105);
            }
        }
        Admin admin = (Admin) SessionUtil.getUserInfo();
        if(adminvo.getAppId() == null){
            adminvo.setAppId(admin.getAppId());
        }
        int insert = adminService.createAdmin(adminvo);
        if (insert <= 0) {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_21103);
        } else {
            return JsonUtil.toJsonSuccess("创建成功");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/updateMember.json", method = RequestMethod.POST)
    public Map updateMember(@RequestBody AdminCreateVO adminvo) {
        Admin admin = (Admin) SessionUtil.getUserInfo();
        if(adminvo.getAppId() == null){
            adminvo.setAppId(admin.getAppId());
        }
        if (admin.getIsSystem() != true) {
            Admin admin1 = adminService.getAdminById(adminvo.getAdminId());
            if (admin1 != null && admin1.getIsSystem() == true) {
                return JsonUtil.toJsonError(ErrorEnum.ERROR_31422);
            }
        }
        int update = adminService.updateMember(adminvo);
        if (update <= 0) {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31415);
        } else {
            return JsonUtil.toJsonSuccess("修改成功");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/rePassWord.json", method = RequestMethod.POST)
    public Map rePassWord(@RequestBody AdminCreateVO adminvo) {
        Admin admin = (Admin) SessionUtil.getUserInfo();
        if (admin.getIsSystem() != true) {
            Admin admin1 = adminService.getAdminById(adminvo.getAdminId());
            if (admin1 != null && admin1.getIsSystem() == true) {
                return JsonUtil.toJsonError(ErrorEnum.ERROR_31422);
            }
        }
        int update = adminService.rePassWord(adminvo);
        if (update <= 0) {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31420);
        } else {
            return JsonUtil.toJsonSuccess("修改成功");
        }
    }

}
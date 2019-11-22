package com.yy.server.controller.back;

import com.yy.server.common.ErrorEnum;
import com.yy.server.model.Role;
import com.yy.server.model.view.PageVO;
import com.yy.server.model.view.RoleVO;
import com.yy.server.service.RoleService;
import com.yy.server.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/console")
public class RoleController extends BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RoleService roleService;

    @ResponseBody
    @RequestMapping(value = "/roleList.json", method = RequestMethod.POST)
    public Map roleList(PageVO page) {
        HashMap<String, Object> map = roleService.getRoleList(page);
        return JsonUtil.toJsonSuccess("获取角色列表", map);
    }


    @ResponseBody
    @RequestMapping(value = "/crestedrole.json", method = RequestMethod.POST)
    public Map createdrole(@RequestBody RoleVO roleVO) {
        int insert = roleService.createdrole(roleVO);
        if (insert > 0) {
            return JsonUtil.toJsonSuccess("创建角色成功", insert);
        } else {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31416);
        }
    }


    @ResponseBody
    @RequestMapping(value = "/getRole.json", method = RequestMethod.POST)
    public Map getRole(@RequestBody RoleVO roleVO) {
        if (StringUtils.isEmpty(roleVO.getRoleId()))
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31417);
        HashMap<String, Object> map = roleService.getRoleById(roleVO.getRoleId());
        return JsonUtil.toJsonSuccess("获取角色成功", map);
    }


    @ResponseBody
    @RequestMapping(value = "/getAllRole.json", method = RequestMethod.POST)
    public Map getAllRole() {
        List<Role> roles = roleService.getAllRole();
        return JsonUtil.toJsonSuccess("获取角色成功", roles);
    }

    @ResponseBody
    @RequestMapping(value = "/updateRole.json", method = RequestMethod.POST)
    public Map updateRole(@RequestBody RoleVO roleVO) {
        int update = roleService.updateRole(roleVO);
        if (update > 0) {
            return JsonUtil.toJsonSuccess("修改角色成功", update);
        } else {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31418);
        }
    }


    @ResponseBody
    @RequestMapping(value = "/updateRoleEnable.json", method = RequestMethod.POST)
    public Map memberState(@RequestBody RoleVO roleVO) {
        if (StringUtils.isEmpty(roleVO.getRoleId()) || roleVO.getEnable() == null)
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31417);
        int i = roleService.updateRoleEnable(roleVO.getRoleId(), roleVO.getEnable());
        if (i > 0) {
            return JsonUtil.toJsonSuccess("修改成功", i);
        } else {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31419);
        }
    }
}
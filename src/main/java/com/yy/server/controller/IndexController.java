package com.yy.server.controller;

import com.yy.server.common.ErrorEnum;
import com.yy.server.model.Admin;
import com.yy.server.model.DeviceInfo;
import com.yy.server.model.Menu;
import com.yy.server.model.Role;
import com.yy.server.service.AdminService;
import com.yy.server.service.MenuService;
import com.yy.server.service.RoleService;
import com.yy.server.util.JsonUtil;
import com.yy.server.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class IndexController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    RoleService roleService;
    @Autowired
    AdminService adminService;
    @Autowired
    MenuService menuService;

    @RequestMapping(value = "/")
    public void index(HttpServletResponse response) {
        try {
            response.sendRedirect("/index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/checkLgoin.json", method = RequestMethod.GET)
    public Map checkLgoin() {
        //DeviceInfo deviceInfo = (DeviceInfo) SessionUtil.getUserInfo();
        Admin admin = (Admin) SessionUtil.getUserInfo();
        if (admin == null) {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31421);
        } else {
            if (admin != null) {
                if (admin.getIsSystem() == true) {
                    List<Menu> menus = menuService.getAllMenus();
                    return JsonUtil.toJsonSuccess("用户已登录", menus);
                }
                List<Role> roles = adminService.getRoles(admin.getAdminId());
                List<Menu> menus = roleService.getMenus(roles);
                return JsonUtil.toJsonSuccess("用户已登录", menus);
            }
//            if (deviceInfo != null) {
//                return JsonUtil.toJsonSuccess("用户已登录", "");
//            }
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31421);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/data.json", method = RequestMethod.POST)
    public Map data(HttpServletRequest request) {
        String type = request.getParameter("data");
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 0);
        return map;
    }

}

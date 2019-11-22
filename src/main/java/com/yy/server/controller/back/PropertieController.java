package com.yy.server.controller.back;

import com.yy.server.common.ErrorEnum;
import com.yy.server.model.PropertieTab;
import com.yy.server.model.view.PageVO;
import com.yy.server.model.view.PropertieTabVO;
import com.yy.server.model.view.PropertieVO;
import com.yy.server.service.PropertieTabService;
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
import java.util.Map;


@Controller
@RequestMapping(value = "/console")
public class PropertieController extends BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PropertieTabService propertieTabService;


    /**
     * 获取关于列表
     */
    @ResponseBody
    @RequestMapping(value = "/aboutManagementList.json", method = RequestMethod.POST)
    public Map aboutManagementList(@RequestBody PropertieTabVO propertieTabVO) {
        if (StringUtils.isEmpty(propertieTabVO.getAppId())) {
            setGlobalAdminAppId(propertieTabVO);
        }
        return propertieTabService.getAboutManagementList(propertieTabVO);
    }


    @ResponseBody
    @RequestMapping(value = "/getSyscode.json", method = RequestMethod.POST)
    public Map getSyscode(@RequestBody PropertieVO vo) {
        if (org.springframework.util.StringUtils.isEmpty(vo.getAppId())) {
            setGlobalAdminAppId(vo);
        }
        return JsonUtil.toJsonSuccess("查询成功", propertieTabService.getPropertiesMap(vo));
    }

    /**
     * 新增关于配置
     */
    @ResponseBody
    @RequestMapping(value = "/addAboutManagement.json", method = RequestMethod.POST)
    public Map addAboutManagement(@RequestBody PropertieTabVO propertieTabVO) {
        return propertieTabService.addAboutManagement(propertieTabVO);
    }

    /**
     * 删除关于配置
     */
    @ResponseBody
    @RequestMapping(value = "/delAboutManagement.json", method = RequestMethod.POST)
    public Map delAboutManagement(@RequestBody PropertieTabVO propertieTabVO) {
        return propertieTabService.delAboutManagement(propertieTabVO);
    }

    /**
     * 回显关于配置
     */
    @ResponseBody
    @RequestMapping(value = "/getAboutManagementById.json", method = RequestMethod.POST)
    public Map getAboutManagementById(@RequestBody PropertieTabVO propertieTabVO) {
        return propertieTabService.getAboutManagementById(propertieTabVO);
    }

    /**
     * 修改关于配置
     */
    @ResponseBody
    @RequestMapping(value = "/updateAboutManagement.json", method = RequestMethod.POST)
    public Map updateAboutManagement(@RequestBody PropertieTab propertieTab) {
        return propertieTabService.updateAboutManagement(propertieTab);
    }


    @ResponseBody
    @RequestMapping(value = "/propertieList.json", method = RequestMethod.POST)
    public Map propertieList(@RequestBody PageVO page) {
        HashMap<String, Object> map = propertieTabService.getPropertiesList(page);
        return JsonUtil.toJsonSuccess("获取配置列表", map);
    }

    @ResponseBody
    @RequestMapping(value = "/createPropertie.json", method = RequestMethod.POST)
    public Map createPropertie(@RequestBody PropertieTabVO propertieVO) {
        String groupKey = propertieVO.getGroupKey();
        String groupName = propertieVO.getGroupName();
        PropertieTab propertieTab = checkPropertie(groupKey, groupName);
        if (propertieTab != null) {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31430);
        }
        int insert = propertieTabService.createPropertie(propertieVO);
        if (insert > 0) {
            return JsonUtil.toJsonSuccess("成功", insert);
        } else {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31430);
        }
    }

    private PropertieTab checkPropertie(String groupKey, String groupName) {
        PropertieTab propertieTab = propertieTabService.checkPropertie(groupKey, groupName);
        return propertieTab;
    }

    @ResponseBody
    @RequestMapping(value = "/updatePropertie.json", method = RequestMethod.POST)
    public Map updaePropertie(@RequestBody PropertieTabVO propertieVO) {
        int id = propertieVO.getId();
        PropertieTab p = propertieTabService.selectById(id + "");
        if (p != null) {
            if (!propertieVO.getGroupKey().equals(p.getGroupKey())) {
                String groupKey = propertieVO.getGroupKey();
                String groupName = propertieVO.getGroupName();
                PropertieTab propertieTab = checkPropertie(groupKey, groupName);
                if (propertieTab != null) {
                    return JsonUtil.toJsonError(ErrorEnum.ERROR_31430);
                }
            }
        }

        int updete = propertieTabService.updaePropertie(propertieVO);
        if (updete > 0) {
            return JsonUtil.toJsonSuccess("成功", updete);
        } else {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_21103);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deletePropertie.json", method = RequestMethod.POST)
    public Map deletePropertie(@RequestBody Map map) {
        String id = (String) map.get("id");
        int delete = propertieTabService.deletePropertie(id);
        if (delete > 0) {
            return JsonUtil.toJsonSuccess("成功", delete);
        } else {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_21114);
        }
    }


}
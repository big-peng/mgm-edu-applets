package com.yy.server.controller.back;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.yy.server.common.ErrorEnum;
import com.yy.server.model.AppProductType;
import com.yy.server.service.AppProductTypeService;
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

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/console/producttype")
public class AppProductTypeController extends BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AppProductTypeService appProductTypeService;

    /**
     * 获取productType列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list.json", method = RequestMethod.POST)
    public Map list(@RequestBody Map map) {
        String appId = map.get("appId").toString();
        if (StringUtils.isEmpty(appId)) {
            logger.info("获取产品类别信息：reqParam is error, reqParam={}", JSONObject.toJSONString(map));
            return JsonUtil.toJsonError(ErrorEnum.ERROR_26001);
        }

        AppProductType appProductType = new AppProductType();
        appProductType.setAppId(appId);
        Object productTypeIdObj = map.get("productTypeId");
        String productTypeId = productTypeIdObj != null ? productTypeIdObj.toString() : "";
        if (!StringUtils.isEmpty(productTypeId)) {
            appProductType.setId(Integer.parseInt(productTypeId));
        }
        appProductType.setState(true);
        List<AppProductType> list = appProductTypeService.getAppProductTypeList(appProductType);
        return JsonUtil.toJsonSuccess("获取列表成功", list);
    }

    /**
     * 新增productType
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/add.json", method = RequestMethod.POST)
    public Map add(@RequestBody AppProductType vo) throws Exception {
        int result = appProductTypeService.addAppProductType(vo);
        if (result > 0) {
            return JsonUtil.toJsonSuccess("新增数据成功", result);
        } else {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31433);
        }
    }

    /**
     * 修改productType
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update.json", method = RequestMethod.POST)
    public Map update(@RequestBody AppProductType vo) {
        if (vo == null || vo.getId() == null || vo.getId() == 0) {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_26001);
        }
        int result = appProductTypeService.updateAppProductType(vo);
        if (result > 0) {
            return JsonUtil.toJsonSuccess("更新数据成功", result);
        } else {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31432);
        }
    }

    /**
     * 删除productType
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/del.json", method = RequestMethod.POST)
    public Map del(@RequestBody AppProductType vo) {
        if (vo == null || vo.getId() == null || vo.getId() == 0) {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_26001);
        }
        int result = appProductTypeService.delAppProductType(vo);
        if (result > 0) {
            return JsonUtil.toJsonSuccess("更新数据成功", result);
        } else {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31432);
        }
    }

    /**
     * 查看productType详情
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/detail.json", method = RequestMethod.POST)
    public Map detail(@RequestBody Map<String, Object> map) {
        String appId = map.get("appId").toString();
        String id = map.get("id").toString();
        if (StringUtils.isEmpty(appId) || StringUtils.isEmpty(id)) {
            logger.info("获取产品信息：reqParam is error, reqParam={}", JSONObject.toJSONString(map));
        }
        AppProductType appProductType = new AppProductType();
        appProductType.setId(Integer.parseInt(id));
        AppProductType appProductTypeQueryVo = appProductTypeService.queryOneAppProductType(appProductType);
        return JsonUtil.toJsonSuccess("获取成功", appProductTypeQueryVo);
    }

    /**
     * 获取产品类别信息
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getList.json", method = RequestMethod.POST)
    public Map productTypeList(@RequestBody Map<String, Object> map) {
        AppProductType appProductType = new AppProductType();
        String appId = map.get("appId").toString();
        if (StringUtils.isEmpty(appId)) {
            logger.info("获取产品类别信息：reqParam is error, reqParam={}", JSONObject.toJSONString(map));
            return JsonUtil.toJsonError(ErrorEnum.ERROR_26001);
        }
        appProductType.setAppId(appId);
        List<AppProductType> list = appProductTypeService.getAppProductTypeList(appProductType);
        List<AppProductType> respList = Lists.newArrayList();
        for (AppProductType appProductTypeTemp : list) {
            AppProductType appProductTypeNew = new AppProductType();
            appProductTypeNew.setId(appProductTypeTemp.getId());
            appProductTypeNew.setName(appProductTypeTemp.getName());
            appProductTypeNew.setImgUrl(appProductTypeTemp.getImgUrl());
            respList.add(appProductTypeNew);
        }
        return JsonUtil.toJsonSuccess("获取产品类别信息成功", respList);
    }

}
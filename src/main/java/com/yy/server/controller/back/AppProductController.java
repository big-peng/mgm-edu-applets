package com.yy.server.controller.back;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.yy.server.common.ErrorEnum;
import com.yy.server.model.AppProduct;
import com.yy.server.model.view.AppProductVO;
import com.yy.server.service.AppProductService;
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


@Controller
@RequestMapping(value = "/console/product")
public class AppProductController extends BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AppProductService appProductService;

    /**
     * 获取产品列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list.json", method = RequestMethod.POST)
    public Map list(@RequestBody AppProductVO vo) {
        if (StringUtils.isEmpty(vo.getAppId())) {
            logger.info("获取产品信息：reqParam is error, reqParam={}", JSONObject.toJSONString(vo));
        }

        if (StringUtils.isEmpty(vo.getState())) {
            vo.setState(true);
        }
        HashMap<String, Object> list = appProductService.getProductList(vo);
        return JsonUtil.toJsonSuccess("获取列表成功", list);
    }

    /**
     * 新增product
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/add.json", method = RequestMethod.POST)
    public Map add(@RequestBody AppProductVO vo) throws Exception {
        int insert = appProductService.addProductVO(vo);
        if (insert > 0) {
            return JsonUtil.toJsonSuccess("新增数据成功", insert);
        } else {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31433);
        }
    }

    /**
     * 修改product
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update.json", method = RequestMethod.POST)
    public Map update(@RequestBody AppProductVO vo) {
        if (vo == null || vo.getId() == null || vo.getId() == 0) {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_26001);
        }
        int update = appProductService.updateProductVO(vo);
        if (update > 0) {
            return JsonUtil.toJsonSuccess("更新数据成功", update);
        } else {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31432);
        }
    }

    /**
     * 删除product
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/del.json", method = RequestMethod.POST)
    public Map delete(@RequestBody AppProductVO vo) {
        if (vo == null || vo.getId() == null || vo.getId() == 0) {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_26001);
        }
        int update = appProductService.deleteProductVO(vo);
        if (update > 0) {
            return JsonUtil.toJsonSuccess("更新数据成功", update);
        } else {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31432);
        }
    }


    /**
     * 查看product详情
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/detail.json", method = RequestMethod.POST)
    public Map detail(@RequestBody Map<String, Object> map) {
        String id = map.get("id").toString();
        String appId = map.get("appId").toString();
        if (StringUtils.isEmpty(appId) || StringUtils.isEmpty(id)) {
            logger.info("获取产品信息：reqParam is error, reqParam={}", JSONObject.toJSONString(map));
        }
        AppProduct appProduct = new AppProduct();
        appProduct.setId(Integer.parseInt(id));
        Map respMap = appProductService.queryOneAppProductMap(appProduct);
        return JsonUtil.toJsonSuccess("获取成功", respMap);
    }

    /**
     * 获取产品列表信息
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getList.json", method = RequestMethod.POST)
    public Map getList(@RequestBody Map<String, Object> map) {
        String appId = map.get("appId").toString();
        String productTypeId = map.get("productTypeId").toString();
        if (StringUtils.isEmpty(appId) || StringUtils.isEmpty(productTypeId)) {
            logger.info("获取产品信息：reqParam is error, reqParam={}", JSONObject.toJSONString(map));
            return JsonUtil.toJsonError(ErrorEnum.ERROR_26001);
        }
        AppProduct appProduct = new AppProduct();
        appProduct.setAppId(appId);
        appProduct.setProductTypeId(Integer.valueOf(productTypeId));

        List<AppProduct> list = appProductService.getAppProductList(appProduct);
        List<AppProduct> respList = Lists.newArrayList();
        for (AppProduct appProductTemp : list) {
            AppProduct appProductNew = new AppProduct();
            appProductNew.setId(appProductTemp.getId());
            appProductNew.setName(appProductTemp.getName());
            appProductNew.setPrice(appProductTemp.getPrice());
            appProductNew.setImgUrl(appProductTemp.getImgUrl());
            appProductNew.setRemark(appProductTemp.getRemark());
            respList.add(appProductNew);
        }
        return JsonUtil.toJsonSuccess("获取信息成功", respList);
    }


}
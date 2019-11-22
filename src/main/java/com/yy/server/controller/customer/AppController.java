package com.yy.server.controller.customer;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yy.server.common.ErrorEnum;
import com.yy.server.controller.customer.base.CusBaseController;
import com.yy.server.model.*;
import com.yy.server.model.view.AppNoticeVO;
import com.yy.server.model.view.AppProductTypeVO;
import com.yy.server.service.*;
import com.yy.server.util.JsonUtil;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer/app/common")
public class AppController extends CusBaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AppBannerService appBannerService;

    @Autowired
    private AppShowSkinService appShowSkinService;

    @Autowired
    private AppProductTypeService appProductTypeService;

    @Autowired
    private AppProductService appProductService;

    @Resource
    private AppShopService shopService;

    /**
     * 获取幻灯片信息
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/slideList.json", method = RequestMethod.POST)
    public Map slideList(@RequestBody Map<String, Object> map) {
        DeviceInfo deviceInfo = (DeviceInfo) SecurityUtils.getSubject().getPrincipal();
        if (deviceInfo == null) {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_21099);
        }
        String appId = map.get("appId").toString();
        if (StringUtils.isEmpty(appId)) {
            logger.info("皮肤照片更新：reqParam is error, reqParam={}", JSONObject.toJSONString(map));
            return JsonUtil.toJsonError(ErrorEnum.ERROR_26001);
        }
        AppBanner appBanner = new AppBanner();
        appBanner.setAppId(appId);
        return JsonUtil.toJsonSuccess("获取成功", appBannerService.getAppBanners(appBanner));
    }


    /**
     * 获取产品所有信息
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/myProductTypeList.json", method = RequestMethod.POST)
    public Map myProductTypeList(@RequestBody Map<String, Object> map) {
        DeviceInfo deviceInfo = (DeviceInfo) SecurityUtils.getSubject().getPrincipal();
//        if (deviceInfo == null) {
//            return JsonUtil.toJsonError(ErrorEnum.ERROR_21099);
//        }
        AppProductType appProductType = new AppProductType();
        String appId = map.get("appId").toString();
        if (StringUtils.isEmpty(appId)) {
            logger.info("获取产品所有信息：reqParam is error, reqParam={}", JSONObject.toJSONString(map));
            return JsonUtil.toJsonError(ErrorEnum.ERROR_26001);
        }
        appProductType.setAppId(appId);
        List<AppProductType> list = appProductTypeService.getAppProductTypeList(appProductType);

        //1.产品类别数据获取
        List<AppProductTypeVO> respList = Lists.newArrayList();
        for (AppProductType appProductTypeTemp : list) {
            AppProductTypeVO appProductTypeNew = new AppProductTypeVO();
            appProductTypeNew.setId(appProductTypeTemp.getId());
            appProductTypeNew.setName(appProductTypeTemp.getName());
            appProductTypeNew.setImgUrl(appProductTypeTemp.getImgUrl());
            appProductTypeNew.setOrderId(appProductTypeTemp.getOrderId());

            List<Map> respProductList = Lists.newArrayList();
            AppProduct appProduct = new AppProduct();
            appProduct.setAppId(appId);
            appProduct.setProductTypeId(appProductTypeTemp.getId());
            List<AppProduct> productlist = appProductService.getAppProductList(appProduct);
            for (AppProduct appProductTemp : productlist) {
                AppProduct appProductVo = new AppProduct();
                appProductVo.setAppId(appId);
                appProductVo.setId(appProductTemp.getId());
                Map productMap = appProductService.queryOneAppProductMap(appProductVo);
                if (productMap != null) {
                    respProductList.add(productMap);
                }
            }
            appProductTypeNew.setProducts(respProductList);
            respList.add(appProductTypeNew);
        }


        //2.幻灯片数据获取
        AppBanner appBanner = new AppBanner();
        appBanner.setAppId(appId);
        List bannerList = appBannerService.getAppBanners(appBanner);

        //3.皮肤数据获取
        AppShowSkin appShowSkinQueryVo = new AppShowSkin();
        appShowSkinQueryVo.setAppId(appId);
        List showSkinList = appShowSkinService.getAppShowSkinByPage(appShowSkinQueryVo);

        Map respMap = Maps.newHashMap();
        respMap.put("productList", respList);
        respMap.put("bannerList", bannerList);
        respMap.put("showSkinList", showSkinList);
        return JsonUtil.toJsonSuccess("获取信息成功", respMap);
    }


    /**
     * 获取产品类别信息
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/productTypeList.json", method = RequestMethod.POST)
    public Map productTypeList(@RequestBody Map<String, Object> map) {
        DeviceInfo deviceInfo = (DeviceInfo) SecurityUtils.getSubject().getPrincipal();
//        if (deviceInfo == null) {
//            return JsonUtil.toJsonError(ErrorEnum.ERROR_21099);
//        }
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


    /**
     * 获取产品列表信息
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/productList.json", method = RequestMethod.POST)
    public Map productList(@RequestBody Map<String, Object> map) {
        DeviceInfo deviceInfo = (DeviceInfo) SecurityUtils.getSubject().getPrincipal();
//        if (deviceInfo == null) {
//            return JsonUtil.toJsonError(ErrorEnum.ERROR_21099);
//        }
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
        return JsonUtil.toJsonSuccess("获取列表信息成功", respList);
    }


    /**
     * 获取产品信息
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/product.json", method = RequestMethod.POST)
    public Map product(@RequestBody Map<String, Object> map) {
        DeviceInfo deviceInfo = (DeviceInfo) SecurityUtils.getSubject().getPrincipal();
//        if (deviceInfo == null) {
//            return JsonUtil.toJsonError(ErrorEnum.ERROR_21099);
//        }
        AppProduct appProduct = new AppProduct();
        String appId = map.get("appId").toString();
        String productId = map.get("productId").toString();
        if (StringUtils.isEmpty(appId) || StringUtils.isEmpty(productId)) {
            logger.info("获取产品信息：reqParam is error, reqParam={}", JSONObject.toJSONString(map));
            return JsonUtil.toJsonError(ErrorEnum.ERROR_26001);
        }
        appProduct.setAppId(appId);
        appProduct.setId(Integer.valueOf(productId));
        Map respMap = appProductService.queryOneAppProductMap(appProduct);
        return JsonUtil.toJsonSuccess("获取信息成功", respMap);
    }

    /**
     * 查看获取皮肤
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getShowSkin.json", method = RequestMethod.POST)
    public Map getShowSkin(@RequestBody Map<String, Object> map) {
        String appId = map.get("appId").toString();
        String type = map.get("type").toString();
        if (StringUtils.isEmpty(appId) || StringUtils.isEmpty(type)) {
            logger.info("皮肤照片更新：reqParam is error, reqParam={}", JSONObject.toJSONString(map));
            return JsonUtil.toJsonError(ErrorEnum.ERROR_26001);
        }
        AppShowSkin appShowSkinQueryVo = new AppShowSkin();
        appShowSkinQueryVo.setAppId(appId);
        appShowSkinQueryVo.setType(type);
        AppShowSkin appShowSkin = appShowSkinService.queryOneAppShowSkin(appShowSkinQueryVo);
        Map respData = Maps.newHashMap();
        if (appShowSkin != null) {
            respData.put("id", appShowSkin.getId());
            respData.put("imgUrl", appShowSkin.getImgUrl());
        }
        return JsonUtil.toJsonSuccess("获取成功", respData);
    }

    /**
     * 查看获取皮肤
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getShowSkinList.json", method = RequestMethod.POST)
    public Map getShowSkinList(@RequestBody Map<String, Object> map) {
        String appId = map.get("appId").toString();
        if (StringUtils.isEmpty(appId)) {
            logger.info("皮肤照片更新：reqParam is error, reqParam={}", JSONObject.toJSONString(map));
            return JsonUtil.toJsonError(ErrorEnum.ERROR_26001);
        }
        AppShowSkin appShowSkinQueryVo = new AppShowSkin();
        appShowSkinQueryVo.setAppId(appId);
        List<AppShowSkin> appShowSkinList = appShowSkinService.getAppShowSkinByPage(appShowSkinQueryVo);
        Map respData = Maps.newHashMap();
        if (appShowSkinList != null && appShowSkinList.size() > 0) {
            respData.put("showSkinList", appShowSkinList);
        }
        return JsonUtil.toJsonSuccess("获取成功", respData);
    }


    /**
     * 获得店铺信息
     *
     * @param map
     * @return
     */
    @RequestMapping("getShop.json")
    public Map getShop(@RequestBody Map<String, Object> map) {
        String appId = map.get("appId").toString();
        if (StringUtils.isEmpty(appId)) {
            logger.info("店铺信息获取：reqParam is error, reqParam={}", JSONObject.toJSONString(map));
            return JsonUtil.toJsonError(ErrorEnum.ERROR_26001);
        }
        AppShop vo = new AppShop();
        vo.setZid(appId);
        vo.setState(true);
        AppShop appShop = shopService.getOneAppShop(vo);
        Map respMap = Maps.newHashMap();
        if (appShop != null) {
            respMap.put("shopName", appShop.getShopName());
            respMap.put("appId", appShop.getZid());
            respMap.put("qrcode1TitleUrl", appShop.getQrcode1TitleUrl());
            respMap.put("qrcode2TitleUrl", appShop.getQrcode2TitleUrl());
            respMap.put("extCodeTitleUrl", appShop.getExtCodeTitleUrl());
            respMap.put("qrcode1ImageUrl", appShop.getQrcode1ImageUrl());
            respMap.put("qrcode2ImageUrl", appShop.getQrcode2ImageUrl());
            respMap.put("extCodeImageUrl", appShop.getExtCodeImageUrl());
            return JsonUtil.toJsonSuccess("获得信息成功", respMap);
        } else {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_26005);
        }
    }
}

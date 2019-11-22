package com.yy.server.controller.edu.applets;

import com.yy.server.model.AppBanner;
import com.yy.server.model.dto.AppletsBannerListDTO;
import com.yy.server.service.AppletsBannerService;
import com.yy.server.util.JsonUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author ChenXiangpeng
 * @ClassName AppletsBannerController
 * @date：2019/11/19
 * @version: V1.0.0
 * @description：小程序轮播图Controller
 */
@RestController
@RequestMapping("/applets/banner")
@Api(tags = "心理咨询登记")
@Slf4j
public class AppletsBannerController {
    @Resource
    private AppletsBannerService appletsBannerService;

    @PostMapping("/list")
    public Map getBannerList(@RequestBody @Valid AppletsBannerListDTO appletsBannerListDTO){
        List appletsBanners = appletsBannerService.getAppletsBanners(appletsBannerListDTO);
        return JsonUtil.toJsonSuccess("成功",appletsBanners);
    }
}

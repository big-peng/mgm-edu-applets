package com.yy.server.service;

import com.yy.server.mapper.BannerMapper;
import com.yy.server.model.AppBanner;
import com.yy.server.model.dto.AppletsBannerListDTO;
import com.yy.server.model.view.AppletsBannerListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ChenXiangpeng
 * @ClassName AppletsBannerService
 * @date：2019/11/19
 * @version: V1.0.0
 * @description：
 */
@Service
@Slf4j
public class AppletsBannerService {
    @Resource
    private BannerMapper bannerMapper;

    public List getAppletsBanners(AppletsBannerListDTO appletsBannerListDTO) {
        appletsBannerListDTO.setAppId("10086");
        List<AppBanner> banners = bannerMapper.getAppletsBanners(appletsBannerListDTO);
        List<AppletsBannerListVO> resultList = banners.stream().map(e -> {
            AppletsBannerListVO appletsBannerListVO = new AppletsBannerListVO();
            appletsBannerListVO.setId(e.getId());
            appletsBannerListVO.setImgUrl(e.getImgUrl());
            appletsBannerListVO.setName(e.getName());
            appletsBannerListVO.setRemark(e.getRemark());
            return appletsBannerListVO;
        }).collect(Collectors.toList());
        return resultList;
    }
}

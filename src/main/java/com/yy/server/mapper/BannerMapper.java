package com.yy.server.mapper;

import com.yy.server.model.AppBanner;
import com.yy.server.model.dto.AppletsBannerListDTO;
import com.yy.server.model.view.AppBannerVO;
import com.yy.server.util.MyMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BannerMapper extends MyMapper<AppBanner> {

    List<AppBannerVO> getBannerList(AppBannerVO vo);

    List<AppBanner> getAppBanners(AppBanner appBanner);

    List<AppBanner> getAppletsBanners(AppletsBannerListDTO appletsBannerListDTO);
}
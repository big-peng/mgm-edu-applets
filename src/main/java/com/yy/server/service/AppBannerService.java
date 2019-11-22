package com.yy.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yy.server.common.BusiConstant;
import com.yy.server.mapper.BannerMapper;
import com.yy.server.model.AppBanner;
import com.yy.server.model.view.AppBannerVO;
import com.yy.server.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AppBannerService {

    @Autowired
    public BannerMapper bannerMapper;

    public HashMap<String, Object> getBannerList(AppBannerVO vo) {
        if (vo.getPage() != null && vo.getRows() != null) {
            PageHelper.startPage(vo.getPage(), vo.getRows());
        }
        List<AppBannerVO> banners = bannerMapper.getBannerList(vo);
        PageInfo pageinfo = new PageInfo(banners);
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageCount", pageinfo.getTotal());
        map.put("pageList", banners);
        return map;
    }

    public int addBanner(AppBannerVO vo) throws Exception {
        AppBanner appBanner = new AppBanner();
        appBanner.setAppId(vo.getAppId());
        appBanner.setName(vo.getName());
        appBanner.setImgUrl(vo.getImgUrl());
        appBanner.setOrderId(vo.getOrderId());
        appBanner.setRemark(vo.getRemark());
        appBanner.setClickType(1);
        appBanner.setClickId("mmy");
        appBanner.setState(BusiConstant.STATE_1);
        String date = DateUtils.getDateTime();
        appBanner.setCreateTime(date);
        appBanner.setUpdateTime(date);
        int insert = bannerMapper.insert(appBanner);
        return insert;
    }

    public int updBanner(AppBannerVO vo) {
        Weekend<AppBanner> weekend = Weekend.of(AppBanner.class);
        WeekendCriteria<AppBanner, Object> criteria = weekend.weekendCriteria();
        criteria.andEqualTo(AppBanner::getId, vo.getId());
        AppBanner appBanner = bannerMapper.selectOneByExample(weekend);
        appBanner.setAppId(vo.getAppId());
        appBanner.setName(vo.getName());
        appBanner.setImgUrl(vo.getImgUrl());
        appBanner.setOrderId(vo.getOrderId());
        appBanner.setClickType(vo.getClickType());
        appBanner.setClickId(vo.getClickId());
        appBanner.setRemark(vo.getRemark());
        appBanner.setState(vo.getState());
        appBanner.setUpdateTime(DateUtils.getDateTime());
        int i = bannerMapper.updateByPrimaryKeySelective(appBanner);
        return i;
    }

    public int deleteBanner(AppBannerVO vo) {
        AppBanner appBannerQueryVO = bannerMapper.selectByPrimaryKey(vo.getId());
        if (appBannerQueryVO != null) {
            AppBanner appBanner = new AppBanner();
            appBanner.setId(appBannerQueryVO.getId());
            //appBanner.setState(BusiConstant.STATE_0);
            //appBanner.setUpdateTime(DateUtils.getDateTime());
            int i = bannerMapper.deleteByPrimaryKey(appBanner);
            return i;
        }
        return 0;
    }

    public AppBanner bannerDetail(Map map) {
        Weekend<AppBanner> weekend = Weekend.of(AppBanner.class);
        WeekendCriteria<AppBanner, Object> criteria = weekend.weekendCriteria();
        criteria.andEqualTo(AppBanner::getId, map.get("id"));
        return bannerMapper.selectOneByExample(weekend);

    }

    public List getAppBanners(AppBanner appBanner) {
        List<AppBanner> banners = bannerMapper.getAppBanners(appBanner);
        return banners;
    }


}

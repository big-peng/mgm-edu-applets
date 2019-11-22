package com.yy.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yy.server.mapper.AppShopMapper;
import com.yy.server.model.Admin;
import com.yy.server.model.AppShop;
import com.yy.server.model.AppShowSkin;
import com.yy.server.model.view.AppShopVO;
import com.yy.server.model.view.PropertieVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;
import tk.mybatis.orderbyhelper.OrderByHelper;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional
public class AppShopService {


    @Resource
    private AppShopMapper appShopMapper;

    @Autowired
    private AppShowSkinService appShowSkinService;

    /**
     * 新增app店铺信息
     *
     * @param appShop
     * @return
     */
    public int insertAppShop(AppShop appShop) {
        AppShowSkin appShowSkinQuery = new AppShowSkin();
        appShowSkinQuery.setAppId("10086");
        List<AppShowSkin> getAppShowSkinByPage = appShowSkinService.getAppShowSkinByPage(appShowSkinQuery);
        for (AppShowSkin appShowSkinTemp : getAppShowSkinByPage) {
            appShowSkinTemp.setId(null);
            appShowSkinTemp.setAppId(appShop.getZid());
            appShowSkinTemp.setCreateTime(new Date());
            appShowSkinTemp.setUpdateTime(new Date());
            appShowSkinService.addAppShowSkin(appShowSkinTemp);
        }
        return appShopMapper.insert(appShop);
    }


    /**
     * 修改app店铺信息
     *
     * @param appShop
     * @return
     */
    public int updateAppShop(AppShop appShop) {
        return appShopMapper.updateByPrimaryKeySelective(appShop);
    }

    /**
     * 查询app店铺信息
     *
     * @param appShop
     * @return
     */
    public AppShop getOneAppShop(AppShop appShop) {
        return appShopMapper.selectOne(appShop);
    }


    /**
     * 查询app店铺信息
     *
     * @param id
     * @return
     */
    public AppShop getAppShopById(int id) {
        return appShopMapper.selectByPrimaryKey(id);
    }


    /**
     * 分页查询app店铺信息
     *
     * @param appShopVo
     * @return
     */
    public PageInfo<AppShop> queryAppShopByPage(AppShopVO appShopVo) {
        if (appShopVo.getPage() != null && appShopVo.getRows() != null) {
            PageHelper.startPage(appShopVo.getPage(), appShopVo.getRows());
        }
        OrderByHelper.orderBy("created_time desc");
        List<AppShopVO> dataList = appShopMapper.selectAppShopList(appShopVo);
        PageInfo pageinfo = new PageInfo(dataList);
        return pageinfo;
    }


    public List<HashMap<String, Object>> getAppShopName(Admin admin) {
        List<HashMap<String, Object>> hashMap = appShopMapper.selectShopName(admin);
        return hashMap;
    }

    public int updateStateAppShop(AppShop appShop) {
        if (appShop != null && appShop.getId() > 0) {
            AppShop delShop = new AppShop();
            delShop.setId(appShop.getId());
            delShop.setState(false);
            delShop.setUpdateTime(new Date());
            return appShopMapper.updateByPrimaryKeySelective(delShop);
        }
        return 0;
    }


    public Boolean checkShopName(AppShop appShop) {
        Weekend<AppShop> weekend = Weekend.of(AppShop.class);
        WeekendCriteria<AppShop, Object> criteria = weekend.weekendCriteria();
        criteria.andEqualTo(AppShop::getShopName, appShop.getShopName());
        criteria.andEqualTo(AppShop::getState, Boolean.TRUE);
        AppShop appShopQueryVo = appShopMapper.selectOneByExample(weekend);
        return (appShopQueryVo == null ? true : false);
    }


    public PageInfo<AppShop> queryShop() {
        PageHelper.startPage(1, 10000);
        OrderByHelper.orderBy("created_time desc");
        AppShop appShop = new AppShop();
        appShop.setState(true);
        List<AppShop> members = appShopMapper.select(appShop);
        PageInfo pageinfo = new PageInfo(members);
        return pageinfo;
    }


    public List<Map<String, Object>> getPropertiesMap(PropertieVO vo) {
        return appShopMapper.getPropertiesMap(vo);
    }

    public List<Map<String, String>> getDownLoadMap(PropertieVO vo) {
        List<AppShop> res = appShopMapper.selectByZid(vo.getAppId());
        if (!StringUtils.isEmpty(res.get(0).getDownloadPage())) {
            List<Map<String, String>> list = new ArrayList<>();
            Map<String, String> resultMap = new HashMap<String, String>() {
                {
                    put("group_key", "downloadpage");
                    put("group_value", res.get(0).getDownloadPage());
                }
            };
            list.add(resultMap);
            return list;
        }
        return null;
    }


    public AppShop checkShopZid(String zid) {
        List<AppShop> appShops = appShopMapper.selectByZid(zid);
        if (appShops == null || appShops.size() == 0) {
            return null;
        } else {
            return appShops.get(0);
        }
    }
}

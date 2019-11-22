package com.yy.server.service;

import com.yy.server.mapper.AppShowSkinMapper;
import com.yy.server.model.AppShowSkin;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class AppShowSkinService {

    @Resource
    private AppShowSkinMapper appShowSkinMapper;

    public boolean addAppShowSkin(AppShowSkin appShowSkin) {
        return appShowSkinMapper.insertSelective(appShowSkin) > 0;
    }


    public boolean updateAppShowSkin(AppShowSkin appShowSkin) {
        return appShowSkinMapper.updateByPrimaryKey(appShowSkin) > 0;
    }

    public AppShowSkin queryOneAppShowSkin(AppShowSkin appShowSkin) {
        return appShowSkinMapper.selectOne(appShowSkin);
    }

    public int getAppShowSkinCount(AppShowSkin appShowSkin) {
        return appShowSkinMapper.selectCount(appShowSkin);
    }

    public List<AppShowSkin> getAppShowSkinByPage(AppShowSkin appShowSkin) {
        return appShowSkinMapper.selectByRowBounds(appShowSkin, new RowBounds(0, 9999));
    }

}

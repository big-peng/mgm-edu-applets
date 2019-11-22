package com.yy.server.service;

import com.yy.server.mapper.AppProductTypeMapper;
import com.yy.server.model.AppProductType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AppProductTypeService {

    @Resource
    private AppProductTypeMapper appProductTypeMapper;

    public int addAppProductType(AppProductType appProductType) {
        appProductType.setState(true);
        appProductType.setCreateTime(new Date());
        appProductType.setUpdateTime(new Date());
        return appProductTypeMapper.insertSelective(appProductType);
    }


    public int updateAppProductType(AppProductType appProductType) {
        appProductType.setState(true);
        appProductType.setUpdateTime(new Date());
        return appProductTypeMapper.updateByPrimaryKey(appProductType);
    }

    public int delAppProductType(AppProductType appProductType) {
        if (appProductType != null && appProductType.getId() > 0) {
            AppProductType delEntity = new AppProductType();
            delEntity.setId(appProductType.getId());
            delEntity.setState(false);
            delEntity.setUpdateTime(new Date());
            return appProductTypeMapper.updateByPrimaryKeySelective(delEntity);
        }
        return 0;

    }

    public AppProductType queryOneAppProductType(AppProductType appProductType) {
        return appProductTypeMapper.selectOne(appProductType);
    }

    public int getAppProductTypeCount(AppProductType appProductType) {
        return appProductTypeMapper.selectCount(appProductType);
    }

    public List<AppProductType> getAppProductTypeList(AppProductType appProductType) {
        return appProductTypeMapper.getProductTypes(appProductType);
    }


}

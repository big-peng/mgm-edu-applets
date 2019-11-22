package com.yy.server.mapper;

import com.yy.server.model.AppProductType;
import com.yy.server.util.MyMapper;

import java.util.List;

public interface AppProductTypeMapper extends MyMapper<AppProductType> {
    List<AppProductType> getProductTypes(AppProductType appProductType);
}
package com.yy.server.mapper;

import com.yy.server.model.CompanyInfo;
import com.yy.server.util.MyMapper;
import org.springframework.stereotype.Component;

@Component
public interface CompanyInfoMapper extends MyMapper<CompanyInfo> {
    void companyInfoMapper(String companyName);
}
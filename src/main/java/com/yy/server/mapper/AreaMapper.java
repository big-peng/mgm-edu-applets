package com.yy.server.mapper;

import com.yy.server.model.Area;
import com.yy.server.util.MyMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AreaMapper extends MyMapper<Area> {


    List<Area> getprovinces();

    List<Area> getcities(String provinceId);


}

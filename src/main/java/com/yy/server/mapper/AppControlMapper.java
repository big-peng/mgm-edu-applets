package com.yy.server.mapper;


import com.yy.server.model.AppControl;
import com.yy.server.model.view.AppConSelVO;
import com.yy.server.util.MyMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppControlMapper extends MyMapper<AppControl> {
    List<AppConSelVO> getAppControls(AppConSelVO appConSelVO);

    @Select("SELECT MAX(app_version_code) FROM app_control WHERE app_type =#{appType}")
    Integer getVersionCount(String appType);
}
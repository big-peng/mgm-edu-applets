package com.yy.server.mapper;


import com.yy.server.model.AppHelp;
import com.yy.server.model.view.AppHelpSelVO;
import com.yy.server.util.MyMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppHelpMapper extends MyMapper<AppHelp> {
    @Select("select help_id,title from app_help t where t.state=#{state} and t.app_type=#{appType} and t.app_id=#{appId} order by show_order ,create_time ")
    @Results({
            @Result(property = "helpId", column = "help_id")
    })
    List<AppHelp> getAppHelpByType(AppHelp vo);

    List<AppHelp> getAppHelps(AppHelpSelVO appHelpSelVO);

    int updateByHelpId(AppHelp vo);
}
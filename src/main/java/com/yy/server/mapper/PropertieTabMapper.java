package com.yy.server.mapper;

import com.yy.server.model.PropertieTab;
import com.yy.server.model.view.PropertieTabVO;
import com.yy.server.util.MyMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PropertieTabMapper extends MyMapper<PropertieTab> {
    @Select("SELECT * FROM `propertie_tab` WHERE group_name = #{groupname} ")
    @Results({
            @Result(property = "groupName", column = "group_name"),
            @Result(property = "groupKey", column = "group_key"),
            @Result(property = "groupValue", column = "group_value"),
            @Result(property = "keyDesc", column = "key_desc")
    })
    List<PropertieTab> selectByGroup(String groupname);


    PropertieTab checkPropertie(String groupKey, String groupName);

    @Delete("delete from propertie_tab where id=#{id} ")
    int deleteByid(String id);

    List<Map> filterAboutManagement(PropertieTabVO propertieTabVO);
}
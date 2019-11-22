package com.yy.server.mapper;

import com.yy.server.model.Menu;
import com.yy.server.model.RoleMenu;
import com.yy.server.util.MyMapper;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleMenuMapper extends MyMapper<RoleMenu> {
    List<Menu> getByRoleId(String roleId);

    @Delete("DELETE FROM role_menu WHERE role_id = #{roleId} ")
    int deleteByRoleId(String roleId);
}
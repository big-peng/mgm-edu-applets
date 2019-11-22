package com.yy.server.mapper;

import com.yy.server.model.AdminRole;
import com.yy.server.model.Role;
import com.yy.server.util.MyMapper;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminRoleMapper extends MyMapper<AdminRole> {

    List<Role> getRoleByAdminId(String admin_id);

    @Delete("DELETE FROM admin_role WHERE admin_id = #{adminId}")
    void deleteByAdminId(String adminId);
}
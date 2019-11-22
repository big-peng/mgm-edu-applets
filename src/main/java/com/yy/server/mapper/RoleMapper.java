package com.yy.server.mapper;

import com.yy.server.model.Role;
import com.yy.server.util.MyMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface RoleMapper extends MyMapper<Role> {

    List<Role> getRoles(HashMap<String, Object> selectmap);

    List<Role> selectRoles();
}
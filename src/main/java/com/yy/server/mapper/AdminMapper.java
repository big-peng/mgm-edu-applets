package com.yy.server.mapper;

import com.yy.server.model.Admin;
import com.yy.server.model.view.AdminSelVO;
import com.yy.server.util.MyMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminMapper extends MyMapper<Admin> {
    List<Admin> getAdminList(AdminSelVO admin);

    Admin selectByUsername(String username);

    Admin selectByAdminId(String adminId);
}
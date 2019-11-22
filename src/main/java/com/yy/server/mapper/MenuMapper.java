package com.yy.server.mapper;

import com.yy.server.model.Menu;
import com.yy.server.util.MyMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface MenuMapper extends MyMapper<Menu> {
    Set<String> getALLMenuCode();

    Set<String> findMenuCodeByUserId(String adminId);

    List<Menu> getALLMenu();
}
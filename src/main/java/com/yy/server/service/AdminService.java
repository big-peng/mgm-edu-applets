package com.yy.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.yy.server.mapper.AdminMapper;
import com.yy.server.mapper.AdminRoleMapper;
import com.yy.server.model.Admin;
import com.yy.server.model.AdminRole;
import com.yy.server.model.Role;
import com.yy.server.model.view.AdminCreateVO;
import com.yy.server.model.view.AdminSelVO;
import com.yy.server.util.DateUtils;
import com.yy.server.util.PasswordUtil;
import com.yy.server.util.UuidUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class AdminService {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;


//    public Admin findByUsername(String userName) {
//        Weekend<Admin> weekend = Weekend.of(Admin.class);
//        WeekendCriteria<Admin, Object> criteria = weekend.weekendCriteria();
//        criteria.andEqualTo(Admin::getUsername, userName);
//        return adminMapper.selectOneByExample(weekend);
//    }

    public Admin findByUsername(String userName, String appId) {
        Weekend<Admin> weekend = Weekend.of(Admin.class);
        WeekendCriteria<Admin, Object> criteria = weekend.weekendCriteria();
        criteria.andEqualTo(Admin::getUsername, userName);
        criteria.andEqualTo(Admin::getAppId, appId);
        return adminMapper.selectOneByExample(weekend);
    }

    public Admin findByUsernameIn(AdminCreateVO vo) {
        Admin admin = new Admin();
        Weekend<Admin> weekend = Weekend.of(Admin.class);
        WeekendCriteria<Admin, Object> criteria = weekend.weekendCriteria();
        criteria.andEqualTo(Admin::getUsername, vo.getUsername());
        criteria.andEqualTo(Admin::getAppId, admin.getAppId());
        return adminMapper.selectOneByExample(weekend);
    }


    public HashMap<String, Object> getMembers(AdminSelVO adminSelVO) {
        if (adminSelVO.getPage() != null && adminSelVO.getRows() != null) {
            PageHelper.startPage(adminSelVO.getPage(), adminSelVO.getRows());
        }
        List<Admin> members = adminMapper.getAdminList(adminSelVO);
        PageInfo pageinfo = new PageInfo(members);
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageCount", pageinfo.getTotal());
        map.put("members", members);
        return map;
    }

    public HashMap<String, Object> getMemberById(String admin_id) {
        Admin ad = new Admin();
        Weekend<Admin> weekend = Weekend.of(Admin.class);
        WeekendCriteria<Admin, Object> criteria = weekend.weekendCriteria();
        criteria.andEqualTo(Admin::getAdminId, admin_id);
        criteria.andEqualTo(Admin::getAppId, ad.getAppId());
        Admin admin = adminMapper.selectOneByExample(weekend);
        HashMap<String, Object> map = new HashMap<>();
        if (admin != null) {
            List<Role> roles = adminRoleMapper.getRoleByAdminId(admin_id);
            admin.setRoles(roles);
        }
        map.put("admin", admin);
        return map;
    }

    public int updateMemberBy(AdminCreateVO adminCreateVO) {
        Admin admin = new Admin();
        if (adminCreateVO.getState() != null) {
            admin.setState(adminCreateVO.getState());
        }
        admin.setAdminId(adminCreateVO.getAdmin_id());
        admin.setUpdatedTime(DateUtils.getDateTime());
        int i = adminMapper.updateByPrimaryKeySelective(admin);
        return i;
    }


    public int delMember(AdminCreateVO adminCreateVO) {
        Admin admin = new Admin();
        admin.setAdminId(adminCreateVO.getAdmin_id());
        int i = adminMapper.deleteByPrimaryKey(admin);
        return i;
    }


    @Transactional
    public int createAdmin(AdminCreateVO adminvo) {
        String roles = adminvo.getRoles();
        Admin admin = new Admin();
        String uuid = UuidUtil.getUUID();
        admin.setAdminId(uuid);
        admin.setAppId(adminvo.getAppId());
        if (!StringUtils.isEmpty(adminvo.getUsername()))
            admin.setUsername(adminvo.getUsername());
        if (adminvo.getState() != null)
            admin.setState(adminvo.getState());

        if (adminvo.getRealName() != null)
            admin.setRealName(adminvo.getRealName());

        if (adminvo.getCareerType() != null && adminvo.getCareerType() > 0)
            admin.setCareerType(adminvo.getCareerType());

        if (adminvo.getSex() != null)
            admin.setSex(adminvo.getSex());

        if (adminvo.getSystem() != null)
            admin.setIsSystem(adminvo.getSystem());
        else
            admin.setIsSystem(false);

        if (adminvo.getEmployee() != null)
            admin.setEmployee(adminvo.getEmployee());
        else
            admin.setEmployee(false);

        if (adminvo.getShowPad() != null)
            admin.setShowPad(adminvo.getShowPad());
        else
            admin.setShowPad(false);


        if (!StringUtils.isEmpty(adminvo.getPasswd())) {
            admin.setSalt(UuidUtil.getUUID());
            String adminPwd = PasswordUtil.createAdminPwd(adminvo.getPasswd(), admin.getSalt());
            admin.setPassword(adminPwd);
        } else {
            admin.setEmployee(true);
        }

        if (!StringUtils.isEmpty(adminvo.getRemark()))
            admin.setRemark(adminvo.getRemark());
        admin.setCreatedTime(DateUtils.getDateTime());
        admin.setUpdatedTime(DateUtils.getDateTime());

        int insert = 0;
        try {
            insert = adminMapper.insert(admin);
            if (roles != null) {
                String[] split = roles.split(",");
                for (String roleId : split) {
                    AdminRole adminRole = new AdminRole();
                    adminRole.setAdminId(uuid);
                    adminRole.setRoleId(roleId);
                    insert = adminRoleMapper.insert(adminRole);
                }
            }
        } catch (Exception e) {
            logger.error("create user error,e=", e);
            throw new RuntimeException("创建用户失败");
        }
        return insert;
    }

    @Transactional
    public int updateMember(AdminCreateVO adminvo) {
        int update = 0;
        Admin admin = adminMapper.selectByAdminId(adminvo.getAdminId());
        admin.setAppId(adminvo.getAppId());
        admin.setAdminId(adminvo.getAdminId());
        if (!StringUtils.isEmpty(adminvo.getUsername()))
            admin.setUsername(adminvo.getUsername());
        if (adminvo.getState() != null)
            admin.setState(adminvo.getState());

        if (!StringUtils.isEmpty(adminvo.getPasswd())) {
            String adminPwd = PasswordUtil.createAdminPwd(adminvo.getPasswd(), admin.getSalt());
            admin.setPassword(adminPwd);
        }
        if (!StringUtils.isEmpty(adminvo.getUsername()))
            admin.setRemark(adminvo.getRemark());

        if (adminvo.getRealName() != null)
            admin.setRealName(adminvo.getRealName());

        if (adminvo.getCareerType() != null && adminvo.getCareerType() > 0)
            admin.setCareerType(adminvo.getCareerType());

        if (adminvo.getSex() != null)
            admin.setSex(adminvo.getSex());

        if (adminvo.getSystem() != null)
            admin.setIsSystem(adminvo.getSystem());
        else
            admin.setIsSystem(false);

        if (adminvo.getEmployee() != null)
            admin.setEmployee(adminvo.getEmployee());
        else
            admin.setEmployee(false);

        if (adminvo.getShowPad() != null)
            admin.setShowPad(adminvo.getShowPad());
        else
            admin.setShowPad(false);

        if (admin.getEmployee()) {
            try {
                update = adminMapper.updateByPrimaryKeySelective(admin);
            } catch (Exception e) {
                throw new RuntimeException("admin修改异常");
            }
        } else {
            String roles = adminvo.getRoles();
            String[] split = roles.split(",");
            if (roles != null && roles.length() > 0) {
                try {
                    update = adminMapper.updateByPrimaryKeySelective(admin);
                    adminRoleMapper.deleteByAdminId(admin.getAdminId());
                    for (String roleId : split) {
                        AdminRole adminRole = new AdminRole();
                        adminRole.setAdminId(admin.getAdminId());
                        adminRole.setRoleId(roleId);
                        update = adminRoleMapper.insert(adminRole);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("admin修改异常");
                }

            }
        }
        return update;
    }

    public int rePassWord(AdminCreateVO adminvo) {
        Admin admin = adminMapper.selectByAdminId(adminvo.getAdminId());
        admin.setPassword(PasswordUtil.createAdminPwd("123456", admin.getSalt()));
        admin.setUpdatedTime(DateUtils.getDateTime());
        int i = adminMapper.updateByPrimaryKeySelective(admin);
        return i;

    }

    public Admin selectByUsername(String username) {
        Admin admin = adminMapper.selectByUsername(username);
        return admin;
    }

    public List<Role> getRoles(String adminId) {
        List<Role> roles = adminRoleMapper.getRoleByAdminId(adminId);
        return roles;
    }


    public List getStaffListByCareerType(AdminSelVO vo) {
        List<AdminSelVO> staffList = Lists.newArrayList();
        List<Admin> members = adminMapper.getAdminList(vo);
        for (Admin admin : members) {
            AdminSelVO adminSelVO = new AdminSelVO();
            adminSelVO.setAdminId(admin.getAdminId());
            adminSelVO.setUsername(admin.getUsername());
            adminSelVO.setRealName(admin.getRealName());
            adminSelVO.setCareerType(admin.getCareerType());
            staffList.add(adminSelVO);
        }
        return staffList;
    }

    public Admin getAdminById(String admin_id) {
        Admin admin = adminMapper.selectByAdminId(admin_id);
        return admin;
    }
}

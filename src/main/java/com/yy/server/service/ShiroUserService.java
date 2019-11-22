package com.yy.server.service;

import com.yy.server.mapper.AdminMapper;
import com.yy.server.mapper.CustomerMapper;
import com.yy.server.mapper.DeviceInfoMapper;
import com.yy.server.mapper.MenuMapper;
import com.yy.server.model.Admin;
import com.yy.server.model.Customer;
import com.yy.server.model.DeviceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

import java.util.List;
import java.util.Set;

@Service
public class ShiroUserService {

    @Autowired
    public AdminMapper adminMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private DeviceInfoMapper deviceInfoMapper;


    public Admin findByUsername(String userName, String appId) {
        Weekend<Admin> weekend = Weekend.of(Admin.class);
        WeekendCriteria<Admin, Object> criteria = weekend.weekendCriteria();
        criteria.andEqualTo(Admin::getUsername, userName);
        criteria.andEqualTo(Admin::getAppId, appId);
        return adminMapper.selectOneByExample(weekend);
    }


    public Set<String> getAllMenuCode() {
        return menuMapper.getALLMenuCode();
    }

    public Set<String> findMenuCodeByUserId(String adminId) {
        return menuMapper.findMenuCodeByUserId(adminId);
    }

    /**
     * 账号和appId 查询账号
     *
     * @param account
     * @param appId
     * @return
     */
    public Customer findByAccount(String account, String appId) {
        Weekend<Customer> weekend = Weekend.of(Customer.class);
        WeekendCriteria<Customer, Object> criteria = weekend.weekendCriteria();
        criteria.andEqualTo(Customer::getAccount, account);
        criteria.andEqualTo(Customer::getAppId, appId);
        return customerMapper.selectOneByExample(weekend);
    }


    /**
     * 设备uuid查询账号
     *
     * @param uuid
     * @return
     */
    public DeviceInfo findByUUID(String uuid) {
        Weekend<DeviceInfo> weekend = Weekend.of(DeviceInfo.class);
        WeekendCriteria<DeviceInfo, Object> criteria = weekend.weekendCriteria();
        criteria.andEqualTo(DeviceInfo::getDeviceUuid, uuid);
        return deviceInfoMapper.selectOneByExample(weekend);
    }


    /**
     * 设备名称查询账号
     *
     * @param deviceName
     * @return
     */
    public DeviceInfo findByName(String deviceName) {
//        Weekend<DeviceInfo> weekend = Weekend.of(DeviceInfo.class);
//        WeekendCriteria<DeviceInfo, Object> criteria = weekend.weekendCriteria();
//        criteria.andEqualTo(DeviceInfo::getDeviceName, deviceName);
//        return deviceInfoMapper.selectOneByExample(weekend);

        //criteria.andBetween(DeviceInfo::getBeginTime, deviceName);
        //criteria.(DeviceInfo::getBeginTime, deviceName);
        DeviceInfo deviceInfoQueryVo = new DeviceInfo();
        deviceInfoQueryVo.setDeviceName(deviceName);
        List<DeviceInfo> list = deviceInfoMapper.getListByDeviceName(deviceInfoQueryVo);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}

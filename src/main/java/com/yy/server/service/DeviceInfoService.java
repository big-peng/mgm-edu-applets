package com.yy.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yy.server.mapper.DeviceInfoMapper;
import com.yy.server.model.DeviceInfo;
import com.yy.server.model.view.DeviceInfoVO;
import com.yy.server.util.PasswordUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class DeviceInfoService {

    @Resource
    private DeviceInfoMapper deviceInfoMapper;


    public HashMap<String, Object> getDeviceInfoList(DeviceInfoVO vo) {
        if (vo.getPage() != null && vo.getRows() != null) {
            PageHelper.startPage(vo.getPage(), vo.getRows());
        }
        List<DeviceInfoVO> products = deviceInfoMapper.getDeviceInfoList(vo);
        PageInfo pageinfo = new PageInfo(products);
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageCount", pageinfo.getTotal());
        map.put("pageList", products);
        return map;
    }


    public int addDeviceInfo(DeviceInfo deviceInfo) {
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        Date nowDate = new Date();
        String password = PasswordUtil.createCustomPwd(deviceInfo.getDevicePwd(), salt);
        deviceInfo.setDevicePwd(password);
        deviceInfo.setSalt(salt);
        deviceInfo.setState((byte) 1);
        deviceInfo.setCreatedTime(nowDate);
        deviceInfo.setUpdatedTime(nowDate);
        deviceInfoMapper.insertSelective(deviceInfo);
        return deviceInfo.getId();
    }

    public int updateDeviceInfo(DeviceInfo deviceInfo) {
        if(StringUtils.isBlank(deviceInfo.getDevicePwd())){
            deviceInfo.setDevicePwd(null);
        }
        deviceInfo.setUpdatedTime(new Date());
        return deviceInfoMapper.updateByPrimaryKeySelective(deviceInfo);
    }

    public int deleteDeviceInfo(DeviceInfo deviceInfo) {
        if(deviceInfo.getId() > 0){
            DeviceInfo delEntity = new DeviceInfo();
            delEntity.setId(deviceInfo.getId());
            delEntity.setState((byte)0);
            return deviceInfoMapper.updateByPrimaryKeySelective(delEntity);
        }
        return 0;
    }

    public int updateDeviceInfoPwd(DeviceInfo deviceInfo) {
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        String password = PasswordUtil.createCustomPwd(deviceInfo.getDevicePwd(), salt);
        DeviceInfo upDeviceInfo = new DeviceInfo();
        upDeviceInfo.setId(deviceInfo.getId());
        upDeviceInfo.setDevicePwd(password);
        upDeviceInfo.setSalt(salt);
        upDeviceInfo.setUpdatedTime(new Date());
        return deviceInfoMapper.updateByPrimaryKeySelective(upDeviceInfo);
    }

    public DeviceInfo queryOneDeviceInfo(DeviceInfo deviceInfo) {
        return deviceInfoMapper.selectOne(deviceInfo);
    }

    public Boolean checkDeviceInfoName(DeviceInfo deviceInfo) {
        Weekend<DeviceInfo> weekend = Weekend.of(DeviceInfo.class);
        WeekendCriteria<DeviceInfo, Object> criteria = weekend.weekendCriteria();
        criteria.andEqualTo(DeviceInfo::getDeviceName, deviceInfo.getDeviceName());
        criteria.andEqualTo(DeviceInfo::getState, deviceInfo.getState());
        DeviceInfo deviceInfoVo = deviceInfoMapper.selectOneByExample(weekend);
        return (deviceInfoVo == null ? true : false);
    }

    public int getDeviceInfoCount(DeviceInfo deviceInfo) {
        return deviceInfoMapper.selectCount(deviceInfo);
    }

    public List<DeviceInfo> getDeviceInfoList(DeviceInfo deviceInfo) {
        return deviceInfoMapper.select(deviceInfo);
    }
}

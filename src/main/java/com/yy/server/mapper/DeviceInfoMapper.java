package com.yy.server.mapper;

import com.yy.server.model.DeviceInfo;
import com.yy.server.model.view.DeviceInfoVO;
import com.yy.server.util.MyMapper;

import java.util.List;

public interface DeviceInfoMapper extends MyMapper<DeviceInfo> {

    List<DeviceInfoVO> getDeviceInfoList(DeviceInfoVO vo);

    List<DeviceInfo> getListByDeviceName(DeviceInfo vo);
}
package com.yy.server.controller.back;

import com.alibaba.fastjson.JSONObject;
import com.yy.server.common.BusiConstant;
import com.yy.server.common.ErrorEnum;
import com.yy.server.model.DeviceInfo;
import com.yy.server.model.view.DeviceInfoVO;
import com.yy.server.service.DeviceInfoService;
import com.yy.server.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value = "/console/deviceinfo")
public class DeviceInfoController extends BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DeviceInfoService deviceInfoService;

    /**
     * 获取设备信息列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list.json", method = RequestMethod.POST)
    public Map getDeviceInfoList(@RequestBody DeviceInfoVO vo) {
        if (StringUtils.isEmpty(vo.getState())) {
            vo.setState(Byte.valueOf("1"));
        }
        HashMap<String, Object> list = deviceInfoService.getDeviceInfoList(vo);
        return JsonUtil.toJsonSuccess("获取列表成功", list);
    }

    /**
     * 新增deviceinfo
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/add.json", method = RequestMethod.POST)
    public Map addDeviceInfo(@RequestBody DeviceInfo vo) {
        logger.info("请求参数：reqParams map={}", JSONObject.toJSONString(vo));
        Boolean isNoHave = deviceInfoService.checkDeviceInfoName(vo);
        if (!isNoHave) {
            return JsonUtil.toJsonSuccess("账号不能重复", false);
        }
        int insert = deviceInfoService.addDeviceInfo(vo);
        if (insert > 0) {
            return JsonUtil.toJsonSuccess("新增数据成功", insert);
        } else {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31433);
        }
    }

    /**
     * 修改deviceinfo
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update.json", method = RequestMethod.POST)
    public Map updDeviceInfo(@RequestBody DeviceInfo vo) {
        logger.info("请求参数：reqParams map={}", JSONObject.toJSONString(vo));
        DeviceInfo deviceInfoOne = new DeviceInfo();
        deviceInfoOne.setId(vo.getId());
        deviceInfoOne.setState((byte)BusiConstant.STATE_1);
        DeviceInfo deviceInfo1 = deviceInfoService.queryOneDeviceInfo(deviceInfoOne);
        if (deviceInfo1 != null && !deviceInfo1.getDeviceName().equals(vo.getDeviceName())) {
            Boolean m = deviceInfoService.checkDeviceInfoName(vo);
            if (!m) {
                return JsonUtil.toJsonSuccess("账号不能重复", false);
            }
        }
        int update = deviceInfoService.updateDeviceInfo(vo);
        if (update > 0) {
            return JsonUtil.toJsonSuccess("更新数据成功", update);
        } else {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31432);
        }
    }


    /**
     * 删除deviceinfo
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/del.json", method = RequestMethod.POST)
    public Map delete(@RequestBody DeviceInfo vo) {
        logger.info("请求参数：reqParams map={}", JSONObject.toJSONString(vo));
        int update = deviceInfoService.deleteDeviceInfo(vo);
        if (update > 0) {
            return JsonUtil.toJsonSuccess("更新数据成功", update);
        } else {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31432);
        }
    }

    /**
     * 更改设备密码
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/changePwd.json", method = RequestMethod.POST)
    public Map changePwd(@RequestBody DeviceInfo vo) {
        logger.info("请求参数：reqParams map={}", JSONObject.toJSONString(vo));
        DeviceInfo deviceInfoOne = new DeviceInfo();
        deviceInfoOne.setId(vo.getId());
        deviceInfoOne.setState((byte)BusiConstant.STATE_1);
        DeviceInfo deviceInfo = deviceInfoService.queryOneDeviceInfo(deviceInfoOne);
        if (deviceInfo == null) {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31423);
        }
        int update = deviceInfoService.updateDeviceInfoPwd(vo);
        if (update > 0) {
            return JsonUtil.toJsonSuccess("更新数据成功", update);
        } else {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31432);
        }
    }

    /**
     * 查看deviceinfo详情
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/detail.json", method = RequestMethod.POST)
    public Map detail(@RequestBody DeviceInfo vo) {
        if (StringUtils.isEmpty(vo.getId())) {
            logger.info("获取设备信息：reqParam is error, reqParam={}", JSONObject.toJSONString(vo));
        }
        return JsonUtil.toJsonSuccess("获取成功", deviceInfoService.queryOneDeviceInfo(vo));
    }

}
package com.yy.server.controller.back;

import com.alibaba.fastjson.JSONObject;
import com.yy.server.common.ErrorEnum;
import com.yy.server.model.AppShowSkin;
import com.yy.server.model.view.AppVO;
import com.yy.server.service.AppShowSkinService;
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

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/console/showskin")
public class AppShowSkinController extends BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AppShowSkinService appShowSkinService;

    /**
     * 获取所有的皮肤
     *
     * @param vo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list.json", method = RequestMethod.POST)
    public Map list(@RequestBody AppVO vo) {
        if (StringUtils.isEmpty(vo.getAppId())) {
            setGlobalAdminAppId(vo);
        }
        AppShowSkin appShowSkin = new AppShowSkin();
        appShowSkin.setAppId(vo.getAppId());
        List<AppShowSkin> list = appShowSkinService.getAppShowSkinByPage(appShowSkin);
        return JsonUtil.toJsonSuccess("获取列表成功", list);
    }

    /**
     * 修改店铺皮肤
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update.json", method = RequestMethod.POST)
    public Map update(@RequestBody Map<String, Object> map) {
        if (map.isEmpty()) {
            logger.error("皮肤照片更新：参数为空！");
        }
        String id = map.get("id").toString();
        String imgUrl = map.get("imgUrl").toString();
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(imgUrl)) {
            logger.info("皮肤照片更新：reqParam is error, reqParam={}", JSONObject.toJSONString(map));
        }
        AppShowSkin appShowSkin = new AppShowSkin();
        appShowSkin.setId(Integer.parseInt(id));
        AppShowSkin appShowSkinQueryVo = appShowSkinService.queryOneAppShowSkin(appShowSkin);
        appShowSkinQueryVo.setImgUrl(imgUrl);
        boolean isSuccess = appShowSkinService.updateAppShowSkin(appShowSkinQueryVo);
        if (isSuccess) {
            return JsonUtil.toJsonSuccess("更新数据成功", isSuccess);
        } else {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31432);
        }
    }

}
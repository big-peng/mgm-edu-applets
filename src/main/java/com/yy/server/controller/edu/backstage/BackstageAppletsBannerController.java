package com.yy.server.controller.edu.backstage;

import com.yy.server.common.ErrorEnum;
import com.yy.server.controller.back.BaseController;
import com.yy.server.model.AppBanner;
import com.yy.server.model.view.AppBannerVO;
import com.yy.server.service.AppBannerService;
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
@RequestMapping(value = "/backstage/banner")
public class BackstageAppletsBannerController extends BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AppBannerService appBannerService;

    /**
     * 获取banner列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Map getBannerList(@RequestBody AppBannerVO vo) {
        vo.setAppId("10086");
        if (StringUtils.isEmpty(vo.getAppId())) {
            setGlobalAdminAppId(vo);
        }
        //vo.setState(1);
        HashMap<String, Object> list = appBannerService.getBannerList(vo);
        return JsonUtil.toJsonSuccess("获取列表成功", list);
    }

    /**
     * 新增banner
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map addBanner(@RequestBody AppBannerVO vo) throws Exception {
        vo.setAppId("10086");
        int insert = appBannerService.addBanner(vo);
        if (insert > 0) {
            return JsonUtil.toJsonSuccess("新增数据成功", insert);
        } else {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31433);
        }
    }

    /**
     * 修改banner
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Map updBanner(@RequestBody AppBannerVO vo) {
        int update = appBannerService.updBanner(vo);
        if (update > 0) {
            return JsonUtil.toJsonSuccess("更新数据成功", update);
        } else {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31432);
        }
    }


    /**
     * 删除banner
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Map delete(@RequestBody AppBannerVO vo) {
        int update = appBannerService.deleteBanner(vo);
        if (update > 0) {
            return JsonUtil.toJsonSuccess("删除数据成功", update);
        } else {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31432);
        }
    }

    /**
     * 查看banner详情
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public Map bannerDetail(@RequestBody Map<String, Object> map) {
        AppBanner appBanner = appBannerService.bannerDetail(map);
        return JsonUtil.toJsonSuccess("获取成功", appBanner);
    }

}
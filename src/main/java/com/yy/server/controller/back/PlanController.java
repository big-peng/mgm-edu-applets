package com.yy.server.controller.back;

import com.alibaba.fastjson.JSONObject;
import com.yy.server.common.BusiConstant;
import com.yy.server.common.ErrorEnum;
import com.yy.server.model.Admin;
import com.yy.server.model.PmPlan;
import com.yy.server.model.view.PmPlanVO;
import com.yy.server.service.PmPlanService;
import com.yy.server.util.JsonUtil;
import org.apache.shiro.SecurityUtils;
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
@RequestMapping(value = "/console/plan")
public class PlanController extends BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PmPlanService pmPlanService;

    /**
     * 获取plan列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list.json", method = RequestMethod.POST)
    public Map list(@RequestBody PmPlanVO vo) {
        String appId = vo.getAppId().toString();
        if (StringUtils.isEmpty(appId)) {
            logger.info("获取产品类别信息：reqParam is error, reqParam={}", JSONObject.toJSONString(vo));
        }

        if (StringUtils.isEmpty(vo.getAppId())) {
            logger.info("获取产品信息：reqParam is error, reqParam={}", JSONObject.toJSONString(vo));
        }

        if (StringUtils.isEmpty(vo.getState())) {
            vo.setState((byte)1);
        }
        vo.setDelFlag(BusiConstant.DELFLAG_N);
        HashMap<String, Object> list = pmPlanService.getPmPlanList(vo);
        return JsonUtil.toJsonSuccess("获取列表成功", list);
    }

    /**
     * 新增entity
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/add.json", method = RequestMethod.POST)
    public Map add(@RequestBody PmPlan vo) throws Exception {
        int result = pmPlanService.addPmPlan(vo);
        if (result > 0) {
            return JsonUtil.toJsonSuccess("新增数据成功", result);
        } else {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31433);
        }
    }

    /**
     * 修改entity
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update.json", method = RequestMethod.POST)
    public Map update(@RequestBody PmPlan vo) {
        int result = pmPlanService.updatePmPlan(vo);
        if (result > 0) {
            return JsonUtil.toJsonSuccess("更新数据成功", result);
        } else {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31432);
        }
    }

    /**
     * 删除entity
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/del.json", method = RequestMethod.POST)
    public Map delete(@RequestBody PmPlan vo) {
        int result = pmPlanService.deletePmPlan(vo);
        if (result > 0) {
            return JsonUtil.toJsonSuccess("更新数据成功", result);
        } else {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_31432);
        }
    }


    /**
     * 查看entity详情
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/detail.json", method = RequestMethod.POST)
    public Map detail(@RequestBody Map<String, Object> map) {
        String id = map.get("id").toString();
        if (StringUtils.isEmpty(id)) {
            logger.info("获取信息：reqParam is error, reqParam={}", JSONObject.toJSONString(map));
        }
        PmPlan pmPlan = pmPlanService.planDetail(map);
        return JsonUtil.toJsonSuccess("获取成功", pmPlan);
    }

    /**
     * 下拉框数据获取
     *
     * @return
     */
    @RequestMapping("getList.json")
    @ResponseBody
    public Map getSelectList() {
        PmPlan pmPlan = new PmPlan();
        Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
        if(admin != null){
            pmPlan.setAppId(admin.getAppId());
            return JsonUtil.toJsonSuccess("下拉框列表", pmPlanService.getPmPlans(pmPlan));
        }
        return JsonUtil.toJsonError(ErrorEnum.ERROR_26001);
    }

}
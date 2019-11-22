package com.yy.server.controller.back;

import com.alibaba.fastjson.JSONObject;
import com.yy.server.common.BusiConstant;
import com.yy.server.common.ErrorEnum;
import com.yy.server.model.PmPlanFactor;
import com.yy.server.model.view.PmPlanFactorVO;
import com.yy.server.service.PmPlanFactorService;
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
@RequestMapping(value = "/console/planfactor")
public class PlanFactorController extends BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PmPlanFactorService pmPlanFactorService;

    /**
     * 获取productType列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list.json", method = RequestMethod.POST)
    public Map list(@RequestBody PmPlanFactorVO vo) {
        if (StringUtils.isEmpty(vo.getAppId())) {
            logger.info("获取产品信息：reqParam is error, reqParam={}", JSONObject.toJSONString(vo));
            return JsonUtil.toJsonError(ErrorEnum.ERROR_26001);
        }

        if (StringUtils.isEmpty(vo.getState())) {
            vo.setState((byte) 1);
        }
        vo.setDelFlag(BusiConstant.DELFLAG_N);
        HashMap<String, Object> list = pmPlanFactorService.getPlanFactorList(vo);
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
    public Map add(@RequestBody PmPlanFactorVO vo) throws Exception {
        int result = pmPlanFactorService.addPmPlanFactorVO(vo);
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
    public Map update(@RequestBody PmPlanFactorVO vo) {
        int result = pmPlanFactorService.updatePmPlanFactorVO(vo);
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
    public Map delete(@RequestBody PmPlanFactorVO vo) {
        int result = pmPlanFactorService.delPmPlanFactorVO(vo);
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
            return JsonUtil.toJsonError(ErrorEnum.ERROR_26001);
        }
        PmPlanFactor pmPlanFactor = new PmPlanFactor();
        pmPlanFactor.setId(Integer.parseInt(id));
        PmPlanFactorVO pmPlanVO = pmPlanFactorService.queryOnePmPlanFactorVO(pmPlanFactor);
        return JsonUtil.toJsonSuccess("获取成功", pmPlanVO);
    }

}
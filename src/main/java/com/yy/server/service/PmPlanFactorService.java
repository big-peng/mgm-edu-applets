package com.yy.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.yy.server.common.BusiConstant;
import com.yy.server.mapper.PmPlanFactorMapper;
import com.yy.server.mapper.PmPlanFactorValueMapper;
import com.yy.server.model.PmPlanFactor;
import com.yy.server.model.PmPlanFactorValue;
import com.yy.server.model.view.PmPlanFactorVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class PmPlanFactorService {

    @Resource
    private PmPlanFactorMapper pmPlanFactorMapper;

    @Resource
    private PmPlanFactorValueMapper pmPlanFactorValueMapper;

    public HashMap<String, Object> getPlanFactorList(PmPlanFactorVO vo) {
        if (vo.getPage() != null && vo.getRows() != null) {
            PageHelper.startPage(vo.getPage(), vo.getRows());
        }
        List<PmPlanFactorVO> products = pmPlanFactorMapper.getDataList(vo);
        PageInfo pageinfo = new PageInfo(products);
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageCount", pageinfo.getTotal());
        map.put("pageList", pageinfo.getList());
        return map;
    }

    public int addPmPlanFactorVO(PmPlanFactorVO appPmPlanFactorVO) {
        Date nowDate = new Date();

        PmPlanFactor appPmPlanFactor = new PmPlanFactor();
        appPmPlanFactor.setName(appPmPlanFactorVO.getName());
        appPmPlanFactor.setComType(appPmPlanFactorVO.getComType());
        appPmPlanFactor.setIsRemarkInput(appPmPlanFactorVO.getIsRemarkInput());
        appPmPlanFactor.setRemarkInputStr(appPmPlanFactorVO.getRemarkInputStr());
        appPmPlanFactor.setOrderId(appPmPlanFactorVO.getOrderId());
        appPmPlanFactor.setAppId(appPmPlanFactorVO.getAppId());
        appPmPlanFactor.setPlanId(appPmPlanFactorVO.getPlanId());
        appPmPlanFactor.setDelFlag(BusiConstant.DELFLAG_N);
        appPmPlanFactor.setTipsDesc(appPmPlanFactorVO.getTipsDesc());
        appPmPlanFactor.setState((byte) 1);
        appPmPlanFactor.setCreateTime(nowDate);
        appPmPlanFactor.setUpdateTime(nowDate);
        pmPlanFactorMapper.insertSelective(appPmPlanFactor);

        int factorId = appPmPlanFactor.getId();
        List<PmPlanFactorValue> planFactorValueList = appPmPlanFactorVO.getPlanFactorValueList();
        if (planFactorValueList != null && planFactorValueList.size() > 0) {
            for (PmPlanFactorValue planFactorValueTemp : planFactorValueList) {
                planFactorValueTemp.setState((byte) 1);
                planFactorValueTemp.setDelFlag(BusiConstant.DELFLAG_N);
                planFactorValueTemp.setFactorId(factorId);
                planFactorValueTemp.setAppId(appPmPlanFactorVO.getAppId());
                planFactorValueTemp.setCreateTime(nowDate);
                planFactorValueTemp.setUpdateTime(nowDate);
            }
            pmPlanFactorValueMapper.insertList(planFactorValueList);
        }
        return factorId;
    }

    public int updatePmPlanFactorVO(PmPlanFactorVO pmPlanFactorVO) {
        Date nowDate = new Date();
        PmPlanFactor pmPlanFactor = new PmPlanFactor();
        pmPlanFactor.setId(pmPlanFactorVO.getId());
        pmPlanFactor.setName(pmPlanFactorVO.getName());
        pmPlanFactor.setComType(pmPlanFactorVO.getComType());
        pmPlanFactor.setIsRemarkInput(pmPlanFactorVO.getIsRemarkInput());
        pmPlanFactor.setRemarkInputStr(pmPlanFactorVO.getRemarkInputStr());
        pmPlanFactor.setTipsDesc(pmPlanFactorVO.getTipsDesc());
        pmPlanFactor.setPlanId(pmPlanFactorVO.getPlanId());
        pmPlanFactor.setOrderId(pmPlanFactorVO.getOrderId());
        pmPlanFactor.setState((byte) 1);
        pmPlanFactor.setUpdateTime(nowDate);
        List<PmPlanFactorValue> planFactorValueList = pmPlanFactorVO.getPlanFactorValueList();
        List<PmPlanFactorValue> insertPlanFactorValueList = Lists.newArrayList();
        if (planFactorValueList != null && planFactorValueList.size() > 0) {
            for (PmPlanFactorValue planFactorValueTemp : planFactorValueList) {
                PmPlanFactorValue planFactorValue = new PmPlanFactorValue();
                planFactorValue.setShowLabel(planFactorValueTemp.getShowLabel());
                planFactorValue.setShowValue(planFactorValueTemp.getShowValue());
                planFactorValue.setShowValueType(planFactorValueTemp.getShowValueType());
                planFactorValue.setIsHaveDefaultValue(planFactorValueTemp.getIsHaveDefaultValue());
                planFactorValue.setDefaultValueType(planFactorValueTemp.getDefaultValueType());
                planFactorValue.setOrderId(planFactorValueTemp.getOrderId());
                planFactorValue.setState((byte) 1);
                planFactorValue.setDelFlag(BusiConstant.DELFLAG_N);
                planFactorValue.setFactorId(pmPlanFactorVO.getId());
                planFactorValue.setAppId(pmPlanFactorVO.getAppId());
                planFactorValue.setCreateTime(nowDate);
                planFactorValue.setUpdateTime(nowDate);
                insertPlanFactorValueList.add(planFactorValue);
            }
        }

        //值列表先删除，再增加
        pmPlanFactorValueMapper.updateDelFactorValuesByFactorId(pmPlanFactorVO.getId());
        pmPlanFactorValueMapper.insertList(insertPlanFactorValueList);
        int success = pmPlanFactorMapper.updateByPrimaryKeySelective(pmPlanFactor);
        return success;
    }

    public int delPmPlanFactorVO(PmPlanFactorVO pmPlanFactorVO) {
        PmPlanFactor pmPlanFactor = new PmPlanFactor();
        pmPlanFactor.setId(pmPlanFactorVO.getId());
        pmPlanFactor.setDelFlag(BusiConstant.DELFLAG_Y);
        //值列表先删除，再增加
        pmPlanFactorValueMapper.updateDelFactorValuesByFactorId(pmPlanFactorVO.getId());
        int success = pmPlanFactorMapper.updateByPrimaryKeySelective(pmPlanFactor);
        return success;
    }



    public boolean updatePmPlanFactor(PmPlanFactor pmPlanFactor) {
        return pmPlanFactorMapper.updateByPrimaryKey(pmPlanFactor) > 0;
    }

    public PmPlanFactor queryOnePmPlanFactor(PmPlanFactor pmPlanFactor) {
        return pmPlanFactorMapper.selectOne(pmPlanFactor);
    }

    public PmPlanFactorVO queryOnePmPlanFactorVO(PmPlanFactor pmPlanFactor) {
        PmPlanFactor pmPlanFactorQueryVo = pmPlanFactorMapper.selectOne(pmPlanFactor);
        if (pmPlanFactorQueryVo != null) {
            PmPlanFactorVO pmPlanFactorVO = new PmPlanFactorVO();
            pmPlanFactorVO.setId(pmPlanFactorQueryVo.getId());
            pmPlanFactorVO.setName(pmPlanFactorQueryVo.getName());
            pmPlanFactorVO.setComType(pmPlanFactorQueryVo.getComType());
            pmPlanFactorVO.setIsRemarkInput(pmPlanFactorQueryVo.getIsRemarkInput());
            pmPlanFactorVO.setRemarkInputStr(pmPlanFactorQueryVo.getRemarkInputStr());
            pmPlanFactorVO.setOrderId(pmPlanFactorQueryVo.getOrderId());
            pmPlanFactorVO.setPlanId(pmPlanFactorQueryVo.getPlanId());
            pmPlanFactorVO.setState(pmPlanFactorQueryVo.getState());
            pmPlanFactorVO.setDelFlag(pmPlanFactorQueryVo.getDelFlag());
            PmPlanFactorValue pmPlanFactorValue = new PmPlanFactorValue();
            pmPlanFactorValue.setFactorId(pmPlanFactorQueryVo.getId());
            List<PmPlanFactorValue> valuesList = pmPlanFactorValueMapper.getPmPlanFactorValues(pmPlanFactorValue);
            pmPlanFactorVO.setPlanFactorValueList(valuesList);
            return pmPlanFactorVO;
        }
        return null;
    }

    public List<PmPlanFactor> getPmPlanFactorList(PmPlanFactor pmPlanFactor) {
        return pmPlanFactorMapper.getPmPlanFactors(pmPlanFactor);
    }

    /**
     * 获取方案所有因子明细信息
     * @param pmPlanFactorVO
     * @return
     */
    public List<PmPlanFactorVO> getPmPlanFactorList(PmPlanFactorVO pmPlanFactorVO) {
        List<PmPlanFactorVO> factorVOList = pmPlanFactorMapper.getDataList(pmPlanFactorVO);
        for (PmPlanFactorVO pmPlanFactorTemp: factorVOList) {
            PmPlanFactorValue pmPlanFactorValue = new PmPlanFactorValue();
            pmPlanFactorValue.setFactorId(pmPlanFactorTemp.getId());
            List<PmPlanFactorValue> valuesList = pmPlanFactorValueMapper.getPmPlanFactorValues(pmPlanFactorValue);
            pmPlanFactorTemp.setPlanFactorValueList(valuesList);
        }
        return factorVOList;
    }

}

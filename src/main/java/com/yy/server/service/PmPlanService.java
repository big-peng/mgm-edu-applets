package com.yy.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yy.server.common.BusiConstant;
import com.yy.server.mapper.PmPlanFactorMapper;
import com.yy.server.mapper.PmPlanFactorValueMapper;
import com.yy.server.mapper.PmPlanMapper;
import com.yy.server.model.PmPlan;
import com.yy.server.model.PmPlanFactorValue;
import com.yy.server.model.view.PmPlanFactorVO;
import com.yy.server.model.view.PmPlanVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PmPlanService {

    @Resource
    private PmPlanMapper pmPlanMapper;

    @Resource
    private PmPlanFactorMapper pmPlanFactorMapper;

    @Resource
    private PmPlanFactorValueMapper pmPlanFactorValueMapper;

    public HashMap<String, Object> getPmPlanList(PmPlanVO vo) {
        if (vo.getPage() != null && vo.getRows() != null) {
            PageHelper.startPage(vo.getPage(), vo.getRows());
        }
        List<PmPlanVO> dataList = pmPlanMapper.getDataList(vo);
        PageInfo pageinfo = new PageInfo(dataList);
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageCount", pageinfo.getTotal());
        map.put("pageList", dataList);
        return map;
    }

    public int addPmPlan(PmPlan entity) {
        Date date = new Date();

        entity.setState((byte) 1);
        entity.setDelFlag("N");
        entity.setCreateTime(date);
        entity.setUpdateTime(date);
        return pmPlanMapper.insert(entity);
    }

    public int updatePmPlan(PmPlan vo) {
        Weekend<PmPlan> weekend = Weekend.of(PmPlan.class);
        WeekendCriteria<PmPlan, Object> criteria = weekend.weekendCriteria();
        criteria.andEqualTo(PmPlan::getId, vo.getId());
        PmPlan entity = pmPlanMapper.selectOneByExample(weekend);
        if (entity != null) {
            entity.setAppId(vo.getAppId());
            entity.setName(vo.getName());
            entity.setOrderId(vo.getOrderId());
            entity.setState(vo.getState());
            entity.setUpdateTime(new Date());
            return pmPlanMapper.updateByPrimaryKeySelective(entity);
        }
        return 0;
    }

    public int deletePmPlan(PmPlan vo) {
        Weekend<PmPlan> weekend = Weekend.of(PmPlan.class);
        WeekendCriteria<PmPlan, Object> criteria = weekend.weekendCriteria();
        criteria.andEqualTo(PmPlan::getId, vo.getId());
        PmPlan entity = pmPlanMapper.selectOneByExample(weekend);
        if (entity != null) {
            PmPlan delEntity = new PmPlan();
            delEntity.setId(entity.getId());
            delEntity.setDelFlag(BusiConstant.DELFLAG_Y);
            delEntity.setUpdateTime(new Date());
            return pmPlanMapper.updateByPrimaryKeySelective(delEntity);
        }
        return 0;
    }

    public PmPlan planDetail(Map map) {
        Weekend<PmPlan> weekend = Weekend.of(PmPlan.class);
        WeekendCriteria<PmPlan, Object> criteria = weekend.weekendCriteria();
        criteria.andEqualTo(PmPlan::getId, map.get("id"));
        return pmPlanMapper.selectOneByExample(weekend);
    }


    public PmPlan queryOnePmPlanFactor(PmPlan pmPlan) {
        return pmPlanMapper.selectOne(pmPlan);
    }


    /**
     * 获取方案的所有因子信息
     *
     * @param vo
     * @return
     */
    public PmPlanVO queryOnePmPlanFactorVO(PmPlanVO vo) {
        PmPlanVO pmPlanVO = null;
        List<PmPlanVO> dataList = pmPlanMapper.getDataList(vo);
        if (dataList != null && dataList.size() > 0) {
            pmPlanVO = dataList.get(0);
            PmPlanFactorVO pmPlanFactorVO = new PmPlanFactorVO();
            pmPlanFactorVO.setAppId(pmPlanVO.getAppId());
            pmPlanFactorVO.setPlanId(pmPlanVO.getId());
            pmPlanFactorVO.setState((byte) 1);
            List<PmPlanFactorVO> factorVOList = pmPlanFactorMapper.getDataList(pmPlanFactorVO);
            for (PmPlanFactorVO pmPlanFactorTemp : factorVOList) {
                PmPlanFactorValue pmPlanFactorValue = new PmPlanFactorValue();
                pmPlanFactorValue.setFactorId(pmPlanFactorTemp.getId());
                List<PmPlanFactorValue> valuesList = pmPlanFactorValueMapper.getPmPlanFactorValues(pmPlanFactorValue);
                pmPlanFactorTemp.setPlanFactorValueList(valuesList);
            }
            pmPlanVO.setPlanFactorVoList(factorVOList);
        }
        return pmPlanVO;
    }

    public List getPmPlans(PmPlan entity) {
        List<PmPlan> list = pmPlanMapper.getPmPlans(entity);
        return list;
    }

}

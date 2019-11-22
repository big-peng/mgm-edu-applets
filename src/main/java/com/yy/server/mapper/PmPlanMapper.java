package com.yy.server.mapper;

import com.yy.server.model.PmPlan;
import com.yy.server.model.view.PmPlanVO;
import com.yy.server.util.MyMapper;

import java.util.List;

public interface PmPlanMapper extends MyMapper<PmPlan> {

    List<PmPlanVO> getDataList(PmPlanVO vo);

    List<PmPlan> getPmPlans(PmPlan pmPlan);

}
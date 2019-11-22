package com.yy.server.mapper;

import com.yy.server.model.PmPlanFactor;
import com.yy.server.model.view.PmPlanFactorVO;
import com.yy.server.util.MyMapper;

import java.util.List;

public interface PmPlanFactorMapper extends MyMapper<PmPlanFactor> {

    List<PmPlanFactorVO> getDataList(PmPlanFactorVO vo);

    List<PmPlanFactor> getPmPlanFactors(PmPlanFactor entity);
}
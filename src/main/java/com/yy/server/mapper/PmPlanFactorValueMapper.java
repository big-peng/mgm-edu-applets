package com.yy.server.mapper;

import com.yy.server.model.PmPlanFactorValue;
import com.yy.server.util.MyMapper;

import java.util.List;

public interface PmPlanFactorValueMapper extends MyMapper<PmPlanFactorValue> {
    List<PmPlanFactorValue> getPmPlanFactorValues(PmPlanFactorValue pmPlanFactorValue);

    int updateDelFactorValuesByFactorId(int factorId);
}
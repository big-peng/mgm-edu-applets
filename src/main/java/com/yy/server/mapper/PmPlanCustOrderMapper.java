package com.yy.server.mapper;

import com.yy.server.model.PmPlanCustOrder;
import com.yy.server.model.view.CustDiagnoseOrderVO;
import com.yy.server.model.view.PmPlanCustOrderVO;
import com.yy.server.util.MyMapper;

import java.util.List;

public interface PmPlanCustOrderMapper extends MyMapper<PmPlanCustOrder> {
    List<PmPlanCustOrderVO> getDataList(PmPlanCustOrderVO vo);

    List<CustDiagnoseOrderVO> getCustDiagnoseOrdersByCustId(Integer custId);

    List<CustDiagnoseOrderVO> getCustManagePlanOrdersByCustId(Integer custId);

    List<CustDiagnoseOrderVO> getCustDiagnoseOrdersByParams(CustDiagnoseOrderVO vo);

    List<CustDiagnoseOrderVO> getCustDiagnosePlanOrdersByParams(CustDiagnoseOrderVO vo);

    List<PmPlanCustOrder> getPmPlanCustOrders(PmPlanCustOrder entity);
}
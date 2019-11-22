package com.yy.server.mapper;

import com.yy.server.model.PmPlanCustOrderDetailPh;
import com.yy.server.util.MyMapper;

import java.util.List;

public interface PmPlanCustOrderDetailPhMapper extends MyMapper<PmPlanCustOrderDetailPh> {
    List<PmPlanCustOrderDetailPh> getLastDataList(int custId);
    int updateDelOrderDetailPhsByOrderId(int orderId);
}
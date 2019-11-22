package com.yy.server.mapper;

import com.yy.server.model.PmPlanCustOrderDetail;
import com.yy.server.util.MyMapper;

public interface PmPlanCustOrderDetailMapper extends MyMapper<PmPlanCustOrderDetail> {
    int updateDelOrderDetailsByOrderId(int orderId);
}
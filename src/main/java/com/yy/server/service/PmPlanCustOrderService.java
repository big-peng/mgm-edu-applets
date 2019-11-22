package com.yy.server.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yy.server.common.BusiConstant;
import com.yy.server.mapper.*;
import com.yy.server.model.*;
import com.yy.server.model.view.CustDiagnoseOrderVO;
import com.yy.server.model.view.PmPlanCustOrderVO;
import com.yy.server.model.view.PmPlanFactorVO;
import com.yy.server.model.view.PmPlanVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class PmPlanCustOrderService {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private PmPlanCustOrderMapper pmPlanCustOrderMapper;

    @Resource
    private PmPlanCustOrderDetailMapper pmPlanCustOrderDetailMapper;

    @Resource
    private PmPlanCustOrderDetailPhMapper pmPlanCustOrderDetailPhMapper;

    @Resource
    private PmPlanMapper pmPlanMapper;

    @Resource
    private PmPlanFactorMapper pmPlanFactorMapper;

    @Resource
    private PmPlanFactorValueMapper pmPlanFactorValueMapper;

    @Autowired
    public AdminMapper adminMapper;


    public HashMap<String, Object> getPmPlanCustOrderList(PmPlanCustOrderVO vo) {
        if (vo.getPage() != null && vo.getRows() != null) {
            PageHelper.startPage(vo.getPage(), vo.getRows());
        }
        List<PmPlanCustOrderVO> dataList = pmPlanCustOrderMapper.getDataList(vo);
        PageInfo pageinfo = new PageInfo(dataList);
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageCount", pageinfo.getTotal());
        map.put("pageList", dataList);
        return map;
    }

    public int addPmPlanCustOrderVO(PmPlanCustOrderVO planCustOrderVO) {
        Date nowDate = new Date();

        PmPlanCustOrder pmPlanCustOrder = new PmPlanCustOrder();
        pmPlanCustOrder.setPlanId(planCustOrderVO.getPlanId());
        pmPlanCustOrder.setName(planCustOrderVO.getName());
        pmPlanCustOrder.setAimQuestions(planCustOrderVO.getAimQuestions());
        pmPlanCustOrder.setPlanName(planCustOrderVO.getPlanName());
        pmPlanCustOrder.setAppId(planCustOrderVO.getAppId());
        pmPlanCustOrder.setAssistantId(planCustOrderVO.getAssistantId() == null ? "" : planCustOrderVO.getAssistantId());
        pmPlanCustOrder.setCustId(planCustOrderVO.getCustId());
        pmPlanCustOrder.setFaceDiagnoseId(planCustOrderVO.getFaceDiagnoseId() == null ? "" : planCustOrderVO.getFaceDiagnoseId());
        pmPlanCustOrder.setPhysicianId(planCustOrderVO.getPhysicianId() == null ? "" : planCustOrderVO.getPhysicianId());
        pmPlanCustOrder.setDiagnoseRemark(planCustOrderVO.getDiagnoseRemark() == null ? "" : planCustOrderVO.getDiagnoseRemark());
        pmPlanCustOrder.setDelFlag(BusiConstant.DELFLAG_N);
        pmPlanCustOrder.setCreateTime(nowDate);
        pmPlanCustOrder.setUpdateTime(nowDate);
        pmPlanCustOrderMapper.insertSelective(pmPlanCustOrder);

        int orderId = pmPlanCustOrder.getId();
        List<PmPlanCustOrderDetail> planCustOrderDetailList = planCustOrderVO.getPlanCustOrderDetailList();
        if (planCustOrderDetailList != null && planCustOrderDetailList.size() > 0) {
            for (PmPlanCustOrderDetail pmPlanCustOrderDetailTemp : planCustOrderDetailList) {
                pmPlanCustOrderDetailTemp.setOrderId(orderId);
                pmPlanCustOrderDetailTemp.setCreateTime(nowDate);
                pmPlanCustOrderDetailTemp.setUpdateTime(nowDate);
                pmPlanCustOrderDetailTemp.setDelFlag(BusiConstant.DELFLAG_N);
            }
            pmPlanCustOrderDetailMapper.insertList(planCustOrderDetailList);
        }

        List<PmPlanCustOrderDetailPh> planCustOrderDetailPhList = planCustOrderVO.getPlanCustOrderDetailPhList();
        if (planCustOrderDetailPhList != null && planCustOrderDetailPhList.size() > 0) {
            logger.info("planCustOrderDetailPhList={}", JSONObject.toJSONString(planCustOrderDetailPhList));

            for (PmPlanCustOrderDetailPh pmPlanCustOrderDetailPhTemp : planCustOrderDetailPhList) {
                pmPlanCustOrderDetailPhTemp.setCurrentValue(pmPlanCustOrderDetailPhTemp.getCurrentValue() != null ? pmPlanCustOrderDetailPhTemp.getCurrentValue() : BigDecimal.ZERO);
                pmPlanCustOrderDetailPhTemp.setLastValue(pmPlanCustOrderDetailPhTemp.getLastValue() != null ? pmPlanCustOrderDetailPhTemp.getLastValue() : BigDecimal.ZERO);
                pmPlanCustOrderDetailPhTemp.setRemark(pmPlanCustOrderDetailPhTemp.getRemark() != null ? pmPlanCustOrderDetailPhTemp.getRemark() : "");
                pmPlanCustOrderDetailPhTemp.setOrderId(orderId);
                pmPlanCustOrderDetailPhTemp.setCreateTime(nowDate);
                pmPlanCustOrderDetailPhTemp.setUpdateTime(nowDate);

                pmPlanCustOrderDetailPhTemp.setDelFlag(BusiConstant.DELFLAG_N);
            }
            pmPlanCustOrderDetailPhMapper.insertList(planCustOrderDetailPhList);
        }
        return orderId;
    }

    public int updatePmPlanCustOrderVO(PmPlanCustOrderVO planCustOrderVO) {
        Date nowDate = new Date();
        int orderId = planCustOrderVO.getId();

        PmPlanCustOrder pmPlanCustOrder = new PmPlanCustOrder();
        pmPlanCustOrder.setId(orderId);
        pmPlanCustOrder.setName(planCustOrderVO.getName());
        pmPlanCustOrder.setAimQuestions(planCustOrderVO.getAimQuestions());
        pmPlanCustOrder.setPlanName(planCustOrderVO.getPlanName());
        pmPlanCustOrder.setPlanId(planCustOrderVO.getPlanId());
        pmPlanCustOrder.setAppId(planCustOrderVO.getAppId());
        pmPlanCustOrder.setAssistantId(planCustOrderVO.getAssistantId());
        pmPlanCustOrder.setFaceDiagnoseId(planCustOrderVO.getFaceDiagnoseId());
        pmPlanCustOrder.setPhysicianId(planCustOrderVO.getPhysicianId());
        pmPlanCustOrder.setDiagnoseRemark(planCustOrderVO.getDiagnoseRemark());
        pmPlanCustOrder.setUpdateTime(nowDate);
        int success = pmPlanCustOrderMapper.updateByPrimaryKeySelective(pmPlanCustOrder);
        //方案详情先删除，再增加
        pmPlanCustOrderDetailMapper.updateDelOrderDetailsByOrderId(orderId);
        List<PmPlanCustOrderDetail> planCustOrderDetailList = planCustOrderVO.getPlanCustOrderDetailList();
        if (planCustOrderDetailList != null) {
            for (PmPlanCustOrderDetail pmPlanCustOrderDetailTemp : planCustOrderDetailList) {
                pmPlanCustOrderDetailTemp.setOrderId(orderId);
                pmPlanCustOrderDetailTemp.setCreateTime(nowDate);
                pmPlanCustOrderDetailTemp.setUpdateTime(nowDate);
                pmPlanCustOrderDetailTemp.setDelFlag(BusiConstant.DELFLAG_N);
            }
            pmPlanCustOrderDetailMapper.insertList(planCustOrderDetailList);
        }

        //方案Ph详情先删除，再增加
        pmPlanCustOrderDetailPhMapper.updateDelOrderDetailPhsByOrderId(orderId);
        List<PmPlanCustOrderDetailPh> planCustOrderDetailPhList = planCustOrderVO.getPlanCustOrderDetailPhList();
        if (planCustOrderDetailPhList != null) {
            logger.info("planCustOrderDetailPhList={}", JSONObject.toJSONString(planCustOrderDetailPhList));
            for (PmPlanCustOrderDetailPh pmPlanCustOrderDetailPhTemp : planCustOrderDetailPhList) {
                pmPlanCustOrderDetailPhTemp.setCurrentValue(pmPlanCustOrderDetailPhTemp.getCurrentValue() != null ? pmPlanCustOrderDetailPhTemp.getCurrentValue() : BigDecimal.ZERO);
                pmPlanCustOrderDetailPhTemp.setLastValue(pmPlanCustOrderDetailPhTemp.getLastValue() != null ? pmPlanCustOrderDetailPhTemp.getLastValue() : BigDecimal.ZERO);
                pmPlanCustOrderDetailPhTemp.setRemark(pmPlanCustOrderDetailPhTemp.getRemark() != null ? pmPlanCustOrderDetailPhTemp.getRemark() : "");

                pmPlanCustOrderDetailPhTemp.setOrderId(orderId);
                pmPlanCustOrderDetailPhTemp.setCreateTime(nowDate);
                pmPlanCustOrderDetailPhTemp.setUpdateTime(nowDate);
                pmPlanCustOrderDetailPhTemp.setDelFlag(BusiConstant.DELFLAG_N);
            }
            pmPlanCustOrderDetailPhMapper.insertList(planCustOrderDetailPhList);
        }
        return success;
    }


    public int delPmPlanCustOrderVO(PmPlanCustOrderVO planCustOrderVO) {
        Date nowDate = new Date();
        int orderId = planCustOrderVO.getId();

        PmPlanCustOrder pmPlanCustOrder = new PmPlanCustOrder();
        pmPlanCustOrder.setId(orderId);
        pmPlanCustOrder.setUpdateTime(nowDate);
        pmPlanCustOrder.setDelFlag(BusiConstant.DELFLAG_Y);
        int success = pmPlanCustOrderMapper.updateByPrimaryKeySelective(pmPlanCustOrder);
        return success;
    }


    public PmPlanCustOrderVO queryPmPlanCustOrderVOByOrderId(int orderId) {
        PmPlanCustOrderVO pmPlanCustOrderVO = null;
        PmPlanCustOrder pmPlanCustOrderQuery = pmPlanCustOrderMapper.selectByPrimaryKey(orderId);
        if (pmPlanCustOrderQuery != null) {
            pmPlanCustOrderVO = new PmPlanCustOrderVO();

            pmPlanCustOrderVO.setPlanId(pmPlanCustOrderQuery.getPlanId());
            pmPlanCustOrderVO.setAppId(pmPlanCustOrderQuery.getAppId());
            pmPlanCustOrderVO.setName(pmPlanCustOrderQuery.getName());
            pmPlanCustOrderVO.setAssistantId(pmPlanCustOrderQuery.getAssistantId());
            if (StringUtils.isNotBlank(pmPlanCustOrderQuery.getAssistantId())) {
                pmPlanCustOrderVO.setAssistantName(getAdminRealName(pmPlanCustOrderQuery.getAssistantId()));
            }
            pmPlanCustOrderVO.setPlanName(pmPlanCustOrderQuery.getPlanName());
            pmPlanCustOrderVO.setCustId(pmPlanCustOrderQuery.getCustId());
            pmPlanCustOrderVO.setFaceDiagnoseId(pmPlanCustOrderQuery.getFaceDiagnoseId());
            if (StringUtils.isNotBlank(pmPlanCustOrderQuery.getFaceDiagnoseId())) {
                pmPlanCustOrderVO.setFaceDiagnoseName(getAdminRealName(pmPlanCustOrderQuery.getFaceDiagnoseId()));
            }
            pmPlanCustOrderVO.setPhysicianId(pmPlanCustOrderQuery.getPhysicianId());
            if (StringUtils.isNotBlank(pmPlanCustOrderQuery.getPhysicianId())) {
                pmPlanCustOrderVO.setPhysicianName(getAdminRealName(pmPlanCustOrderQuery.getPhysicianId()));
            }
            pmPlanCustOrderVO.setDiagnoseRemark(pmPlanCustOrderQuery.getDiagnoseRemark());
            pmPlanCustOrderVO.setCreateTime(pmPlanCustOrderQuery.getCreateTime());
            pmPlanCustOrderVO.setId(pmPlanCustOrderQuery.getId());
            PmPlanCustOrderDetail pmPlanCustOrderDetail = new PmPlanCustOrderDetail();
            pmPlanCustOrderDetail.setOrderId(orderId);
            pmPlanCustOrderDetail.setDelFlag(BusiConstant.DELFLAG_N);
            List<PmPlanCustOrderDetail> custOrderDetailList = pmPlanCustOrderDetailMapper.select(pmPlanCustOrderDetail);
            pmPlanCustOrderVO.setPlanCustOrderDetailList(custOrderDetailList);

            PmPlanCustOrderDetailPh pmPlanCustOrderDetailPh = new PmPlanCustOrderDetailPh();
            pmPlanCustOrderDetailPh.setOrderId(orderId);
            pmPlanCustOrderDetailPh.setDelFlag(BusiConstant.DELFLAG_N);
            List<PmPlanCustOrderDetailPh> custOrderDetailPhList = pmPlanCustOrderDetailPhMapper.select(pmPlanCustOrderDetailPh);
            pmPlanCustOrderVO.setPlanCustOrderDetailPhList(custOrderDetailPhList);
        }
        return pmPlanCustOrderVO;
    }

    public List<PmPlanCustOrderDetailPh> queryCustLastOrderDetailPhList(int custId) {
        List<PmPlanCustOrderDetailPh> custOrderDetailPhList = pmPlanCustOrderDetailPhMapper.getLastDataList(custId);
        return custOrderDetailPhList;
    }

    public PmPlanCustOrder queryOnePmPlanFactor(PmPlanCustOrder pmPlanCustOrder) {
        return pmPlanCustOrderMapper.selectOne(pmPlanCustOrder);
    }


    /**
     * 获取方案的所有因子信息
     *
     * @param vo
     * @return
     */
    public PmPlanVO queryOnePmPlanFactorVO(PmPlanVO vo) {
        List<PmPlanVO> dataList = pmPlanMapper.getDataList(vo);
        PmPlanVO pmPlanVO = dataList.get(0);

        PmPlanFactorVO pmPlanFactorVO = new PmPlanFactorVO();
        pmPlanFactorVO.setAppId(pmPlanVO.getAppId());
        pmPlanFactorVO.setPlanId(pmPlanVO.getId());
        List<PmPlanFactorVO> factorVOList = pmPlanFactorMapper.getDataList(pmPlanFactorVO);
        for (PmPlanFactorVO pmPlanFactorTemp : factorVOList) {
            PmPlanFactorValue pmPlanFactorValue = new PmPlanFactorValue();
            pmPlanFactorValue.setFactorId(pmPlanFactorTemp.getId());
            List<PmPlanFactorValue> valuesList = pmPlanFactorValueMapper.getPmPlanFactorValues(pmPlanFactorValue);
            pmPlanFactorTemp.setPlanFactorValueList(valuesList);
        }
        pmPlanVO.setPlanFactorVoList(factorVOList);
        return pmPlanVO;
    }

    public List getPmPlans(PmPlan entity) {
        List<PmPlan> list = pmPlanMapper.getPmPlans(entity);
        return list;
    }

    private String getAdminRealName(String adminId) {
        Admin admin = adminMapper.selectByAdminId(adminId);
        return (admin != null ? admin.getRealName() : null);
    }

    public HashMap<String, Object> getCustManagePlanOrderList(CustDiagnoseOrderVO vo) {
        if (vo.getPage() != null && vo.getRows() != null) {
            PageHelper.startPage(vo.getPage(), vo.getRows());
        }
        List<CustDiagnoseOrderVO> list = pmPlanCustOrderMapper.getCustManagePlanOrdersByCustId(vo.getCustId());
        if (list != null) {
            int startRowNum = (vo.getPage() == 1 ? 0 : vo.getPage()) * vo.getRows();
            int index = 0;
            for (CustDiagnoseOrderVO custDiagnoseOrderVO : list) {
                custDiagnoseOrderVO.setRowNum(startRowNum + (++index));
            }
        }
        PageInfo pageinfo = new PageInfo(list);
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageCount", pageinfo.getTotal());
        map.put("pageList", list);
        return map;
    }


    public HashMap<String, Object> getCustDiagnoseOrderList(CustDiagnoseOrderVO vo) {
        if (vo.getPage() != null && vo.getRows() != null) {
            PageHelper.startPage(vo.getPage(), vo.getRows());
        }
        List<CustDiagnoseOrderVO> list = pmPlanCustOrderMapper.getCustDiagnoseOrdersByCustId(vo.getCustId());
        if (vo.getPage() > 1 && list != null) {
            int startRowNum = vo.getPage() * vo.getRows();
            for (CustDiagnoseOrderVO custDiagnoseOrderVO : list) {
                custDiagnoseOrderVO.setRowNum(startRowNum + custDiagnoseOrderVO.getRowNum());
            }
        }
        PageInfo pageinfo = new PageInfo(list);
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageCount", pageinfo.getTotal());
        map.put("pageList", list);
        return map;
    }

    public HashMap<String, Object> getCustDiagnoseOrderListByParams(CustDiagnoseOrderVO vo) {
        if (vo.getPage() != null && vo.getRows() != null) {
            PageHelper.startPage(vo.getPage(), vo.getRows());
        }
        List<CustDiagnoseOrderVO> list = pmPlanCustOrderMapper.getCustDiagnoseOrdersByParams(vo);
        if (vo.getPage() > 1 && list != null) {
            int startRowNum = vo.getPage() * vo.getRows();
            for (CustDiagnoseOrderVO custDiagnoseOrderVO : list) {
                custDiagnoseOrderVO.setRowNum(startRowNum + custDiagnoseOrderVO.getRowNum());
            }
        }
        PageInfo pageinfo = new PageInfo(list);
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageCount", pageinfo.getTotal());
        map.put("pageList", list);
        return map;
    }


    public HashMap<String, Object> getCustDiagnosePlanOrderListByParams(CustDiagnoseOrderVO vo) {
        if (vo.getPage() != null && vo.getRows() != null) {
            PageHelper.startPage(vo.getPage(), vo.getRows());
        }
        List<CustDiagnoseOrderVO> list = pmPlanCustOrderMapper.getCustDiagnosePlanOrdersByParams(vo);
        if (vo.getPage() > 1 && list != null) {
            int startRowNum = vo.getPage() * vo.getRows();
            for (CustDiagnoseOrderVO custDiagnoseOrderVO : list) {
                custDiagnoseOrderVO.setRowNum(startRowNum + custDiagnoseOrderVO.getRowNum());
            }
        }
        PageInfo pageinfo = new PageInfo(list);
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageCount", pageinfo.getTotal());
        map.put("pageList", list);
        return map;
    }


}

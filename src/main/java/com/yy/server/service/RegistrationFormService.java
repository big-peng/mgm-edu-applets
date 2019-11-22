package com.yy.server.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yy.server.common.BusiConstant;
import com.yy.server.mapper.CustRegisterMapper;
import com.yy.server.model.dto.CounselingRegistrationAddDTO;
import com.yy.server.model.dto.CounselingRegistrationListDTO;
import com.yy.server.model.po.CustRegister;
import com.yy.server.model.view.CounselingRegistrationListVO;
import com.yy.server.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ChenXiangpeng
 * @ClassName RegistrationFormService
 * @date：2019/11/13
 * @version: V1.0.0
 * @description：
 */
@Service
@Slf4j
public class RegistrationFormService {
    @Resource
    private CustRegisterMapper custRegisterMapper;

    @Transactional
    public Boolean add(CounselingRegistrationAddDTO params) throws Exception{
        CustRegister custRegister = new CustRegister();
        BeanUtils.copyProperties(params,custRegister);
        custRegister.setAccount(System.currentTimeMillis()+params.getPhone());
        custRegister.setPassword(params.getPhone());
        custRegister.setCreatedTime(new Date());
        custRegister.setUpdatedTime(new Date());
        custRegister.setState(true);
        custRegister.setAppId(params.getPhone());
        int i = custRegisterMapper.insertSelective(custRegister);
        if (i<1){
            throw new Exception("登记失败，资料输入错误");
        }
        return true;
    }

    public Map<String, Object> list(CounselingRegistrationListDTO params) {
        Map<String,Object> result = new HashMap<>();

        Example example = new Example(CustRegister.class);
        Example.Criteria criteria = example.createCriteria()
                .andGreaterThanOrEqualTo("createdTime", params.getCreatedTimeStart())
                .andLessThanOrEqualTo("createdTime", params.getCreatedTimeEnd())
                .andGreaterThanOrEqualTo("reservationTime", params.getReservationTimeStart())
                .andLessThanOrEqualTo("reservationTime", params.getReservationTimeEnd());
        if (StringUtil.isNotBlank(params.getSex())){
            criteria.andEqualTo("sex", params.getSex());
        }
        if (StringUtil.isNotBlank(params.getName())){
            criteria.andLike("name", "%" + params.getName() + "%");
        }
        if (StringUtil.isNotBlank(params.getPhone())){
            criteria.andLike("phone", "%" + params.getPhone() + "%");
        }
        example.setOrderByClause("created_time desc");
        Page<CustRegister> page = PageHelper.startPage(params.getPage(), params.getRows());
        List<CustRegister> custRegisters = custRegisterMapper.selectByExample(example);
        if (custRegisters != null){
            List<CounselingRegistrationListVO> resultList = custRegisters.stream().map(e -> {
                CounselingRegistrationListVO counselingRegistrationListVO = new CounselingRegistrationListVO();
                BeanUtils.copyProperties(e, counselingRegistrationListVO);
                return counselingRegistrationListVO;
            }).collect(Collectors.toList());
            result.put("data",resultList);
        }
        result.put("pageNo",page.getPageNum());
        result.put("totalPages",page.getPages());
        result.put("totalRecords",page.getTotal());
        return result;
    }
}

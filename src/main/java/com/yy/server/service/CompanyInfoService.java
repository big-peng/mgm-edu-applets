package com.yy.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yy.server.mapper.CompanyInfoMapper;
import com.yy.server.model.CompanyInfo;
import com.yy.server.model.view.CompanyInfoVo;
import com.yy.server.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;
import tk.mybatis.orderbyhelper.OrderByHelper;

import java.util.List;

@Service
@Transactional
public class CompanyInfoService {

    @Autowired
    private CompanyInfoMapper companyInfoMapper;


    /**
     * 新增公司信息
     *
     * @param companyInfo
     * @return
     */
    public int insertCompanyInfo(CompanyInfo companyInfo) {
        String dateTime = DateUtils.getDateTime();
        companyInfo.setCreatedTime(dateTime);
        companyInfo.setUpdatedTime(dateTime);
        companyInfo.setState(Boolean.TRUE);
        return companyInfoMapper.insert(companyInfo);
    }


    /**
     * 修改公司信息
     *
     * @param companyInfo
     * @return
     */
    public int updateCompanyInfo(CompanyInfo companyInfo) {
        companyInfo.setUpdatedTime(DateUtils.getDateTime());
        return companyInfoMapper.updateByPrimaryKeySelective(companyInfo);
    }

    /**
     * 删除公司信息
     *
     * @param vo
     * @return
     */
    public int deleteCompanyInfo(CompanyInfo vo) {
        if (vo != null && vo.getId() > 0) {
            CompanyInfo companyInfo = new CompanyInfo();
            companyInfo.setState(false);
            companyInfo.setId(vo.getId());
            return companyInfoMapper.updateByPrimaryKeySelective(companyInfo);
        }
        return 0;

    }

    /**
     * 查询公司信息
     *
     * @param companyInfo
     * @return
     */
    public CompanyInfo getOneCompanyInfo(CompanyInfo companyInfo) {
        return companyInfoMapper.selectOne(companyInfo);
    }


    /**
     * 分页查询公司信息
     *
     * @param companyInfoVo
     * @return
     */
    public PageInfo<CompanyInfo> queryCompanyInfoByPage(CompanyInfoVo companyInfoVo) {
        if (companyInfoVo.getPage() != null && companyInfoVo.getRows() != null) {
            PageHelper.startPage(companyInfoVo.getPage(), companyInfoVo.getRows());
        }
        OrderByHelper.orderBy("created_time desc");
        List<CompanyInfo> members = companyInfoMapper.select(companyInfoVo);
        PageInfo pageinfo = new PageInfo(members);
        return pageinfo;
    }

    public List queryCompany() {
        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.setState(true);
        OrderByHelper.orderBy("created_time desc");
        List<CompanyInfo> companyInfoList = companyInfoMapper.select(companyInfo);
        return companyInfoList;
    }


    public Boolean checkCompanyName(CompanyInfo companyInfo) {
        Weekend<CompanyInfo> weekend = Weekend.of(CompanyInfo.class);
        WeekendCriteria<CompanyInfo, Object> criteria = weekend.weekendCriteria();
        criteria.andEqualTo(CompanyInfo::getCompanyName, companyInfo.getCompanyName());
        criteria.andEqualTo(CompanyInfo::getState, companyInfo.getState());
        CompanyInfo company = companyInfoMapper.selectOneByExample(weekend);
        return (company == null ? true : false);
    }

    public CompanyInfo selectCompanyInfoById(Integer id) {
        return companyInfoMapper.selectByPrimaryKey(id);
    }
}

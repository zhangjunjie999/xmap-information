package com.xmap.information.service.impl;

import java.util.List;
import com.xmap.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.InvestmentInformationMapper;
import com.xmap.information.domain.InvestmentInformation;
import com.xmap.information.service.IInvestmentInformationService;

/**
 * 招商信息列Service业务层处理
 * 
 * @author xmap
 * @date 2023-07-04
 */
@Service
public class InvestmentInformationServiceImpl implements IInvestmentInformationService 
{
    @Autowired
    private InvestmentInformationMapper investmentInformationMapper;

    /**
     * 查询招商信息列
     * 
     * @param investmentId 招商信息列主键
     * @return 招商信息列
     */
    @Override
    public InvestmentInformation selectInvestmentInformationByInvestmentId(Long investmentId)
    {
        return investmentInformationMapper.selectInvestmentInformationByInvestmentId(investmentId);
    }

    /**
     * 查询招商信息列列表
     * 
     * @param investmentInformation 招商信息列
     * @return 招商信息列
     */
    @Override
    public List<InvestmentInformation> selectInvestmentInformationList(InvestmentInformation investmentInformation)
    {
        return investmentInformationMapper.selectInvestmentInformationList(investmentInformation);
    }

    /**
     * 新增招商信息列
     * 
     * @param investmentInformation 招商信息列
     * @return 结果
     */
    @Override
    public int insertInvestmentInformation(InvestmentInformation investmentInformation)
    {
        investmentInformation.setCreateTime(DateUtils.getNowDate());
        return investmentInformationMapper.insertInvestmentInformation(investmentInformation);
    }

    /**
     * 修改招商信息列
     * 
     * @param investmentInformation 招商信息列
     * @return 结果
     */
    @Override
    public int updateInvestmentInformation(InvestmentInformation investmentInformation)
    {
        return investmentInformationMapper.updateInvestmentInformation(investmentInformation);
    }

    /**
     * 批量删除招商信息列
     * 
     * @param investmentIds 需要删除的招商信息列主键
     * @return 结果
     */
    @Override
    public int deleteInvestmentInformationByInvestmentIds(Long[] investmentIds)
    {
        return investmentInformationMapper.deleteInvestmentInformationByInvestmentIds(investmentIds);
    }

    /**
     * 删除招商信息列信息
     * 
     * @param investmentId 招商信息列主键
     * @return 结果
     */
    @Override
    public int deleteInvestmentInformationByInvestmentId(Long investmentId)
    {
        return investmentInformationMapper.deleteInvestmentInformationByInvestmentId(investmentId);
    }
}

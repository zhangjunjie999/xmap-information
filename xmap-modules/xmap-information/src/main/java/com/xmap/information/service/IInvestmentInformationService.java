package com.xmap.information.service;

import java.util.List;
import com.xmap.information.domain.InvestmentInformation;

/**
 * 招商信息列Service接口
 * 
 * @author xmap
 * @date 2023-07-04
 */
public interface IInvestmentInformationService 
{
    /**
     * 查询招商信息列
     * 
     * @param investmentId 招商信息列主键
     * @return 招商信息列
     */
    public InvestmentInformation selectInvestmentInformationByInvestmentId(Long investmentId);

    /**
     * 查询招商信息列列表
     * 
     * @param investmentInformation 招商信息列
     * @return 招商信息列集合
     */
    public List<InvestmentInformation> selectInvestmentInformationList(InvestmentInformation investmentInformation);

    /**
     * 新增招商信息列
     * 
     * @param investmentInformation 招商信息列
     * @return 结果
     */
    public int insertInvestmentInformation(InvestmentInformation investmentInformation);

    /**
     * 修改招商信息列
     * 
     * @param investmentInformation 招商信息列
     * @return 结果
     */
    public int updateInvestmentInformation(InvestmentInformation investmentInformation);

    /**
     * 批量删除招商信息列
     * 
     * @param investmentIds 需要删除的招商信息列主键集合
     * @return 结果
     */
    public int deleteInvestmentInformationByInvestmentIds(Long[] investmentIds);

    /**
     * 删除招商信息列信息
     * 
     * @param investmentId 招商信息列主键
     * @return 结果
     */
    public int deleteInvestmentInformationByInvestmentId(Long investmentId);
}

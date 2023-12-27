package com.xmap.information.mapper;

import java.util.List;
import com.xmap.information.domain.InvestmentInformation;

/**
 * 招商信息列Mapper接口
 * 
 * @author xmap
 * @date 2023-07-04
 */
public interface InvestmentInformationMapper 
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
     * 删除招商信息列
     * 
     * @param investmentId 招商信息列主键
     * @return 结果
     */
    public int deleteInvestmentInformationByInvestmentId(Long investmentId);

    /**
     * 批量删除招商信息列
     * 
     * @param investmentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteInvestmentInformationByInvestmentIds(Long[] investmentIds);
}

package com.xmap.information.mapper;

import java.util.List;
import com.xmap.information.domain.JgsAnaly;

/**
 * 统计分析Mapper接口
 * 
 * @author xmap
 * @date 2023-10-09
 */
public interface JgsAnalyMapper 
{
    /**
     * 查询统计分析
     * 
     * @param statisticsId 统计分析主键
     * @return 统计分析
     */
    public JgsAnaly selectJgsAnalyByStatisticsId(Long statisticsId);

    /**
     * 查询统计分析列表
     * 
     * @param jgsAnaly 统计分析
     * @return 统计分析集合
     */
    public List<JgsAnaly> selectJgsAnalyList(JgsAnaly jgsAnaly);

    /**
     * 新增统计分析
     * 
     * @param jgsAnaly 统计分析
     * @return 结果
     */
    public int insertJgsAnaly(JgsAnaly jgsAnaly);

    /**
     * 修改统计分析
     * 
     * @param jgsAnaly 统计分析
     * @return 结果
     */
    public int updateJgsAnaly(JgsAnaly jgsAnaly);

    /**
     * 删除统计分析
     * 
     * @param statisticsId 统计分析主键
     * @return 结果
     */
    public int deleteJgsAnalyByStatisticsId(Long statisticsId);

    /**
     * 批量删除统计分析
     * 
     * @param statisticsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJgsAnalyByStatisticsIds(Long[] statisticsIds);
}

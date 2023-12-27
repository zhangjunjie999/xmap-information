package com.xmap.information.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.JgsAnalyMapper;
import com.xmap.information.domain.JgsAnaly;
import com.xmap.information.service.IJgsAnalyService;

/**
 * 统计分析Service业务层处理
 * 
 * @author xmap
 * @date 2023-10-09
 */
@Service
public class JgsAnalyServiceImpl implements IJgsAnalyService 
{
    @Autowired
    private JgsAnalyMapper jgsAnalyMapper;

    /**
     * 查询统计分析
     * 
     * @param statisticsId 统计分析主键
     * @return 统计分析
     */
    @Override
    public JgsAnaly selectJgsAnalyByStatisticsId(Long statisticsId)
    {
        return jgsAnalyMapper.selectJgsAnalyByStatisticsId(statisticsId);
    }

    /**
     * 查询统计分析列表
     * 
     * @param jgsAnaly 统计分析
     * @return 统计分析
     */
    @Override
    public List<JgsAnaly> selectJgsAnalyList(JgsAnaly jgsAnaly)
    {
        return jgsAnalyMapper.selectJgsAnalyList(jgsAnaly);
    }

    /**
     * 新增统计分析
     * 
     * @param jgsAnaly 统计分析
     * @return 结果
     */
    @Override
    public int insertJgsAnaly(JgsAnaly jgsAnaly)
    {
        return jgsAnalyMapper.insertJgsAnaly(jgsAnaly);
    }

    /**
     * 修改统计分析
     * 
     * @param jgsAnaly 统计分析
     * @return 结果
     */
    @Override
    public int updateJgsAnaly(JgsAnaly jgsAnaly)
    {
        return jgsAnalyMapper.updateJgsAnaly(jgsAnaly);
    }

    /**
     * 批量删除统计分析
     * 
     * @param statisticsIds 需要删除的统计分析主键
     * @return 结果
     */
    @Override
    public int deleteJgsAnalyByStatisticsIds(Long[] statisticsIds)
    {
        return jgsAnalyMapper.deleteJgsAnalyByStatisticsIds(statisticsIds);
    }

    /**
     * 删除统计分析信息
     * 
     * @param statisticsId 统计分析主键
     * @return 结果
     */
    @Override
    public int deleteJgsAnalyByStatisticsId(Long statisticsId)
    {
        return jgsAnalyMapper.deleteJgsAnalyByStatisticsId(statisticsId);
    }
}

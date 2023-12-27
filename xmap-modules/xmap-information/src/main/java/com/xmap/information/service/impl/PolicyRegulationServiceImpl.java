package com.xmap.information.service.impl;

import java.util.List;
import com.xmap.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.PolicyRegulationMapper;
import com.xmap.information.domain.PolicyRegulation;
import com.xmap.information.service.IPolicyRegulationService;

/**
 * 政策法规列表Service业务层处理
 * 
 * @author xmap
 * @date 2023-07-12
 */
@Service
public class PolicyRegulationServiceImpl implements IPolicyRegulationService 
{
    @Autowired
    private PolicyRegulationMapper policyRegulationMapper;

    /**
     * 查询政策法规列表
     * 
     * @param policesId 政策法规列表主键
     * @return 政策法规列表
     */
    @Override
    public PolicyRegulation selectPolicyRegulationByPolicesId(Long policesId)
    {
        return policyRegulationMapper.selectPolicyRegulationByPolicesId(policesId);
    }

    /**
     * 查询政策法规列表列表
     * 
     * @param policyRegulation 政策法规列表
     * @return 政策法规列表
     */
    @Override
    public List<PolicyRegulation> selectPolicyRegulationList(PolicyRegulation policyRegulation)
    {
        return policyRegulationMapper.selectPolicyRegulationList(policyRegulation);
    }

    /**
     * 新增政策法规列表
     * 
     * @param policyRegulation 政策法规列表
     * @return 结果
     */
    @Override
    public int insertPolicyRegulation(PolicyRegulation policyRegulation)
    {
        policyRegulation.setCreateTime(DateUtils.getNowDate());
        return policyRegulationMapper.insertPolicyRegulation(policyRegulation);
    }

    /**
     * 修改政策法规列表
     * 
     * @param policyRegulation 政策法规列表
     * @return 结果
     */
    @Override
    public int updatePolicyRegulation(PolicyRegulation policyRegulation)
    {
        return policyRegulationMapper.updatePolicyRegulation(policyRegulation);
    }

    /**
     * 批量删除政策法规列表
     * 
     * @param policesIds 需要删除的政策法规列表主键
     * @return 结果
     */
    @Override
    public int deletePolicyRegulationByPolicesIds(Long[] policesIds)
    {
        return policyRegulationMapper.deletePolicyRegulationByPolicesIds(policesIds);
    }

    /**
     * 删除政策法规列表信息
     * 
     * @param policesId 政策法规列表主键
     * @return 结果
     */
    @Override
    public int deletePolicyRegulationByPolicesId(Long policesId)
    {
        return policyRegulationMapper.deletePolicyRegulationByPolicesId(policesId);
    }
}

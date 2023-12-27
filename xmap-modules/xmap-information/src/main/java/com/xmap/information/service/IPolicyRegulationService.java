package com.xmap.information.service;

import java.util.List;
import com.xmap.information.domain.PolicyRegulation;

/**
 * 政策法规列表Service接口
 * 
 * @author xmap
 * @date 2023-07-12
 */
public interface IPolicyRegulationService 
{
    /**
     * 查询政策法规列表
     * 
     * @param policesId 政策法规列表主键
     * @return 政策法规列表
     */
    public PolicyRegulation selectPolicyRegulationByPolicesId(Long policesId);

    /**
     * 查询政策法规列表列表
     * 
     * @param policyRegulation 政策法规列表
     * @return 政策法规列表集合
     */
    public List<PolicyRegulation> selectPolicyRegulationList(PolicyRegulation policyRegulation);

    /**
     * 新增政策法规列表
     * 
     * @param policyRegulation 政策法规列表
     * @return 结果
     */
    public int insertPolicyRegulation(PolicyRegulation policyRegulation);

    /**
     * 修改政策法规列表
     * 
     * @param policyRegulation 政策法规列表
     * @return 结果
     */
    public int updatePolicyRegulation(PolicyRegulation policyRegulation);

    /**
     * 批量删除政策法规列表
     * 
     * @param policesIds 需要删除的政策法规列表主键集合
     * @return 结果
     */
    public int deletePolicyRegulationByPolicesIds(Long[] policesIds);

    /**
     * 删除政策法规列表信息
     * 
     * @param policesId 政策法规列表主键
     * @return 结果
     */
    public int deletePolicyRegulationByPolicesId(Long policesId);
}

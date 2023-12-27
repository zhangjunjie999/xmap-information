package com.xmap.information.mapper;

import java.util.List;
import com.xmap.information.domain.PolicyRegulation;

/**
 * 政策法规列表Mapper接口
 * 
 * @author xmap
 * @date 2023-07-12
 */
public interface PolicyRegulationMapper 
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
     * 删除政策法规列表
     * 
     * @param policesId 政策法规列表主键
     * @return 结果
     */
    public int deletePolicyRegulationByPolicesId(Long policesId);

    /**
     * 批量删除政策法规列表
     * 
     * @param policesIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePolicyRegulationByPolicesIds(Long[] policesIds);
}

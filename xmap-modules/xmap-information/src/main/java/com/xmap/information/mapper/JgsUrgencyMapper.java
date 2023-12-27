package com.xmap.information.mapper;

import java.util.List;
import com.xmap.information.domain.JgsUrgency;

/**
 * 紧急插播管理Mapper接口
 * 
 * @author xmap
 * @date 2023-07-31
 */
public interface JgsUrgencyMapper 
{
    /**
     * 查询紧急插播管理
     * 
     * @param id 紧急插播管理主键
     * @return 紧急插播管理
     */
    public JgsUrgency selectJgsUrgencyById(Long id);

    /**
     * 查询紧急插播管理列表
     * 
     * @param jgsUrgency 紧急插播管理
     * @return 紧急插播管理集合
     */
    public List<JgsUrgency> selectJgsUrgencyList(JgsUrgency jgsUrgency);

    /**
     * 新增紧急插播管理
     * 
     * @param jgsUrgency 紧急插播管理
     * @return 结果
     */
    public int insertJgsUrgency(JgsUrgency jgsUrgency);

    /**
     * 修改紧急插播管理
     * 
     * @param jgsUrgency 紧急插播管理
     * @return 结果
     */
    public int updateJgsUrgency(JgsUrgency jgsUrgency);

    /**
     * 删除紧急插播管理
     * 
     * @param id 紧急插播管理主键
     * @return 结果
     */
    public int deleteJgsUrgencyById(Long id);

    /**
     * 批量删除紧急插播管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJgsUrgencyByIds(Long[] ids);
}

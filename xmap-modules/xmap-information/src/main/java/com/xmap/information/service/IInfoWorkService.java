package com.xmap.information.service;

import java.util.List;
import com.xmap.information.domain.InfoWork;

/**
 * 工作动态列Service接口
 * 
 * @author xmap
 * @date 2023-07-03
 */
public interface IInfoWorkService 
{
    /**
     * 查询工作动态列
     * 
     * @param dynamicId 工作动态列主键
     * @return 工作动态列
     */
    public InfoWork selectInfoWorkByDynamicId(Long dynamicId);

    /**
     * 查询工作动态列列表
     * 
     * @param infoWork 工作动态列
     * @return 工作动态列集合
     */
    public List<InfoWork> selectInfoWorkList(InfoWork infoWork);

    /**
     * 新增工作动态列
     * 
     * @param infoWork 工作动态列
     * @return 结果
     */
    public int insertInfoWork(InfoWork infoWork);

    /**
     * 修改工作动态列
     * 
     * @param infoWork 工作动态列
     * @return 结果
     */
    public int updateInfoWork(InfoWork infoWork);

    /**
     * 批量删除工作动态列
     * 
     * @param dynamicIds 需要删除的工作动态列主键集合
     * @return 结果
     */
    public int deleteInfoWorkByDynamicIds(Long[] dynamicIds);

    /**
     * 删除工作动态列信息
     * 
     * @param dynamicId 工作动态列主键
     * @return 结果
     */
    public int deleteInfoWorkByDynamicId(Long dynamicId);
}

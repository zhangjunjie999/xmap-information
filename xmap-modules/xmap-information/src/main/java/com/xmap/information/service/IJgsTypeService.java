package com.xmap.information.service;

import java.util.List;
import com.xmap.information.domain.JgsType;

/**
 * 类型管理Service接口
 * 
 * @author xmap
 * @date 2023-10-08
 */
public interface IJgsTypeService 
{
    /**
     * 查询类型管理
     * 
     * @param id 类型管理主键
     * @return 类型管理
     */
    public JgsType selectJgsTypeById(Long id);

    /**
     * 查询类型管理列表
     * 
     * @param jgsType 类型管理
     * @return 类型管理集合
     */
    public List<JgsType> selectJgsTypeList(JgsType jgsType);

    /**
     * 新增类型管理
     * 
     * @param jgsType 类型管理
     * @return 结果
     */
    public int insertJgsType(JgsType jgsType);

    /**
     * 修改类型管理
     * 
     * @param jgsType 类型管理
     * @return 结果
     */
    public int updateJgsType(JgsType jgsType);

    /**
     * 批量删除类型管理
     * 
     * @param ids 需要删除的类型管理主键集合
     * @return 结果
     */
    public int deleteJgsTypeByIds(Long[] ids);

    /**
     * 删除类型管理信息
     * 
     * @param id 类型管理主键
     * @return 结果
     */
    public int deleteJgsTypeById(Long id);
}

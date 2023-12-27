package com.xmap.information.service;

import java.util.List;
import com.xmap.information.domain.JgsViewtype;

/**
 * 景点类型管理Service接口
 * 
 * @author xmap
 * @date 2023-11-01
 */
public interface IJgsViewtypeService 
{
    /**
     * 查询景点类型管理
     * 
     * @param id 景点类型管理主键
     * @return 景点类型管理
     */
    public JgsViewtype selectJgsViewtypeById(Long id);

    /**
     * 查询景点类型管理列表
     * 
     * @param jgsViewtype 景点类型管理
     * @return 景点类型管理集合
     */
    public List<JgsViewtype> selectJgsViewtypeList(JgsViewtype jgsViewtype);

    /**
     * 新增景点类型管理
     * 
     * @param jgsViewtype 景点类型管理
     * @return 结果
     */
    public int insertJgsViewtype(JgsViewtype jgsViewtype);

    /**
     * 修改景点类型管理
     * 
     * @param jgsViewtype 景点类型管理
     * @return 结果
     */
    public int updateJgsViewtype(JgsViewtype jgsViewtype);

    /**
     * 批量删除景点类型管理
     * 
     * @param ids 需要删除的景点类型管理主键集合
     * @return 结果
     */
    public int deleteJgsViewtypeByIds(Long[] ids);

    /**
     * 删除景点类型管理信息
     * 
     * @param id 景点类型管理主键
     * @return 结果
     */
    public int deleteJgsViewtypeById(Long id);
}

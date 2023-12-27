package com.xmap.information.mapper;

import java.util.List;
import com.xmap.information.domain.JgsSite;

/**
 * 地点标志管理Mapper接口
 * 
 * @author xmap
 * @date 2023-11-01
 */
public interface JgsSiteMapper 
{
    /**
     * 查询地点标志管理
     * 
     * @param id 地点标志管理主键
     * @return 地点标志管理
     */
    public JgsSite selectJgsSiteById(Long id);

    /**
     * 查询地点标志管理列表
     * 
     * @param jgsSite 地点标志管理
     * @return 地点标志管理集合
     */
    public List<JgsSite> selectJgsSiteList(JgsSite jgsSite);

    /**
     * 新增地点标志管理
     * 
     * @param jgsSite 地点标志管理
     * @return 结果
     */
    public int insertJgsSite(JgsSite jgsSite);

    /**
     * 修改地点标志管理
     * 
     * @param jgsSite 地点标志管理
     * @return 结果
     */
    public int updateJgsSite(JgsSite jgsSite);

    /**
     * 删除地点标志管理
     * 
     * @param id 地点标志管理主键
     * @return 结果
     */
    public int deleteJgsSiteById(Long id);

    /**
     * 批量删除地点标志管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJgsSiteByIds(Long[] ids);
}

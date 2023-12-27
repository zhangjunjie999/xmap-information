package com.xmap.information.mapper;

import java.util.List;
import com.xmap.information.domain.JgsDictionary;

/**
 * 字典管理Mapper接口
 * 
 * @author xmap
 * @date 2023-08-15
 */
public interface JgsDictionaryMapper 
{
    /**
     * 查询字典管理
     * 
     * @param id 字典管理主键
     * @return 字典管理
     */
    public JgsDictionary selectJgsDictionaryById(Long id);

    /**
     * 查询字典管理列表
     * 
     * @param jgsDictionary 字典管理
     * @return 字典管理集合
     */
    public List<JgsDictionary> selectJgsDictionaryList(JgsDictionary jgsDictionary);

    /**
     * 新增字典管理
     * 
     * @param jgsDictionary 字典管理
     * @return 结果
     */
    public int insertJgsDictionary(JgsDictionary jgsDictionary);

    /**
     * 修改字典管理
     * 
     * @param jgsDictionary 字典管理
     * @return 结果
     */
    public int updateJgsDictionary(JgsDictionary jgsDictionary);

    /**
     * 删除字典管理
     * 
     * @param id 字典管理主键
     * @return 结果
     */
    public int deleteJgsDictionaryById(Long id);

    /**
     * 批量删除字典管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJgsDictionaryByIds(Long[] ids);
}

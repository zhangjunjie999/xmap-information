package com.xmap.information.mapper;

import com.xmap.information.domain.JgsScenicList;

import java.util.List;

/**
 * 景点引导牌Mapper接口
 *
 * @author xmap
 * @date 2023-11-22
 */
public interface JgsScenicListMapper
{
    /**
     * 查询景点引导牌
     *
     * @param id 景点引导牌主键
     * @return 景点引导牌
     */
    public JgsScenicList selectJgsScenicListById(Long id);

    /**
     * 查询景点引导牌列表
     *
     * @param jgsScenicList 景点引导牌
     * @return 景点引导牌集合
     */
    public List<JgsScenicList> selectJgsScenicListList(JgsScenicList jgsScenicList);

    /**
     * 新增景点引导牌
     *
     * @param jgsScenicList 景点引导牌
     * @return 结果
     */
    public int insertJgsScenicList(JgsScenicList jgsScenicList);

    /**
     * 修改景点引导牌
     *
     * @param jgsScenicList 景点引导牌
     * @return 结果
     */
    public int updateJgsScenicList(JgsScenicList jgsScenicList);

    /**
     * 删除景点引导牌
     *
     * @param id 景点引导牌主键
     * @return 结果
     */
    public int deleteJgsScenicListById(Long id);

    /**
     * 批量删除景点引导牌
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJgsScenicListByIds(Long[] ids);
}

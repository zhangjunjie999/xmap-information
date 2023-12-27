package com.xmap.information.mapper;

import java.util.List;
import com.xmap.information.domain.JgsParkCard;

/**
 * 内容发布引导牌管理Mapper接口
 * 
 * @author xmap
 * @date 2023-11-07
 */
public interface JgsParkCardMapper 
{
    /**
     * 查询内容发布引导牌管理
     * 
     * @param id 内容发布引导牌管理主键
     * @return 内容发布引导牌管理
     */
    public JgsParkCard selectJgsParkCardById(Long id);

    /**
     * 查询内容发布引导牌管理列表
     * 
     * @param jgsParkCard 内容发布引导牌管理
     * @return 内容发布引导牌管理集合
     */
    public List<JgsParkCard> selectJgsParkCardList(JgsParkCard jgsParkCard);

    /**
     * 新增内容发布引导牌管理
     * 
     * @param jgsParkCard 内容发布引导牌管理
     * @return 结果
     */
    public int insertJgsParkCard(JgsParkCard jgsParkCard);

    /**
     * 修改内容发布引导牌管理
     * 
     * @param jgsParkCard 内容发布引导牌管理
     * @return 结果
     */
    public int updateJgsParkCard(JgsParkCard jgsParkCard);

    /**
     * 删除内容发布引导牌管理
     * 
     * @param id 内容发布引导牌管理主键
     * @return 结果
     */
    public int deleteJgsParkCardById(Long id);

    /**
     * 批量删除内容发布引导牌管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJgsParkCardByIds(Long[] ids);
}

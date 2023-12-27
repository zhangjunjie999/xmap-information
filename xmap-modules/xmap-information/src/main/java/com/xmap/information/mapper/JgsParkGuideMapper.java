package com.xmap.information.mapper;

import java.util.List;
import com.xmap.information.domain.JgsParkGuide;

/**
 * 停车场引导牌管理Mapper接口
 * 
 * @author xmap
 * @date 2023-11-03
 */
public interface JgsParkGuideMapper 
{
    /**
     * 查询停车场引导牌管理
     * 
     * @param id 停车场引导牌管理主键
     * @return 停车场引导牌管理
     */
    public JgsParkGuide selectJgsParkGuideById(Long id);

    /**
     * 查询停车场引导牌管理列表
     * 
     * @param jgsParkGuide 停车场引导牌管理
     * @return 停车场引导牌管理集合
     */
    public List<JgsParkGuide> selectJgsParkGuideList(JgsParkGuide jgsParkGuide);

    /**
     * 新增停车场引导牌管理
     * 
     * @param jgsParkGuide 停车场引导牌管理
     * @return 结果
     */
    public int insertJgsParkGuide(JgsParkGuide jgsParkGuide);

    /**
     * 修改停车场引导牌管理
     * 
     * @param jgsParkGuide 停车场引导牌管理
     * @return 结果
     */
    public int updateJgsParkGuide(JgsParkGuide jgsParkGuide);

    /**
     * 删除停车场引导牌管理
     * 
     * @param id 停车场引导牌管理主键
     * @return 结果
     */
    public int deleteJgsParkGuideById(Long id);

    /**
     * 批量删除停车场引导牌管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJgsParkGuideByIds(Long[] ids);
}

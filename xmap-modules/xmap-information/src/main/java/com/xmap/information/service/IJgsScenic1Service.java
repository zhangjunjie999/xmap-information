package com.xmap.information.service;

import com.xmap.information.domain.JgsScenic1;

import java.util.List;

/**
 * 景点引导牌Service接口
 *
 * @author xmap
 * @date 2023-11-23
 */
public interface IJgsScenic1Service
{
    /**
     * 查询景点引导牌
     *
     * @param id 景点引导牌主键
     * @return 景点引导牌
     */
    public JgsScenic1 selectJgsScenic1ById(Long id);

    /**
     * 查询景点引导牌列表
     *
     * @param jgsScenic1 景点引导牌
     * @return 景点引导牌集合
     */
    public List<JgsScenic1> selectJgsScenic1List(JgsScenic1 jgsScenic1);

    /**
     * 新增景点引导牌
     *
     * @param jgsScenic1 景点引导牌
     * @return 结果
     */
    public int insertJgsScenic1(JgsScenic1 jgsScenic1);

    /**
     * 修改景点引导牌
     *
     * @param jgsScenic1 景点引导牌
     * @return 结果
     */
    public int updateJgsScenic1(JgsScenic1 jgsScenic1);

    /**
     * 批量删除景点引导牌
     *
     * @param ids 需要删除的景点引导牌主键集合
     * @return 结果
     */
    public int deleteJgsScenic1ByIds(Long[] ids);

    /**
     * 删除景点引导牌信息
     *
     * @param id 景点引导牌主键
     * @return 结果
     */
    public int deleteJgsScenic1ById(Long id);
}

package com.xmap.information.service;

import com.xmap.information.domain.JgsView;

import java.util.List;

/**
 * 景点引导牌Service接口
 *
 * @author xmap
 * @date 2023-11-22
 */
public interface IJgsViewService
{
    /**
     * 查询景点引导牌
     *
     * @param id 景点引导牌主键
     * @return 景点引导牌
     */
    public JgsView selectJgsViewById(Long id);

    /**
     * 查询景点引导牌列表
     *
     * @param jgsView 景点引导牌
     * @return 景点引导牌集合
     */
    public List<JgsView> selectJgsViewList(JgsView jgsView);

    /**
     * 新增景点引导牌
     *
     * @param jgsView 景点引导牌
     * @return 结果
     */
    public int insertJgsView(JgsView jgsView);

    /**
     * 修改景点引导牌
     *
     * @param jgsView 景点引导牌
     * @return 结果
     */
    public int updateJgsView(JgsView jgsView);

    /**
     * 批量删除景点引导牌
     *
     * @param ids 需要删除的景点引导牌主键集合
     * @return 结果
     */
    public int deleteJgsViewByIds(Long[] ids);

    /**
     * 删除景点引导牌信息
     *
     * @param id 景点引导牌主键
     * @return 结果
     */
    public int deleteJgsViewById(Long id);
}

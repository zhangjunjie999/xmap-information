package com.xmap.information.service.impl;

import com.xmap.information.domain.JgsView;
import com.xmap.information.mapper.JgsViewMapper;
import com.xmap.information.service.IJgsViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 景点引导牌Service业务层处理
 *
 * @author xmap
 * @date 2023-11-22
 */
@Service
public class JgsViewServiceImpl implements IJgsViewService
{
    @Autowired
    private JgsViewMapper jgsViewMapper;

    /**
     * 查询景点引导牌
     *
     * @param id 景点引导牌主键
     * @return 景点引导牌
     */
    @Override
    public JgsView selectJgsViewById(Long id)
    {
        return jgsViewMapper.selectJgsViewById(id);
    }

    /**
     * 查询景点引导牌列表
     *
     * @param jgsView 景点引导牌
     * @return 景点引导牌
     */
    @Override
    public List<JgsView> selectJgsViewList(JgsView jgsView)
    {
        return jgsViewMapper.selectJgsViewList(jgsView);
    }

    /**
     * 新增景点引导牌
     *
     * @param jgsView 景点引导牌
     * @return 结果
     */
    @Override
    public int insertJgsView(JgsView jgsView)
    {
        return jgsViewMapper.insertJgsView(jgsView);
    }

    /**
     * 修改景点引导牌
     *
     * @param jgsView 景点引导牌
     * @return 结果
     */
    @Override
    public int updateJgsView(JgsView jgsView)
    {
        return jgsViewMapper.updateJgsView(jgsView);
    }

    /**
     * 批量删除景点引导牌
     *
     * @param ids 需要删除的景点引导牌主键
     * @return 结果
     */
    @Override
    public int deleteJgsViewByIds(Long[] ids)
    {
        return jgsViewMapper.deleteJgsViewByIds(ids);
    }

    /**
     * 删除景点引导牌信息
     *
     * @param id 景点引导牌主键
     * @return 结果
     */
    @Override
    public int deleteJgsViewById(Long id)
    {
        return jgsViewMapper.deleteJgsViewById(id);
    }
}

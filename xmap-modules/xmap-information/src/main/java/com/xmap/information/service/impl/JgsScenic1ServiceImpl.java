package com.xmap.information.service.impl;

import com.xmap.information.domain.JgsScenic1;
import com.xmap.information.mapper.JgsScenic1Mapper;
import com.xmap.information.service.IJgsScenic1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 景点引导牌Service业务层处理
 *
 * @author xmap
 * @date 2023-11-23
 */
@Service
public class JgsScenic1ServiceImpl implements IJgsScenic1Service
{
    @Autowired
    private JgsScenic1Mapper jgsScenic1Mapper;

    /**
     * 查询景点引导牌
     *
     * @param id 景点引导牌主键
     * @return 景点引导牌
     */
    @Override
    public JgsScenic1 selectJgsScenic1ById(Long id)
    {
        return jgsScenic1Mapper.selectJgsScenic1ById(id);
    }

    /**
     * 查询景点引导牌列表
     *
     * @param jgsScenic1 景点引导牌
     * @return 景点引导牌
     */
    @Override
    public List<JgsScenic1> selectJgsScenic1List(JgsScenic1 jgsScenic1)
    {
        return jgsScenic1Mapper.selectJgsScenic1List(jgsScenic1);
    }

    /**
     * 新增景点引导牌
     *
     * @param jgsScenic1 景点引导牌
     * @return 结果
     */
    @Override
    public int insertJgsScenic1(JgsScenic1 jgsScenic1)
    {
        return jgsScenic1Mapper.insertJgsScenic1(jgsScenic1);
    }

    /**
     * 修改景点引导牌
     *
     * @param jgsScenic1 景点引导牌
     * @return 结果
     */
    @Override
    public int updateJgsScenic1(JgsScenic1 jgsScenic1)
    {
        return jgsScenic1Mapper.updateJgsScenic1(jgsScenic1);
    }

    /**
     * 批量删除景点引导牌
     *
     * @param ids 需要删除的景点引导牌主键
     * @return 结果
     */
    @Override
    public int deleteJgsScenic1ByIds(Long[] ids)
    {
        return jgsScenic1Mapper.deleteJgsScenic1ByIds(ids);
    }

    /**
     * 删除景点引导牌信息
     *
     * @param id 景点引导牌主键
     * @return 结果
     */
    @Override
    public int deleteJgsScenic1ById(Long id)
    {
        return jgsScenic1Mapper.deleteJgsScenic1ById(id);
    }
}

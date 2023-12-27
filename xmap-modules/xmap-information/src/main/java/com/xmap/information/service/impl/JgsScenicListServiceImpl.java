package com.xmap.information.service.impl;

import com.xmap.information.domain.JgsScenicList;
import com.xmap.information.mapper.JgsScenicListMapper;
import com.xmap.information.service.IJgsScenicListService;
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
public class JgsScenicListServiceImpl implements IJgsScenicListService
{
    @Autowired
    private JgsScenicListMapper jgsScenicListMapper;

    /**
     * 查询景点引导牌
     *
     * @param id 景点引导牌主键
     * @return 景点引导牌
     */
    @Override
    public JgsScenicList selectJgsScenicListById(Long id)
    {
        return jgsScenicListMapper.selectJgsScenicListById(id);
    }

    /**
     * 查询景点引导牌列表
     *
     * @param jgsScenicList 景点引导牌
     * @return 景点引导牌
     */
    @Override
    public List<JgsScenicList> selectJgsScenicListList(JgsScenicList jgsScenicList)
    {
        return jgsScenicListMapper.selectJgsScenicListList(jgsScenicList);
    }

    /**
     * 新增景点引导牌
     *
     * @param jgsScenicList 景点引导牌
     * @return 结果
     */
    @Override
    public int insertJgsScenicList(JgsScenicList jgsScenicList)
    {
        return jgsScenicListMapper.insertJgsScenicList(jgsScenicList);
    }

    /**
     * 修改景点引导牌
     *
     * @param jgsScenicList 景点引导牌
     * @return 结果
     */
    @Override
    public int updateJgsScenicList(JgsScenicList jgsScenicList)
    {
        return jgsScenicListMapper.updateJgsScenicList(jgsScenicList);
    }

    /**
     * 批量删除景点引导牌
     *
     * @param ids 需要删除的景点引导牌主键
     * @return 结果
     */
    @Override
    public int deleteJgsScenicListByIds(Long[] ids)
    {
        return jgsScenicListMapper.deleteJgsScenicListByIds(ids);
    }

    /**
     * 删除景点引导牌信息
     *
     * @param id 景点引导牌主键
     * @return 结果
     */
    @Override
    public int deleteJgsScenicListById(Long id)
    {
        return jgsScenicListMapper.deleteJgsScenicListById(id);
    }
}

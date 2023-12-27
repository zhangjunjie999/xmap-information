package com.xmap.information.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.JgsParkCardMapper;
import com.xmap.information.domain.JgsParkCard;
import com.xmap.information.service.IJgsParkCardService;

/**
 * 内容发布引导牌管理Service业务层处理
 * 
 * @author xmap
 * @date 2023-11-07
 */
@Service
public class JgsParkCardServiceImpl implements IJgsParkCardService 
{
    @Autowired
    private JgsParkCardMapper jgsParkCardMapper;

    /**
     * 查询内容发布引导牌管理
     * 
     * @param id 内容发布引导牌管理主键
     * @return 内容发布引导牌管理
     */
    @Override
    public JgsParkCard selectJgsParkCardById(Long id)
    {
        return jgsParkCardMapper.selectJgsParkCardById(id);
    }

    /**
     * 查询内容发布引导牌管理列表
     * 
     * @param jgsParkCard 内容发布引导牌管理
     * @return 内容发布引导牌管理
     */
    @Override
    public List<JgsParkCard> selectJgsParkCardList(JgsParkCard jgsParkCard)
    {
        return jgsParkCardMapper.selectJgsParkCardList(jgsParkCard);
    }

    /**
     * 新增内容发布引导牌管理
     * 
     * @param jgsParkCard 内容发布引导牌管理
     * @return 结果
     */
    @Override
    public int insertJgsParkCard(JgsParkCard jgsParkCard)
    {
        return jgsParkCardMapper.insertJgsParkCard(jgsParkCard);
    }

    /**
     * 修改内容发布引导牌管理
     * 
     * @param jgsParkCard 内容发布引导牌管理
     * @return 结果
     */
    @Override
    public int updateJgsParkCard(JgsParkCard jgsParkCard)
    {
        return jgsParkCardMapper.updateJgsParkCard(jgsParkCard);
    }

    /**
     * 批量删除内容发布引导牌管理
     * 
     * @param ids 需要删除的内容发布引导牌管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsParkCardByIds(Long[] ids)
    {
        return jgsParkCardMapper.deleteJgsParkCardByIds(ids);
    }

    /**
     * 删除内容发布引导牌管理信息
     * 
     * @param id 内容发布引导牌管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsParkCardById(Long id)
    {
        return jgsParkCardMapper.deleteJgsParkCardById(id);
    }
}

package com.xmap.screen.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.screen.mapper.JgsParkGuideMapper;
import com.xmap.screen.domain.JgsParkGuide;
import com.xmap.screen.service.IJgsParkGuideService;

/**
 * 停车场引导牌管理Service业务层处理
 * 
 * @author xmap
 * @date 2023-11-03
 */
@Service
public class JgsParkGuideServiceImpl implements IJgsParkGuideService 
{
    @Autowired
    private JgsParkGuideMapper jgsParkGuideMapper;

    /**
     * 查询停车场引导牌管理
     * 
     * @param id 停车场引导牌管理主键
     * @return 停车场引导牌管理
     */
    @Override
    public JgsParkGuide selectJgsParkGuideById(Long id)
    {
        return jgsParkGuideMapper.selectJgsParkGuideById(id);
    }

    /**
     * 查询停车场引导牌管理列表
     * 
     * @param jgsParkGuide 停车场引导牌管理
     * @return 停车场引导牌管理
     */
    @Override
    public List<JgsParkGuide> selectJgsParkGuideList(JgsParkGuide jgsParkGuide)
    {
        return jgsParkGuideMapper.selectJgsParkGuideList(jgsParkGuide);
    }

    /**
     * 新增停车场引导牌管理
     * 
     * @param jgsParkGuide 停车场引导牌管理
     * @return 结果
     */
    @Override
    public int insertJgsParkGuide(JgsParkGuide jgsParkGuide)
    {
        return jgsParkGuideMapper.insertJgsParkGuide(jgsParkGuide);
    }

    /**
     * 修改停车场引导牌管理
     * 
     * @param jgsParkGuide 停车场引导牌管理
     * @return 结果
     */
    @Override
    public int updateJgsParkGuide(JgsParkGuide jgsParkGuide)
    {
        return jgsParkGuideMapper.updateJgsParkGuide(jgsParkGuide);
    }

    /**
     * 批量删除停车场引导牌管理
     * 
     * @param ids 需要删除的停车场引导牌管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsParkGuideByIds(Long[] ids)
    {
        return jgsParkGuideMapper.deleteJgsParkGuideByIds(ids);
    }

    /**
     * 删除停车场引导牌管理信息
     * 
     * @param id 停车场引导牌管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsParkGuideById(Long id)
    {
        return jgsParkGuideMapper.deleteJgsParkGuideById(id);
    }
}

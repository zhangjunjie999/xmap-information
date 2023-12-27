package com.xmap.information.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.JgsSiteMapper;
import com.xmap.information.domain.JgsSite;
import com.xmap.information.service.IJgsSiteService;

/**
 * 地点标志管理Service业务层处理
 * 
 * @author xmap
 * @date 2023-11-01
 */
@Service
public class JgsSiteServiceImpl implements IJgsSiteService 
{
    @Autowired
    private JgsSiteMapper jgsSiteMapper;

    /**
     * 查询地点标志管理
     * 
     * @param id 地点标志管理主键
     * @return 地点标志管理
     */
    @Override
    public JgsSite selectJgsSiteById(Long id)
    {
        return jgsSiteMapper.selectJgsSiteById(id);
    }

    /**
     * 查询地点标志管理列表
     * 
     * @param jgsSite 地点标志管理
     * @return 地点标志管理
     */
    @Override
    public List<JgsSite> selectJgsSiteList(JgsSite jgsSite)
    {
        return jgsSiteMapper.selectJgsSiteList(jgsSite);
    }

    /**
     * 新增地点标志管理
     * 
     * @param jgsSite 地点标志管理
     * @return 结果
     */
    @Override
    public int insertJgsSite(JgsSite jgsSite)
    {
        return jgsSiteMapper.insertJgsSite(jgsSite);
    }

    /**
     * 修改地点标志管理
     * 
     * @param jgsSite 地点标志管理
     * @return 结果
     */
    @Override
    public int updateJgsSite(JgsSite jgsSite)
    {
        return jgsSiteMapper.updateJgsSite(jgsSite);
    }

    /**
     * 批量删除地点标志管理
     * 
     * @param ids 需要删除的地点标志管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsSiteByIds(Long[] ids)
    {
        return jgsSiteMapper.deleteJgsSiteByIds(ids);
    }

    /**
     * 删除地点标志管理信息
     * 
     * @param id 地点标志管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsSiteById(Long id)
    {
        return jgsSiteMapper.deleteJgsSiteById(id);
    }
}

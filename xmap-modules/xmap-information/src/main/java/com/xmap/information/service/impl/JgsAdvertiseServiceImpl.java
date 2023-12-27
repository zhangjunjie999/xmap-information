package com.xmap.information.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.JgsAdvertiseMapper;
import com.xmap.information.domain.JgsAdvertise;
import com.xmap.information.service.IJgsAdvertiseService;

/**
 * 广告管理Service业务层处理
 * 
 * @author xmap
 * @date 2023-11-15
 */
@Service
public class JgsAdvertiseServiceImpl implements IJgsAdvertiseService 
{
    @Autowired
    private JgsAdvertiseMapper jgsAdvertiseMapper;

    /**
     * 查询广告管理
     * 
     * @param id 广告管理主键
     * @return 广告管理
     */
    @Override
    public JgsAdvertise selectJgsAdvertiseById(Long id)
    {
        return jgsAdvertiseMapper.selectJgsAdvertiseById(id);
    }

    /**
     * 查询广告管理列表
     * 
     * @param jgsAdvertise 广告管理
     * @return 广告管理
     */
    @Override
    public List<JgsAdvertise> selectJgsAdvertiseList(JgsAdvertise jgsAdvertise)
    {
        return jgsAdvertiseMapper.selectJgsAdvertiseList(jgsAdvertise);
    }

    /**
     * 新增广告管理
     * 
     * @param jgsAdvertise 广告管理
     * @return 结果
     */
    @Override
    public int insertJgsAdvertise(JgsAdvertise jgsAdvertise)
    {
        return jgsAdvertiseMapper.insertJgsAdvertise(jgsAdvertise);
    }

    /**
     * 修改广告管理
     * 
     * @param jgsAdvertise 广告管理
     * @return 结果
     */
    @Override
    public int updateJgsAdvertise(JgsAdvertise jgsAdvertise)
    {
        return jgsAdvertiseMapper.updateJgsAdvertise(jgsAdvertise);
    }

    /**
     * 批量删除广告管理
     * 
     * @param ids 需要删除的广告管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsAdvertiseByIds(Long[] ids)
    {
        return jgsAdvertiseMapper.deleteJgsAdvertiseByIds(ids);
    }

    /**
     * 删除广告管理信息
     * 
     * @param id 广告管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsAdvertiseById(Long id)
    {
        return jgsAdvertiseMapper.deleteJgsAdvertiseById(id);
    }
}

package com.xmap.information.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.JgsActivityMapper;
import com.xmap.information.domain.JgsActivity;
import com.xmap.information.service.IJgsActivityService;

/**
 * 活动盛事管理Service业务层处理
 * 
 * @author xmap
 * @date 2023-07-28
 */
@Service
public class JgsActivityServiceImpl implements IJgsActivityService 
{
    @Autowired
    private JgsActivityMapper jgsActivityMapper;

    /**
     * 查询活动盛事管理
     * 
     * @param id 活动盛事管理主键
     * @return 活动盛事管理
     */
    @Override
    public JgsActivity selectJgsActivityById(Long id)
    {
        return jgsActivityMapper.selectJgsActivityById(id);
    }

    /**
     * 查询活动盛事管理列表
     * 
     * @param jgsActivity 活动盛事管理
     * @return 活动盛事管理
     */
    @Override
    public List<JgsActivity> selectJgsActivityList(JgsActivity jgsActivity)
    {
        return jgsActivityMapper.selectJgsActivityList(jgsActivity);
    }

    /**
     * 新增活动盛事管理
     * 
     * @param jgsActivity 活动盛事管理
     * @return 结果
     */
    @Override
    public int insertJgsActivity(JgsActivity jgsActivity)
    {
        return jgsActivityMapper.insertJgsActivity(jgsActivity);
    }

    /**
     * 修改活动盛事管理
     * 
     * @param jgsActivity 活动盛事管理
     * @return 结果
     */
    @Override
    public int updateJgsActivity(JgsActivity jgsActivity)
    {
        return jgsActivityMapper.updateJgsActivity(jgsActivity);
    }

    /**
     * 批量删除活动盛事管理
     * 
     * @param ids 需要删除的活动盛事管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsActivityByIds(Long[] ids)
    {
        return jgsActivityMapper.deleteJgsActivityByIds(ids);
    }

    /**
     * 删除活动盛事管理信息
     * 
     * @param id 活动盛事管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsActivityById(Long id)
    {
        return jgsActivityMapper.deleteJgsActivityById(id);
    }
}

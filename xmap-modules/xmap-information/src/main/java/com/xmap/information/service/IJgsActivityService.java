package com.xmap.information.service;

import java.util.List;
import com.xmap.information.domain.JgsActivity;

/**
 * 活动盛事管理Service接口
 * 
 * @author xmap
 * @date 2023-07-28
 */
public interface IJgsActivityService 
{
    /**
     * 查询活动盛事管理
     * 
     * @param id 活动盛事管理主键
     * @return 活动盛事管理
     */
    public JgsActivity selectJgsActivityById(Long id);

    /**
     * 查询活动盛事管理列表
     * 
     * @param jgsActivity 活动盛事管理
     * @return 活动盛事管理集合
     */
    public List<JgsActivity> selectJgsActivityList(JgsActivity jgsActivity);

    /**
     * 新增活动盛事管理
     * 
     * @param jgsActivity 活动盛事管理
     * @return 结果
     */
    public int insertJgsActivity(JgsActivity jgsActivity);

    /**
     * 修改活动盛事管理
     * 
     * @param jgsActivity 活动盛事管理
     * @return 结果
     */
    public int updateJgsActivity(JgsActivity jgsActivity);

    /**
     * 批量删除活动盛事管理
     * 
     * @param ids 需要删除的活动盛事管理主键集合
     * @return 结果
     */
    public int deleteJgsActivityByIds(Long[] ids);

    /**
     * 删除活动盛事管理信息
     * 
     * @param id 活动盛事管理主键
     * @return 结果
     */
    public int deleteJgsActivityById(Long id);
}

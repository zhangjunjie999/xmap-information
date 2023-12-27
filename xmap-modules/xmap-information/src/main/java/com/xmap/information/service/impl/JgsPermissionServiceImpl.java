package com.xmap.information.service.impl;

import java.util.List;
import com.xmap.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.JgsPermissionMapper;
import com.xmap.information.domain.JgsPermission;
import com.xmap.information.service.IJgsPermissionService;

/**
 * 权限管理Service业务层处理
 * 
 * @author xmap
 * @date 2023-08-15
 */
@Service
public class JgsPermissionServiceImpl implements IJgsPermissionService 
{
    @Autowired
    private JgsPermissionMapper jgsPermissionMapper;

    /**
     * 查询权限管理
     * 
     * @param id 权限管理主键
     * @return 权限管理
     */
    @Override
    public JgsPermission selectJgsPermissionById(Long id)
    {
        return jgsPermissionMapper.selectJgsPermissionById(id);
    }

    /**
     * 查询权限管理列表
     * 
     * @param jgsPermission 权限管理
     * @return 权限管理
     */
    @Override
    public List<JgsPermission> selectJgsPermissionList(JgsPermission jgsPermission)
    {
        return jgsPermissionMapper.selectJgsPermissionList(jgsPermission);
    }

    /**
     * 新增权限管理
     * 
     * @param jgsPermission 权限管理
     * @return 结果
     */
    @Override
    public int insertJgsPermission(JgsPermission jgsPermission)
    {
        jgsPermission.setCreateTime(DateUtils.getNowDate());
        return jgsPermissionMapper.insertJgsPermission(jgsPermission);
    }

    /**
     * 修改权限管理
     * 
     * @param jgsPermission 权限管理
     * @return 结果
     */
    @Override
    public int updateJgsPermission(JgsPermission jgsPermission)
    {
        jgsPermission.setUpdateTime(DateUtils.getNowDate());
        return jgsPermissionMapper.updateJgsPermission(jgsPermission);
    }

    /**
     * 批量删除权限管理
     * 
     * @param ids 需要删除的权限管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsPermissionByIds(Long[] ids)
    {
        return jgsPermissionMapper.deleteJgsPermissionByIds(ids);
    }

    /**
     * 删除权限管理信息
     * 
     * @param id 权限管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsPermissionById(Long id)
    {
        return jgsPermissionMapper.deleteJgsPermissionById(id);
    }
}

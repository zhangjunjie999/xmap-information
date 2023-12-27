package com.xmap.information.service;

import java.util.List;
import com.xmap.information.domain.JgsPermission;

/**
 * 权限管理Service接口
 * 
 * @author xmap
 * @date 2023-08-15
 */
public interface IJgsPermissionService 
{
    /**
     * 查询权限管理
     * 
     * @param id 权限管理主键
     * @return 权限管理
     */
    public JgsPermission selectJgsPermissionById(Long id);

    /**
     * 查询权限管理列表
     * 
     * @param jgsPermission 权限管理
     * @return 权限管理集合
     */
    public List<JgsPermission> selectJgsPermissionList(JgsPermission jgsPermission);

    /**
     * 新增权限管理
     * 
     * @param jgsPermission 权限管理
     * @return 结果
     */
    public int insertJgsPermission(JgsPermission jgsPermission);

    /**
     * 修改权限管理
     * 
     * @param jgsPermission 权限管理
     * @return 结果
     */
    public int updateJgsPermission(JgsPermission jgsPermission);

    /**
     * 批量删除权限管理
     * 
     * @param ids 需要删除的权限管理主键集合
     * @return 结果
     */
    public int deleteJgsPermissionByIds(Long[] ids);

    /**
     * 删除权限管理信息
     * 
     * @param id 权限管理主键
     * @return 结果
     */
    public int deleteJgsPermissionById(Long id);
}

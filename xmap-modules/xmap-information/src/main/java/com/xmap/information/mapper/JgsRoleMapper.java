package com.xmap.information.mapper;

import java.util.List;
import com.xmap.information.domain.JgsRole;

/**
 * 角色管理Mapper接口
 * 
 * @author xmap
 * @date 2023-08-15
 */
public interface JgsRoleMapper 
{
    /**
     * 查询角色管理
     * 
     * @param id 角色管理主键
     * @return 角色管理
     */
    public JgsRole selectJgsRoleById(Long id);

    /**
     * 查询角色管理列表
     * 
     * @param jgsRole 角色管理
     * @return 角色管理集合
     */
    public List<JgsRole> selectJgsRoleList(JgsRole jgsRole);

    /**
     * 新增角色管理
     * 
     * @param jgsRole 角色管理
     * @return 结果
     */
    public int insertJgsRole(JgsRole jgsRole);

    /**
     * 修改角色管理
     * 
     * @param jgsRole 角色管理
     * @return 结果
     */
    public int updateJgsRole(JgsRole jgsRole);

    /**
     * 删除角色管理
     * 
     * @param id 角色管理主键
     * @return 结果
     */
    public int deleteJgsRoleById(Long id);

    /**
     * 批量删除角色管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJgsRoleByIds(Long[] ids);
}

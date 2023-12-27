package com.xmap.information.service.impl;

import java.util.List;
import com.xmap.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.JgsRoleMapper;
import com.xmap.information.domain.JgsRole;
import com.xmap.information.service.IJgsRoleService;

/**
 * 角色管理Service业务层处理
 * 
 * @author xmap
 * @date 2023-08-15
 */
@Service
public class JgsRoleServiceImpl implements IJgsRoleService 
{
    @Autowired
    private JgsRoleMapper jgsRoleMapper;

    /**
     * 查询角色管理
     * 
     * @param id 角色管理主键
     * @return 角色管理
     */
    @Override
    public JgsRole selectJgsRoleById(Long id)
    {
        return jgsRoleMapper.selectJgsRoleById(id);
    }

    /**
     * 查询角色管理列表
     * 
     * @param jgsRole 角色管理
     * @return 角色管理
     */
    @Override
    public List<JgsRole> selectJgsRoleList(JgsRole jgsRole)
    {
        return jgsRoleMapper.selectJgsRoleList(jgsRole);
    }

    /**
     * 新增角色管理
     * 
     * @param jgsRole 角色管理
     * @return 结果
     */
    @Override
    public int insertJgsRole(JgsRole jgsRole)
    {
        jgsRole.setCreateTime(DateUtils.getNowDate());
        return jgsRoleMapper.insertJgsRole(jgsRole);
    }

    /**
     * 修改角色管理
     * 
     * @param jgsRole 角色管理
     * @return 结果
     */
    @Override
    public int updateJgsRole(JgsRole jgsRole)
    {
        jgsRole.setUpdateTime(DateUtils.getNowDate());
        return jgsRoleMapper.updateJgsRole(jgsRole);
    }

    /**
     * 批量删除角色管理
     * 
     * @param ids 需要删除的角色管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsRoleByIds(Long[] ids)
    {
        return jgsRoleMapper.deleteJgsRoleByIds(ids);
    }

    /**
     * 删除角色管理信息
     * 
     * @param id 角色管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsRoleById(Long id)
    {
        return jgsRoleMapper.deleteJgsRoleById(id);
    }
}

package com.xmap.information.service.impl;

import java.util.List;
import com.xmap.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.JgsTypeMapper;
import com.xmap.information.domain.JgsType;
import com.xmap.information.service.IJgsTypeService;

/**
 * 类型管理Service业务层处理
 * 
 * @author xmap
 * @date 2023-10-08
 */
@Service
public class JgsTypeServiceImpl implements IJgsTypeService 
{
    @Autowired
    private JgsTypeMapper jgsTypeMapper;

    /**
     * 查询类型管理
     * 
     * @param id 类型管理主键
     * @return 类型管理
     */
    @Override
    public JgsType selectJgsTypeById(Long id)
    {
        return jgsTypeMapper.selectJgsTypeById(id);
    }

    /**
     * 查询类型管理列表
     * 
     * @param jgsType 类型管理
     * @return 类型管理
     */
    @Override
    public List<JgsType> selectJgsTypeList(JgsType jgsType)
    {
        return jgsTypeMapper.selectJgsTypeList(jgsType);
    }

    /**
     * 新增类型管理
     * 
     * @param jgsType 类型管理
     * @return 结果
     */
    @Override
    public int insertJgsType(JgsType jgsType)
    {
        jgsType.setCreateTime(DateUtils.getNowDate());
        return jgsTypeMapper.insertJgsType(jgsType);
    }

    /**
     * 修改类型管理
     * 
     * @param jgsType 类型管理
     * @return 结果
     */
    @Override
    public int updateJgsType(JgsType jgsType)
    {
        return jgsTypeMapper.updateJgsType(jgsType);
    }

    /**
     * 批量删除类型管理
     * 
     * @param ids 需要删除的类型管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsTypeByIds(Long[] ids)
    {
        return jgsTypeMapper.deleteJgsTypeByIds(ids);
    }

    /**
     * 删除类型管理信息
     * 
     * @param id 类型管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsTypeById(Long id)
    {
        return jgsTypeMapper.deleteJgsTypeById(id);
    }
}

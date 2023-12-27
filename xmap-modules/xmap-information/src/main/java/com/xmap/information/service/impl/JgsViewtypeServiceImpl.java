package com.xmap.information.service.impl;

import java.util.List;
import com.xmap.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.JgsViewtypeMapper;
import com.xmap.information.domain.JgsViewtype;
import com.xmap.information.service.IJgsViewtypeService;

/**
 * 景点类型管理Service业务层处理
 * 
 * @author xmap
 * @date 2023-11-01
 */
@Service
public class JgsViewtypeServiceImpl implements IJgsViewtypeService 
{
    @Autowired
    private JgsViewtypeMapper jgsViewtypeMapper;

    /**
     * 查询景点类型管理
     * 
     * @param id 景点类型管理主键
     * @return 景点类型管理
     */
    @Override
    public JgsViewtype selectJgsViewtypeById(Long id)
    {
        return jgsViewtypeMapper.selectJgsViewtypeById(id);
    }

    /**
     * 查询景点类型管理列表
     * 
     * @param jgsViewtype 景点类型管理
     * @return 景点类型管理
     */
    @Override
    public List<JgsViewtype> selectJgsViewtypeList(JgsViewtype jgsViewtype)
    {
        return jgsViewtypeMapper.selectJgsViewtypeList(jgsViewtype);
    }

    /**
     * 新增景点类型管理
     * 
     * @param jgsViewtype 景点类型管理
     * @return 结果
     */
    @Override
    public int insertJgsViewtype(JgsViewtype jgsViewtype)
    {
        jgsViewtype.setCreateTime(DateUtils.getNowDate());
        return jgsViewtypeMapper.insertJgsViewtype(jgsViewtype);
    }

    /**
     * 修改景点类型管理
     * 
     * @param jgsViewtype 景点类型管理
     * @return 结果
     */
    @Override
    public int updateJgsViewtype(JgsViewtype jgsViewtype)
    {
        return jgsViewtypeMapper.updateJgsViewtype(jgsViewtype);
    }

    /**
     * 批量删除景点类型管理
     * 
     * @param ids 需要删除的景点类型管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsViewtypeByIds(Long[] ids)
    {
        return jgsViewtypeMapper.deleteJgsViewtypeByIds(ids);
    }

    /**
     * 删除景点类型管理信息
     * 
     * @param id 景点类型管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsViewtypeById(Long id)
    {
        return jgsViewtypeMapper.deleteJgsViewtypeById(id);
    }
}

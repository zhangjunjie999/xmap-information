package com.xmap.information.service.impl;

import java.util.List;
import com.xmap.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.JgsVerifyMapper;
import com.xmap.information.domain.JgsVerify;
import com.xmap.information.service.IJgsVerifyService;

/**
 * 审核管理列Service业务层处理
 * 
 * @author xmap
 * @date 2023-11-07
 */
@Service
public class JgsVerifyServiceImpl implements IJgsVerifyService 
{
    @Autowired
    private JgsVerifyMapper jgsVerifyMapper;

    /**
     * 查询审核管理列
     * 
     * @param id 审核管理列主键
     * @return 审核管理列
     */
    @Override
    public JgsVerify selectJgsVerifyById(Long id)
    {
        return jgsVerifyMapper.selectJgsVerifyById(id);
    }

    /**
     * 查询审核管理列列表
     * 
     * @param jgsVerify 审核管理列
     * @return 审核管理列
     */
    @Override
    public List<JgsVerify> selectJgsVerifyList(JgsVerify jgsVerify)
    {
        return jgsVerifyMapper.selectJgsVerifyList(jgsVerify);
    }

    /**
     * 新增审核管理列
     * 
     * @param jgsVerify 审核管理列
     * @return 结果
     */
    @Override
    public int insertJgsVerify(JgsVerify jgsVerify)
    {
        jgsVerify.setCreateTime(DateUtils.getNowDate());
        return jgsVerifyMapper.insertJgsVerify(jgsVerify);
    }

    /**
     * 修改审核管理列
     * 
     * @param jgsVerify 审核管理列
     * @return 结果
     */
    @Override
    public int updateJgsVerify(JgsVerify jgsVerify)
    {
        return jgsVerifyMapper.updateJgsVerify(jgsVerify);
    }

    /**
     * 批量删除审核管理列
     * 
     * @param ids 需要删除的审核管理列主键
     * @return 结果
     */
    @Override
    public int deleteJgsVerifyByIds(Long[] ids)
    {
        return jgsVerifyMapper.deleteJgsVerifyByIds(ids);
    }

    /**
     * 删除审核管理列信息
     * 
     * @param id 审核管理列主键
     * @return 结果
     */
    @Override
    public int deleteJgsVerifyById(Long id)
    {
        return jgsVerifyMapper.deleteJgsVerifyById(id);
    }
}

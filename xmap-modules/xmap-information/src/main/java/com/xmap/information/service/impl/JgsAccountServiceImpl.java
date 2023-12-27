package com.xmap.information.service.impl;

import java.util.List;
import com.xmap.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.JgsAccountMapper;
import com.xmap.information.domain.JgsAccount;
import com.xmap.information.service.IJgsAccountService;

/**
 * 账户管理Service业务层处理
 * 
 * @author xmap
 * @date 2023-08-15
 */
@Service
public class JgsAccountServiceImpl implements IJgsAccountService 
{
    @Autowired
    private JgsAccountMapper jgsAccountMapper;

    /**
     * 查询账户管理
     * 
     * @param id 账户管理主键
     * @return 账户管理
     */
    @Override
    public JgsAccount selectJgsAccountById(Long id)
    {
        return jgsAccountMapper.selectJgsAccountById(id);
    }

    /**
     * 查询账户管理列表
     * 
     * @param jgsAccount 账户管理
     * @return 账户管理
     */
    @Override
    public List<JgsAccount> selectJgsAccountList(JgsAccount jgsAccount)
    {
        return jgsAccountMapper.selectJgsAccountList(jgsAccount);
    }

    /**
     * 新增账户管理
     * 
     * @param jgsAccount 账户管理
     * @return 结果
     */
    @Override
    public int insertJgsAccount(JgsAccount jgsAccount)
    {
        jgsAccount.setCreateTime(DateUtils.getNowDate());
        return jgsAccountMapper.insertJgsAccount(jgsAccount);
    }

    /**
     * 修改账户管理
     * 
     * @param jgsAccount 账户管理
     * @return 结果
     */
    @Override
    public int updateJgsAccount(JgsAccount jgsAccount)
    {
        jgsAccount.setUpdateTime(DateUtils.getNowDate());
        return jgsAccountMapper.updateJgsAccount(jgsAccount);
    }

    /**
     * 批量删除账户管理
     * 
     * @param ids 需要删除的账户管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsAccountByIds(Long[] ids)
    {
        return jgsAccountMapper.deleteJgsAccountByIds(ids);
    }

    /**
     * 删除账户管理信息
     * 
     * @param id 账户管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsAccountById(Long id)
    {
        return jgsAccountMapper.deleteJgsAccountById(id);
    }
}

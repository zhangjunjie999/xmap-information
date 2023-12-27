package com.xmap.information.mapper;

import java.util.List;
import com.xmap.information.domain.JgsAccount;

/**
 * 账户管理Mapper接口
 * 
 * @author xmap
 * @date 2023-08-15
 */
public interface JgsAccountMapper 
{
    /**
     * 查询账户管理
     * 
     * @param id 账户管理主键
     * @return 账户管理
     */
    public JgsAccount selectJgsAccountById(Long id);

    /**
     * 查询账户管理列表
     * 
     * @param jgsAccount 账户管理
     * @return 账户管理集合
     */
    public List<JgsAccount> selectJgsAccountList(JgsAccount jgsAccount);

    /**
     * 新增账户管理
     * 
     * @param jgsAccount 账户管理
     * @return 结果
     */
    public int insertJgsAccount(JgsAccount jgsAccount);

    /**
     * 修改账户管理
     * 
     * @param jgsAccount 账户管理
     * @return 结果
     */
    public int updateJgsAccount(JgsAccount jgsAccount);

    /**
     * 删除账户管理
     * 
     * @param id 账户管理主键
     * @return 结果
     */
    public int deleteJgsAccountById(Long id);

    /**
     * 批量删除账户管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJgsAccountByIds(Long[] ids);
}

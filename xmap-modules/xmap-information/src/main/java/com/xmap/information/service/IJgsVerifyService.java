package com.xmap.information.service;

import java.util.List;
import com.xmap.information.domain.JgsVerify;

/**
 * 审核管理列Service接口
 * 
 * @author xmap
 * @date 2023-11-07
 */
public interface IJgsVerifyService 
{
    /**
     * 查询审核管理列
     * 
     * @param id 审核管理列主键
     * @return 审核管理列
     */
    public JgsVerify selectJgsVerifyById(Long id);

    /**
     * 查询审核管理列列表
     * 
     * @param jgsVerify 审核管理列
     * @return 审核管理列集合
     */
    public List<JgsVerify> selectJgsVerifyList(JgsVerify jgsVerify);

    /**
     * 新增审核管理列
     * 
     * @param jgsVerify 审核管理列
     * @return 结果
     */
    public int insertJgsVerify(JgsVerify jgsVerify);

    /**
     * 修改审核管理列
     * 
     * @param jgsVerify 审核管理列
     * @return 结果
     */
    public int updateJgsVerify(JgsVerify jgsVerify);

    /**
     * 批量删除审核管理列
     * 
     * @param ids 需要删除的审核管理列主键集合
     * @return 结果
     */
    public int deleteJgsVerifyByIds(Long[] ids);

    /**
     * 删除审核管理列信息
     * 
     * @param id 审核管理列主键
     * @return 结果
     */
    public int deleteJgsVerifyById(Long id);
}

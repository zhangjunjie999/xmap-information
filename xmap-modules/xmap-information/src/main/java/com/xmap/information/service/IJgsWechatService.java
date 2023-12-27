package com.xmap.information.service;

import java.util.List;
import com.xmap.information.domain.JgsWechat;

/**
 * 微信管理Service接口
 * 
 * @author xmap
 * @date 2023-10-08
 */
public interface IJgsWechatService 
{
    /**
     * 查询微信管理
     * 
     * @param id 微信管理主键
     * @return 微信管理
     */
    public JgsWechat selectJgsWechatById(Long id);

    /**
     * 查询微信管理列表
     * 
     * @param jgsWechat 微信管理
     * @return 微信管理集合
     */
    public List<JgsWechat> selectJgsWechatList(JgsWechat jgsWechat);

    /**
     * 新增微信管理
     * 
     * @param jgsWechat 微信管理
     * @return 结果
     */
    public int insertJgsWechat(JgsWechat jgsWechat);

    /**
     * 修改微信管理
     * 
     * @param jgsWechat 微信管理
     * @return 结果
     */
    public int updateJgsWechat(JgsWechat jgsWechat);

    /**
     * 批量删除微信管理
     * 
     * @param ids 需要删除的微信管理主键集合
     * @return 结果
     */
    public int deleteJgsWechatByIds(Long[] ids);

    /**
     * 删除微信管理信息
     * 
     * @param id 微信管理主键
     * @return 结果
     */
    public int deleteJgsWechatById(Long id);
}

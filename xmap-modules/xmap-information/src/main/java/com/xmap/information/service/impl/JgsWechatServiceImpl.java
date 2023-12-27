package com.xmap.information.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.JgsWechatMapper;
import com.xmap.information.domain.JgsWechat;
import com.xmap.information.service.IJgsWechatService;

/**
 * 微信管理Service业务层处理
 * 
 * @author xmap
 * @date 2023-10-08
 */
@Service
public class JgsWechatServiceImpl implements IJgsWechatService 
{
    @Autowired
    private JgsWechatMapper jgsWechatMapper;

    /**
     * 查询微信管理
     * 
     * @param id 微信管理主键
     * @return 微信管理
     */
    @Override
    public JgsWechat selectJgsWechatById(Long id)
    {
        return jgsWechatMapper.selectJgsWechatById(id);
    }

    /**
     * 查询微信管理列表
     * 
     * @param jgsWechat 微信管理
     * @return 微信管理
     */
    @Override
    public List<JgsWechat> selectJgsWechatList(JgsWechat jgsWechat)
    {
        return jgsWechatMapper.selectJgsWechatList(jgsWechat);
    }

    /**
     * 新增微信管理
     * 
     * @param jgsWechat 微信管理
     * @return 结果
     */
    @Override
    public int insertJgsWechat(JgsWechat jgsWechat)
    {
        return jgsWechatMapper.insertJgsWechat(jgsWechat);
    }

    /**
     * 修改微信管理
     * 
     * @param jgsWechat 微信管理
     * @return 结果
     */
    @Override
    public int updateJgsWechat(JgsWechat jgsWechat)
    {
        return jgsWechatMapper.updateJgsWechat(jgsWechat);
    }

    /**
     * 批量删除微信管理
     * 
     * @param ids 需要删除的微信管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsWechatByIds(Long[] ids)
    {
        return jgsWechatMapper.deleteJgsWechatByIds(ids);
    }

    /**
     * 删除微信管理信息
     * 
     * @param id 微信管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsWechatById(Long id)
    {
        return jgsWechatMapper.deleteJgsWechatById(id);
    }
}

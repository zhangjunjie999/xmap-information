package com.xmap.information.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.JgsReplyMapper;
import com.xmap.information.domain.JgsReply;
import com.xmap.information.service.IJgsReplyService;

/**
 * 回复表Service业务层处理
 * 
 * @author xmap
 * @date 2023-07-28
 */
@Service
public class JgsReplyServiceImpl implements IJgsReplyService 
{
    @Autowired
    private JgsReplyMapper jgsReplyMapper;

    /**
     * 查询回复表
     * 
     * @param id 回复表主键
     * @return 回复表
     */
    @Override
    public JgsReply selectJgsReplyById(Long id)
    {
        return jgsReplyMapper.selectJgsReplyById(id);
    }

    /**
     * 查询回复表列表
     * 
     * @param jgsReply 回复表
     * @return 回复表
     */
    @Override
    public List<JgsReply> selectJgsReplyList(JgsReply jgsReply)
    {
        return jgsReplyMapper.selectJgsReplyList(jgsReply);
    }

    /**
     * 新增回复表
     * 
     * @param jgsReply 回复表
     * @return 结果
     */
    @Override
    public int insertJgsReply(JgsReply jgsReply)
    {
        return jgsReplyMapper.insertJgsReply(jgsReply);
    }

    /**
     * 修改回复表
     * 
     * @param jgsReply 回复表
     * @return 结果
     */
    @Override
    public int updateJgsReply(JgsReply jgsReply)
    {
        return jgsReplyMapper.updateJgsReply(jgsReply);
    }

    /**
     * 批量删除回复表
     * 
     * @param ids 需要删除的回复表主键
     * @return 结果
     */
    @Override
    public int deleteJgsReplyByIds(Long[] ids)
    {
        return jgsReplyMapper.deleteJgsReplyByIds(ids);
    }

    /**
     * 删除回复表信息
     * 
     * @param id 回复表主键
     * @return 结果
     */
    @Override
    public int deleteJgsReplyById(Long id)
    {
        return jgsReplyMapper.deleteJgsReplyById(id);
    }
}

package com.xmap.information.mapper;

import java.util.List;
import com.xmap.information.domain.JgsReply;

/**
 * 回复表Mapper接口
 * 
 * @author xmap
 * @date 2023-07-28
 */
public interface JgsReplyMapper 
{
    /**
     * 查询回复表
     * 
     * @param id 回复表主键
     * @return 回复表
     */
    public JgsReply selectJgsReplyById(Long id);

    /**
     * 查询回复表列表
     * 
     * @param jgsReply 回复表
     * @return 回复表集合
     */
    public List<JgsReply> selectJgsReplyList(JgsReply jgsReply);

    /**
     * 新增回复表
     * 
     * @param jgsReply 回复表
     * @return 结果
     */
    public int insertJgsReply(JgsReply jgsReply);

    /**
     * 修改回复表
     * 
     * @param jgsReply 回复表
     * @return 结果
     */
    public int updateJgsReply(JgsReply jgsReply);

    /**
     * 删除回复表
     * 
     * @param id 回复表主键
     * @return 结果
     */
    public int deleteJgsReplyById(Long id);

    /**
     * 批量删除回复表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJgsReplyByIds(Long[] ids);
}

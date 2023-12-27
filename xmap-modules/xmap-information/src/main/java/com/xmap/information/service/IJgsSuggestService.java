package com.xmap.information.service;

import java.util.List;
import com.xmap.information.domain.JgsSuggest;

/**
 * 游客建议Service接口
 * 
 * @author xmap
 * @date 2023-07-28
 */
public interface IJgsSuggestService 
{
    /**
     * 查询游客建议
     * 
     * @param id 游客建议主键
     * @return 游客建议
     */
    public JgsSuggest selectJgsSuggestById(Long id);

    /**
     * 查询游客建议列表
     * 
     * @param jgsSuggest 游客建议
     * @return 游客建议集合
     */
    public List<JgsSuggest> selectJgsSuggestList(JgsSuggest jgsSuggest);

    /**
     * 新增游客建议
     * 
     * @param jgsSuggest 游客建议
     * @return 结果
     */
    public int insertJgsSuggest(JgsSuggest jgsSuggest);

    /**
     * 修改游客建议
     * 
     * @param jgsSuggest 游客建议
     * @return 结果
     */
    public int updateJgsSuggest(JgsSuggest jgsSuggest);

    /**
     * 批量删除游客建议
     * 
     * @param ids 需要删除的游客建议主键集合
     * @return 结果
     */
    public int deleteJgsSuggestByIds(Long[] ids);

    /**
     * 删除游客建议信息
     * 
     * @param id 游客建议主键
     * @return 结果
     */
    public int deleteJgsSuggestById(Long id);
}

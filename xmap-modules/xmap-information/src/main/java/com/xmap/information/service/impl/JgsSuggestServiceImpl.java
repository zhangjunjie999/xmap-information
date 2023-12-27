package com.xmap.information.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.JgsSuggestMapper;
import com.xmap.information.domain.JgsSuggest;
import com.xmap.information.service.IJgsSuggestService;

/**
 * 游客建议Service业务层处理
 * 
 * @author xmap
 * @date 2023-07-28
 */
@Service
public class JgsSuggestServiceImpl implements IJgsSuggestService 
{
    @Autowired
    private JgsSuggestMapper jgsSuggestMapper;

    /**
     * 查询游客建议
     * 
     * @param id 游客建议主键
     * @return 游客建议
     */
    @Override
    public JgsSuggest selectJgsSuggestById(Long id)
    {
        return jgsSuggestMapper.selectJgsSuggestById(id);
    }

    /**
     * 查询游客建议列表
     * 
     * @param jgsSuggest 游客建议
     * @return 游客建议
     */
    @Override
    public List<JgsSuggest> selectJgsSuggestList(JgsSuggest jgsSuggest)
    {
        return jgsSuggestMapper.selectJgsSuggestList(jgsSuggest);
    }

    /**
     * 新增游客建议
     * 
     * @param jgsSuggest 游客建议
     * @return 结果
     */
    @Override
    public int insertJgsSuggest(JgsSuggest jgsSuggest)
    {
        return jgsSuggestMapper.insertJgsSuggest(jgsSuggest);
    }

    /**
     * 修改游客建议
     * 
     * @param jgsSuggest 游客建议
     * @return 结果
     */
    @Override
    public int updateJgsSuggest(JgsSuggest jgsSuggest)
    {
        return jgsSuggestMapper.updateJgsSuggest(jgsSuggest);
    }

    /**
     * 批量删除游客建议
     * 
     * @param ids 需要删除的游客建议主键
     * @return 结果
     */
    @Override
    public int deleteJgsSuggestByIds(Long[] ids)
    {
        return jgsSuggestMapper.deleteJgsSuggestByIds(ids);
    }

    /**
     * 删除游客建议信息
     * 
     * @param id 游客建议主键
     * @return 结果
     */
    @Override
    public int deleteJgsSuggestById(Long id)
    {
        return jgsSuggestMapper.deleteJgsSuggestById(id);
    }
}

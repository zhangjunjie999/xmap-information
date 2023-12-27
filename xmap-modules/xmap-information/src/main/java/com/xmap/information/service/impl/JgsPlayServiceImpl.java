package com.xmap.information.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.JgsPlayMapper;
import com.xmap.information.domain.JgsPlay;
import com.xmap.information.service.IJgsPlayService;

/**
 * 玩转井冈山Service业务层处理
 * 
 * @author xmap
 * @date 2023-07-28
 */
@Service
public class JgsPlayServiceImpl implements IJgsPlayService 
{
    @Autowired
    private JgsPlayMapper jgsPlayMapper;

    /**
     * 查询玩转井冈山
     * 
     * @param id 玩转井冈山主键
     * @return 玩转井冈山
     */
    @Override
    public JgsPlay selectJgsPlayById(Long id)
    {
        return jgsPlayMapper.selectJgsPlayById(id);
    }

    /**
     * 查询玩转井冈山列表
     * 
     * @param jgsPlay 玩转井冈山
     * @return 玩转井冈山
     */
    @Override
    public List<JgsPlay> selectJgsPlayList(JgsPlay jgsPlay)
    {
        return jgsPlayMapper.selectJgsPlayList(jgsPlay);
    }

    /**
     * 新增玩转井冈山
     * 
     * @param jgsPlay 玩转井冈山
     * @return 结果
     */
    @Override
    public int insertJgsPlay(JgsPlay jgsPlay)
    {
        return jgsPlayMapper.insertJgsPlay(jgsPlay);
    }

    /**
     * 修改玩转井冈山
     * 
     * @param jgsPlay 玩转井冈山
     * @return 结果
     */
    @Override
    public int updateJgsPlay(JgsPlay jgsPlay)
    {
        return jgsPlayMapper.updateJgsPlay(jgsPlay);
    }

    /**
     * 批量删除玩转井冈山
     * 
     * @param ids 需要删除的玩转井冈山主键
     * @return 结果
     */
    @Override
    public int deleteJgsPlayByIds(Long[] ids)
    {
        return jgsPlayMapper.deleteJgsPlayByIds(ids);
    }

    /**
     * 删除玩转井冈山信息
     * 
     * @param id 玩转井冈山主键
     * @return 结果
     */
    @Override
    public int deleteJgsPlayById(Long id)
    {
        return jgsPlayMapper.deleteJgsPlayById(id);
    }
}

package com.xmap.information.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.JgsBoardMapper;
import com.xmap.information.domain.JgsBoard;
import com.xmap.information.service.IJgsBoardService;

/**
 * 公告信息管理Service业务层处理
 * 
 * @author xmap
 * @date 2023-08-01
 */
@Service
public class JgsBoardServiceImpl implements IJgsBoardService 
{
    @Autowired
    private JgsBoardMapper jgsBoardMapper;

    /**
     * 查询公告信息管理
     * 
     * @param id 公告信息管理主键
     * @return 公告信息管理
     */
    @Override
    public JgsBoard selectJgsBoardById(Long id)
    {
        return jgsBoardMapper.selectJgsBoardById(id);
    }

    /**
     * 查询公告信息管理列表
     * 
     * @param jgsBoard 公告信息管理
     * @return 公告信息管理
     */
    @Override
    public List<JgsBoard> selectJgsBoardList(JgsBoard jgsBoard)
    {
        return jgsBoardMapper.selectJgsBoardList(jgsBoard);
    }

    /**
     * 新增公告信息管理
     * 
     * @param jgsBoard 公告信息管理
     * @return 结果
     */
    @Override
    public int insertJgsBoard(JgsBoard jgsBoard)
    {
        return jgsBoardMapper.insertJgsBoard(jgsBoard);
    }

    /**
     * 修改公告信息管理
     * 
     * @param jgsBoard 公告信息管理
     * @return 结果
     */
    @Override
    public int updateJgsBoard(JgsBoard jgsBoard)
    {
        return jgsBoardMapper.updateJgsBoard(jgsBoard);
    }

    /**
     * 批量删除公告信息管理
     * 
     * @param ids 需要删除的公告信息管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsBoardByIds(Long[] ids)
    {
        return jgsBoardMapper.deleteJgsBoardByIds(ids);
    }

    /**
     * 删除公告信息管理信息
     * 
     * @param id 公告信息管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsBoardById(Long id)
    {
        return jgsBoardMapper.deleteJgsBoardById(id);
    }
}

package com.xmap.information.mapper;

import java.util.List;
import com.xmap.information.domain.JgsBoard;

/**
 * 公告信息管理Mapper接口
 * 
 * @author xmap
 * @date 2023-08-01
 */
public interface JgsBoardMapper 
{
    /**
     * 查询公告信息管理
     * 
     * @param id 公告信息管理主键
     * @return 公告信息管理
     */
    public JgsBoard selectJgsBoardById(Long id);

    /**
     * 查询公告信息管理列表
     * 
     * @param jgsBoard 公告信息管理
     * @return 公告信息管理集合
     */
    public List<JgsBoard> selectJgsBoardList(JgsBoard jgsBoard);

    /**
     * 新增公告信息管理
     * 
     * @param jgsBoard 公告信息管理
     * @return 结果
     */
    public int insertJgsBoard(JgsBoard jgsBoard);

    /**
     * 修改公告信息管理
     * 
     * @param jgsBoard 公告信息管理
     * @return 结果
     */
    public int updateJgsBoard(JgsBoard jgsBoard);

    /**
     * 删除公告信息管理
     * 
     * @param id 公告信息管理主键
     * @return 结果
     */
    public int deleteJgsBoardById(Long id);

    /**
     * 批量删除公告信息管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJgsBoardByIds(Long[] ids);
}

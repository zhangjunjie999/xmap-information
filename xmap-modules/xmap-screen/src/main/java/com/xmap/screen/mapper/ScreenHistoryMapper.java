package com.xmap.screen.mapper;

import java.util.List;
import com.xmap.screen.domain.ScreenHistory;

/**
 * 设备对接历史Mapper接口
 * 
 * @author xmap
 * @date 2023-12-05
 */
public interface ScreenHistoryMapper 
{
    /**
     * 查询设备对接历史
     * 
     * @param historyId 设备对接历史主键
     * @return 设备对接历史
     */
    public ScreenHistory selectScreenHistoryByHistoryId(Long historyId);

    /**
     * 查询设备对接历史列表
     * 
     * @param screenHistory 设备对接历史
     * @return 设备对接历史集合
     */
    public List<ScreenHistory> selectScreenHistoryList(ScreenHistory screenHistory);

    /**
     * 新增设备对接历史
     * 
     * @param screenHistory 设备对接历史
     * @return 结果
     */
    public int insertScreenHistory(ScreenHistory screenHistory);

    /**
     * 修改设备对接历史
     * 
     * @param screenHistory 设备对接历史
     * @return 结果
     */
    public int updateScreenHistory(ScreenHistory screenHistory);

    /**
     * 删除设备对接历史
     * 
     * @param historyId 设备对接历史主键
     * @return 结果
     */
    public int deleteScreenHistoryByHistoryId(Long historyId);

    /**
     * 批量删除设备对接历史
     * 
     * @param historyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteScreenHistoryByHistoryIds(Long[] historyIds);
}

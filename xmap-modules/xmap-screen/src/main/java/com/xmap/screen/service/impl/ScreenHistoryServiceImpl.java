package com.xmap.screen.service.impl;

import java.util.List;

import com.xmap.common.core.utils.DateUtils;
import com.xmap.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.screen.mapper.ScreenHistoryMapper;
import com.xmap.screen.domain.ScreenHistory;
import com.xmap.screen.service.IScreenHistoryService;

/**
 * 设备对接历史Service业务层处理
 * 
 * @author xmap
 * @date 2023-12-05
 */
@Service
public class ScreenHistoryServiceImpl implements IScreenHistoryService 
{
    @Autowired
    private ScreenHistoryMapper screenHistoryMapper;

    /**
     * 查询设备对接历史
     * 
     * @param historyId 设备对接历史主键
     * @return 设备对接历史
     */
    @Override
    public ScreenHistory selectScreenHistoryByHistoryId(Long historyId)
    {
        return screenHistoryMapper.selectScreenHistoryByHistoryId(historyId);
    }

    /**
     * 查询设备对接历史列表
     * 
     * @param screenHistory 设备对接历史
     * @return 设备对接历史
     */
    @Override
    public List<ScreenHistory> selectScreenHistoryList(ScreenHistory screenHistory)
    {
        return screenHistoryMapper.selectScreenHistoryList(screenHistory);
    }

    /**
     * 新增设备对接历史
     * 
     * @param screenHistory 设备对接历史
     * @return 结果
     */
    @Override
    public int insertScreenHistory(ScreenHistory screenHistory)
    {
        //设置创建时间
        screenHistory.setCreateDate(DateUtils.getNowDate());
        //设置创建人
        screenHistory.setCreateBy(SecurityUtils.getUsername());
        return screenHistoryMapper.insertScreenHistory(screenHistory);
    }

    /**
     * 修改设备对接历史
     * 
     * @param screenHistory 设备对接历史
     * @return 结果
     */
    @Override
    public int updateScreenHistory(ScreenHistory screenHistory)
    {
        return screenHistoryMapper.updateScreenHistory(screenHistory);
    }

    /**
     * 批量删除设备对接历史
     * 
     * @param historyIds 需要删除的设备对接历史主键
     * @return 结果
     */
    @Override
    public int deleteScreenHistoryByHistoryIds(Long[] historyIds)
    {
        return screenHistoryMapper.deleteScreenHistoryByHistoryIds(historyIds);
    }

    /**
     * 删除设备对接历史信息
     * 
     * @param historyId 设备对接历史主键
     * @return 结果
     */
    @Override
    public int deleteScreenHistoryByHistoryId(Long historyId)
    {
        return screenHistoryMapper.deleteScreenHistoryByHistoryId(historyId);
    }
}

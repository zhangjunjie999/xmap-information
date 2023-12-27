package com.xmap.information.service.impl;

import java.util.List;
import com.xmap.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.TravelNewsMapper;
import com.xmap.information.domain.TravelNews;
import com.xmap.information.service.ITravelNewsService;

/**
 * 旅游新闻列表Service业务层处理
 * 
 * @author xmap
 * @date 2023-07-04
 */
@Service
public class TravelNewsServiceImpl implements ITravelNewsService 
{
    @Autowired
    private TravelNewsMapper travelNewsMapper;

    /**
     * 查询旅游新闻列表
     * 
     * @param newsId 旅游新闻列表主键
     * @return 旅游新闻列表
     */
    @Override
    public TravelNews selectTravelNewsByNewsId(Long newsId)
    {
        return travelNewsMapper.selectTravelNewsByNewsId(newsId);
    }

    /**
     * 查询旅游新闻列表列表
     * 
     * @param travelNews 旅游新闻列表
     * @return 旅游新闻列表
     */
    @Override
    public List<TravelNews> selectTravelNewsList(TravelNews travelNews)
    {
        return travelNewsMapper.selectTravelNewsList(travelNews);
    }

    /**
     * 新增旅游新闻列表
     * 
     * @param travelNews 旅游新闻列表
     * @return 结果
     */
    @Override
    public int insertTravelNews(TravelNews travelNews)
    {
        travelNews.setCreateTime(DateUtils.getNowDate());
        return travelNewsMapper.insertTravelNews(travelNews);
    }

    /**
     * 修改旅游新闻列表
     * 
     * @param travelNews 旅游新闻列表
     * @return 结果
     */
    @Override
    public int updateTravelNews(TravelNews travelNews)
    {
        return travelNewsMapper.updateTravelNews(travelNews);
    }

    /**
     * 批量删除旅游新闻列表
     * 
     * @param newsIds 需要删除的旅游新闻列表主键
     * @return 结果
     */
    @Override
    public int deleteTravelNewsByNewsIds(Long[] newsIds)
    {
        return travelNewsMapper.deleteTravelNewsByNewsIds(newsIds);
    }

    /**
     * 删除旅游新闻列表信息
     * 
     * @param newsId 旅游新闻列表主键
     * @return 结果
     */
    @Override
    public int deleteTravelNewsByNewsId(Long newsId)
    {
        return travelNewsMapper.deleteTravelNewsByNewsId(newsId);
    }
}

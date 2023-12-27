package com.xmap.information.mapper;

import java.util.List;
import com.xmap.information.domain.TravelNews;

/**
 * 旅游新闻列表Mapper接口
 * 
 * @author xmap
 * @date 2023-07-04
 */
public interface TravelNewsMapper 
{
    /**
     * 查询旅游新闻列表
     * 
     * @param newsId 旅游新闻列表主键
     * @return 旅游新闻列表
     */
    public TravelNews selectTravelNewsByNewsId(Long newsId);

    /**
     * 查询旅游新闻列表列表
     * 
     * @param travelNews 旅游新闻列表
     * @return 旅游新闻列表集合
     */
    public List<TravelNews> selectTravelNewsList(TravelNews travelNews);

    /**
     * 新增旅游新闻列表
     * 
     * @param travelNews 旅游新闻列表
     * @return 结果
     */
    public int insertTravelNews(TravelNews travelNews);

    /**
     * 修改旅游新闻列表
     * 
     * @param travelNews 旅游新闻列表
     * @return 结果
     */
    public int updateTravelNews(TravelNews travelNews);

    /**
     * 删除旅游新闻列表
     * 
     * @param newsId 旅游新闻列表主键
     * @return 结果
     */
    public int deleteTravelNewsByNewsId(Long newsId);

    /**
     * 批量删除旅游新闻列表
     * 
     * @param newsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTravelNewsByNewsIds(Long[] newsIds);
}

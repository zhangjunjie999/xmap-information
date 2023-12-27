package com.xmap.information.service;

import java.util.List;
import com.xmap.information.domain.InformationWeather;

/**
 * 天气Service接口
 * 
 * @author xmap
 * @date 2023-11-17
 */
public interface IInformationWeatherService 
{
    /**
     * 查询天气
     * 
     * @param weatherId 天气主键
     * @return 天气
     */
    public InformationWeather selectInformationWeatherByWeatherId(Long weatherId);

    /**
     * 查询天气列表
     * 
     * @param informationWeather 天气
     * @return 天气集合
     */
    public List<InformationWeather> selectInformationWeatherList(InformationWeather informationWeather);

    /**
     * 新增天气
     * 
     * @param informationWeather 天气
     * @return 结果
     */
    public int insertInformationWeather(InformationWeather informationWeather);

    /**
     * 修改天气
     * 
     * @param informationWeather 天气
     * @return 结果
     */
    public int updateInformationWeather(InformationWeather informationWeather);

    /**
     * 批量删除天气
     * 
     * @param weatherIds 需要删除的天气主键集合
     * @return 结果
     */
    public int deleteInformationWeatherByWeatherIds(Long[] weatherIds);

    /**
     * 删除天气信息
     * 
     * @param weatherId 天气主键
     * @return 结果
     */
    public int deleteInformationWeatherByWeatherId(Long weatherId);
}

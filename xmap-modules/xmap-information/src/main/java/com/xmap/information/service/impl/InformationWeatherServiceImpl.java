package com.xmap.information.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.InformationWeatherMapper;
import com.xmap.information.domain.InformationWeather;
import com.xmap.information.service.IInformationWeatherService;

/**
 * 天气Service业务层处理
 * 
 * @author xmap
 * @date 2023-11-17
 */
@Service
public class InformationWeatherServiceImpl implements IInformationWeatherService 
{
    @Autowired
    private InformationWeatherMapper informationWeatherMapper;

    /**
     * 查询天气
     * 
     * @param weatherId 天气主键
     * @return 天气
     */
    @Override
    public InformationWeather selectInformationWeatherByWeatherId(Long weatherId)
    {
        return informationWeatherMapper.selectInformationWeatherByWeatherId(weatherId);
    }

    /**
     * 查询天气列表
     * 
     * @param informationWeather 天气
     * @return 天气
     */
    @Override
    public List<InformationWeather> selectInformationWeatherList(InformationWeather informationWeather)
    {
        return informationWeatherMapper.selectInformationWeatherList(informationWeather);
    }

    /**
     * 新增天气
     * 
     * @param informationWeather 天气
     * @return 结果
     */
    @Override
    public int insertInformationWeather(InformationWeather informationWeather)
    {
        return informationWeatherMapper.insertInformationWeather(informationWeather);
    }

    /**
     * 修改天气
     * 
     * @param informationWeather 天气
     * @return 结果
     */
    @Override
    public int updateInformationWeather(InformationWeather informationWeather)
    {
        return informationWeatherMapper.updateInformationWeather(informationWeather);
    }

    /**
     * 批量删除天气
     * 
     * @param weatherIds 需要删除的天气主键
     * @return 结果
     */
    @Override
    public int deleteInformationWeatherByWeatherIds(Long[] weatherIds)
    {
        return informationWeatherMapper.deleteInformationWeatherByWeatherIds(weatherIds);
    }

    /**
     * 删除天气信息
     * 
     * @param weatherId 天气主键
     * @return 结果
     */
    @Override
    public int deleteInformationWeatherByWeatherId(Long weatherId)
    {
        return informationWeatherMapper.deleteInformationWeatherByWeatherId(weatherId);
    }
}

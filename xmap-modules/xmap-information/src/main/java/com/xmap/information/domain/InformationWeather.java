package com.xmap.information.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xmap.common.core.annotation.Excel;
import com.xmap.common.core.web.domain.BaseEntity;

/**
 * 天气对象 information_weather
 * 
 * @author xmap
 * @date 2023-11-17
 */
public class InformationWeather extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 天气编号 */
    private Long weatherId;

    /** 地区 */
    @Excel(name = "地区")
    private String weatherCity;

    /** 天气 */
    @Excel(name = "天气")
    private String weatherInfo;

    /** 当前温度 */
    @Excel(name = "当前温度")
    private String weatherTemperature;

    /** 低温 */
    @Excel(name = "低温")
    private String weatherLow;

    /** 高温 */
    @Excel(name = "高温")
    private String weatherHigh;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date weatherTime;

    /** 湿度 */
    @Excel(name = "湿度")
    private String weatherHumidity;

    /** 风力 */
    @Excel(name = "风力")
    private String weatherPower;

    /** 风向 */
    @Excel(name = "风向")
    private String weatherDirection;

    /** 太阳升起 */
    @Excel(name = "太阳升起")
    private String weatherRise;

    /** 太阳落下 */
    @Excel(name = "太阳落下")
    private String weatherDown;

    /** 空气质量 */
    @Excel(name = "空气质量")
    private String weatherAir;

    public void setWeatherId(Long weatherId) 
    {
        this.weatherId = weatherId;
    }

    public Long getWeatherId() 
    {
        return weatherId;
    }
    public void setWeatherCity(String weatherCity) 
    {
        this.weatherCity = weatherCity;
    }

    public String getWeatherCity() 
    {
        return weatherCity;
    }
    public void setWeatherInfo(String weatherInfo) 
    {
        this.weatherInfo = weatherInfo;
    }

    public String getWeatherInfo() 
    {
        return weatherInfo;
    }
    public void setWeatherTemperature(String weatherTemperature) 
    {
        this.weatherTemperature = weatherTemperature;
    }

    public String getWeatherTemperature() 
    {
        return weatherTemperature;
    }
    public void setWeatherLow(String weatherLow) 
    {
        this.weatherLow = weatherLow;
    }

    public String getWeatherLow() 
    {
        return weatherLow;
    }
    public void setWeatherHigh(String weatherHigh) 
    {
        this.weatherHigh = weatherHigh;
    }

    public String getWeatherHigh() 
    {
        return weatherHigh;
    }
    public void setWeatherTime(Date weatherTime) 
    {
        this.weatherTime = weatherTime;
    }

    public Date getWeatherTime() 
    {
        return weatherTime;
    }
    public void setWeatherHumidity(String weatherHumidity) 
    {
        this.weatherHumidity = weatherHumidity;
    }

    public String getWeatherHumidity() 
    {
        return weatherHumidity;
    }
    public void setWeatherPower(String weatherPower) 
    {
        this.weatherPower = weatherPower;
    }

    public String getWeatherPower() 
    {
        return weatherPower;
    }
    public void setWeatherDirection(String weatherDirection) 
    {
        this.weatherDirection = weatherDirection;
    }

    public String getWeatherDirection() 
    {
        return weatherDirection;
    }
    public void setWeatherRise(String weatherRise) 
    {
        this.weatherRise = weatherRise;
    }

    public String getWeatherRise() 
    {
        return weatherRise;
    }
    public void setWeatherDown(String weatherDown) 
    {
        this.weatherDown = weatherDown;
    }

    public String getWeatherDown() 
    {
        return weatherDown;
    }
    public void setWeatherAir(String weatherAir) 
    {
        this.weatherAir = weatherAir;
    }

    public String getWeatherAir() 
    {
        return weatherAir;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("weatherId", getWeatherId())
            .append("weatherCity", getWeatherCity())
            .append("weatherInfo", getWeatherInfo())
            .append("weatherTemperature", getWeatherTemperature())
            .append("weatherLow", getWeatherLow())
            .append("weatherHigh", getWeatherHigh())
            .append("weatherTime", getWeatherTime())
            .append("weatherHumidity", getWeatherHumidity())
            .append("weatherPower", getWeatherPower())
            .append("weatherDirection", getWeatherDirection())
            .append("weatherRise", getWeatherRise())
            .append("weatherDown", getWeatherDown())
            .append("weatherAir", getWeatherAir())
            .toString();
    }
}

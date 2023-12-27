package com.xmap.information.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xmap.common.core.annotation.Excel;
import com.xmap.common.core.web.domain.BaseEntity;

import java.util.Date;

/**
 * 停车场引导牌管理对象 jgs_park_guide_list
 *
 * @author xmap
 * @date 2023-11-03
 */
public class ParkWeather extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String city;//地区
    private String time;//天气上一次更新时间
    private String shidu;//湿度
    private String quality;//空气质量
    private String wendu;//温度

    private String high;//最高温度
    private String low;//最低温度
    private String sunrise;//日出时间
    private String sunset;//日落时间
    private String aqi;//空气指数
    private String fx;//风向
    private String fl;//风速
    private String type;//天气
    private String notice;//温馨提醒



    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCity()
    {
        return city;
    }
    public void setTime(String time)
    {
        this.time = time;
    }

    public String getTime()
    {
        return time;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setWendu(String wendu)
    {
        this.wendu = wendu;
    }

    public String getWendu()
    {
        return wendu;
    }

    public void setHigh(String high)
    {
        this.high = high;
    }

    public String getHigh()
    {
        return high;
    }

    public void setLow(String low)
    {
        this.low = low;
    }

    public String getLow()
    {
        return low;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("city", getCity())
                .append("time", getTime())
                .append("type", getType())
                .append("wendu", getWendu())
                .append("high", getHigh())
                .append("low", getLow())
                .toString();
    }
}




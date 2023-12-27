package com.xmap.information.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xmap.common.core.annotation.Excel;
import com.xmap.common.core.web.domain.BaseEntity;

/**
 * 停车场引导牌管理对象 jgs_park_guide_list
 *
 * @author xmap
 * @date 2023-11-03
 */
public class JgsParkGuide extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 站点名 */
    @Excel(name = "站点名")
    private String siteName;

    /** 通知公告 */
    @Excel(name = "通知公告")
    private String notice;

    /** 天气 */
    @Excel(name = "天气")
    private String weather;

    /** 图标 */
    @Excel(name = "图标")
    private String icon;

    /** 距离 */
    @Excel(name = "距离")
    private Long distance;

    /** 位置 */
    @Excel(name = "位置")
    private String location;

    /** 剩余车位 */
    @Excel(name = "剩余车位")
    private Long availablePark;

    /** 已用车位 */
    @Excel(name = "已用车位")
    private Long occupiedPark;

    /** 周边停车场 */
    @Excel(name = "周边停车场")
    private String nearbyPark;

    /** 空余位数 */
    @Excel(name = "空余位数")
    private Long availableSpace;

    /** 救援电话 */
    @Excel(name = "救援电话")
    private String emergencyPhone;


    @Excel(name = "审核状态")
    private String verifyStatus;

    /** 时间 */
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Excel(name = "时间")
    private String dateTime;

    /** 星期 */
    @Excel(name = "星期")
    private String weekDay;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setSiteName(String siteName)
    {
        this.siteName = siteName;
    }

    public String getSiteName()
    {
        return siteName;
    }
    public void setNotice(String notice)
    {
        this.notice = notice;
    }

    public String getNotice()
    {
        return notice;
    }
    public void setWeather(String weather)
    {
        this.weather = weather;
    }

    public String getWeather()
    {
        return weather;
    }
    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public String getIcon()
    {
        return icon;
    }
    public void setDistance(Long distance)
    {
        this.distance = distance;
    }

    public Long getDistance()
    {
        return distance;
    }
    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getLocation()
    {
        return location;
    }
    public void setAvailablePark(Long availablePark)
    {
        this.availablePark = availablePark;
    }

    public Long getAvailablePark()
    {
        return availablePark;
    }
    public void setOccupiedPark(Long occupiedPark)
    {
        this.occupiedPark = occupiedPark;
    }

    public Long getOccupiedPark()
    {
        return occupiedPark;
    }
    public void setNearbyPark(String nearbyPark)
    {
        this.nearbyPark = nearbyPark;
    }

    public String getNearbyPark()
    {
        return nearbyPark;
    }
    public void setAvailableSpace(Long availableSpace)
    {
        this.availableSpace = availableSpace;
    }

    public Long getAvailableSpace()
    {
        return availableSpace;
    }
    public void setEmergencyPhone(String emergencyPhone)
    {
        this.emergencyPhone = emergencyPhone;
    }

    public String getEmergencyPhone()
    {
        return emergencyPhone;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }


    public void setDateTime(String dateTime)
    {
        this.dateTime = dateTime;
    }

    public String getDateTime()
    {
        return dateTime;
    }

    public void setWeekDay(String weekDay)
    {
        this.weekDay = weekDay;
    }

    public String getWeekDay()
    {
        return weekDay;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("siteName", getSiteName())
            .append("notice", getNotice())
            .append("weather", getWeather())
            .append("icon", getIcon())
            .append("distance", getDistance())
            .append("location", getLocation())
            .append("availablePark", getAvailablePark())
            .append("occupiedPark", getOccupiedPark())
            .append("nearbyPark", getNearbyPark())
            .append("availableSpace", getAvailableSpace())
            .append("emergencyPhone", getEmergencyPhone())
                .append("verifyStatus",getVerifyStatus())
                .append("dateTime", getDateTime())
                .append("weekDay", getWeekDay())
            .toString();
    }
}

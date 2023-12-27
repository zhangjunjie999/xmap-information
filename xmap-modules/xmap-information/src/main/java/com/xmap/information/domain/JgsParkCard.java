package com.xmap.information.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xmap.common.core.annotation.Excel;
import com.xmap.common.core.web.domain.BaseEntity;

/**
 * 内容发布引导牌管理对象 jgs_park_guide
 *
 * @author xmap
 * @date 2023-11-07
 */
public class JgsParkCard extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    @Excel(name = "类型")
    private String type;

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

    /** 审核状态 */
    @Excel(name = "审核状态")
    private String verifyStatus;

    /** 审核人 */
    @Excel(name = "审核人")
    private String verifyPeople;

    /** 审核日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date verifyDate;

    /** 发布人 */
    @Excel(name = "发布人")
    private String publisher;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publishTime;

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
    public void setVerifyStatus(String verifyStatus)
    {
        this.verifyStatus = verifyStatus;
    }

    public String getVerifyStatus()
    {
        return verifyStatus;
    }
    public void setVerifyPeople(String verifyPeople)
    {
        this.verifyPeople = verifyPeople;
    }

    public String getVerifyPeople()
    {
        return verifyPeople;
    }
    public void setVerifyDate(Date verifyDate)
    {
        this.verifyDate = verifyDate;
    }

    public Date getVerifyDate()
    {
        return verifyDate;
    }
    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }

    public String getPublisher()
    {
        return publisher;
    }
    public void setPublishTime(Date publishTime)
    {
        this.publishTime = publishTime;
    }

    public Date getPublishTime()
    {
        return publishTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
            .append("verifyStatus", getVerifyStatus())
            .append("verifyPeople", getVerifyPeople())
            .append("verifyDate", getVerifyDate())
            .append("publisher", getPublisher())
            .append("publishTime", getPublishTime()).append("getType",getType())
            .toString();
    }
}

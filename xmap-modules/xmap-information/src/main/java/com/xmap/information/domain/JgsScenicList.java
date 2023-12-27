package com.xmap.information.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xmap.common.core.annotation.Excel;
import com.xmap.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 景点引导牌对象 jgs_scenic_list
 *
 * @author xmap
 * @date 2023-11-22
 */
public class JgsScenicList extends BaseEntity
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

    /** 在园人数 */
    @Excel(name = "在园人数")
    private Long attendance;

    /** 剩余停车位 */
    @Excel(name = "剩余停车位")
    private Long parking;

    /** 票价 */
    @Excel(name = "票价")
    private Long ticket;

    /** 建议下一个景点 */
    @Excel(name = "建议下一个景点")
    private String nextScenic;

    /** 景点人数 */
    @Excel(name = "景点人数")
    private Long numberScenic;

    /** 附近景点 */
    @Excel(name = "附近景点")
    private String nearbyScenic;

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
    public void setAttendance(Long attendance)
    {
        this.attendance = attendance;
    }

    public Long getAttendance()
    {
        return attendance;
    }
    public void setParking(Long parking)
    {
        this.parking = parking;
    }

    public Long getParking()
    {
        return parking;
    }
    public void setTicket(Long ticket)
    {
        this.ticket = ticket;
    }

    public Long getTicket()
    {
        return ticket;
    }
    public void setNextScenic(String nextScenic)
    {
        this.nextScenic = nextScenic;
    }

    public String getNextScenic()
    {
        return nextScenic;
    }
    public void setNumberScenic(Long numberScenic)
    {
        this.numberScenic = numberScenic;
    }

    public Long getNumberScenic()
    {
        return numberScenic;
    }
    public void setNearbyScenic(String nearbyScenic)
    {
        this.nearbyScenic = nearbyScenic;
    }

    public String getNearbyScenic()
    {
        return nearbyScenic;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("siteName", getSiteName())
            .append("notice", getNotice())
            .append("weather", getWeather())
            .append("attendance", getAttendance())
            .append("parking", getParking())
            .append("ticket", getTicket())
            .append("nextScenic", getNextScenic())
            .append("numberScenic", getNumberScenic())
            .append("nearbyScenic", getNearbyScenic())
            .append("emergencyPhone", getEmergencyPhone())
            .append("verifyStatus", getVerifyStatus())
            .append("verifyPeople", getVerifyPeople())
            .append("verifyDate", getVerifyDate())
            .append("publisher", getPublisher())
            .append("publishTime", getPublishTime())
            .toString();
    }
}

package com.xmap.information.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xmap.common.core.annotation.Excel;
import com.xmap.common.core.web.domain.BaseEntity;

/**
 * 广告管理对象 jgs_advertise_list
 * 
 * @author xmap
 * @date 2023-11-15
 */
public class JgsAdvertise extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 广告标题 */
    @Excel(name = "广告标题")
    private String advertiseTitle;

    /** 广告描述 */
    @Excel(name = "广告描述")
    private String advertiseDescription;

    /** 广告类型 */
    @Excel(name = "广告类型")
    private String advertiseType;

    /** 广告内容 */
    @Excel(name = "广告内容")
    private String advertiseContent;

    /** 广告位 */
    @Excel(name = "广告位")
    private String advertiseLocation;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAdvertiseTitle(String advertiseTitle) 
    {
        this.advertiseTitle = advertiseTitle;
    }

    public String getAdvertiseTitle() 
    {
        return advertiseTitle;
    }
    public void setAdvertiseDescription(String advertiseDescription) 
    {
        this.advertiseDescription = advertiseDescription;
    }

    public String getAdvertiseDescription() 
    {
        return advertiseDescription;
    }
    public void setAdvertiseType(String advertiseType) 
    {
        this.advertiseType = advertiseType;
    }

    public String getAdvertiseType() 
    {
        return advertiseType;
    }
    public void setAdvertiseContent(String advertiseContent) 
    {
        this.advertiseContent = advertiseContent;
    }

    public String getAdvertiseContent() 
    {
        return advertiseContent;
    }
    public void setAdvertiseLocation(String advertiseLocation) 
    {
        this.advertiseLocation = advertiseLocation;
    }

    public String getAdvertiseLocation() 
    {
        return advertiseLocation;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("advertiseTitle", getAdvertiseTitle())
            .append("advertiseDescription", getAdvertiseDescription())
            .append("advertiseType", getAdvertiseType())
            .append("advertiseContent", getAdvertiseContent())
            .append("advertiseLocation", getAdvertiseLocation())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .toString();
    }
}

package com.xmap.information.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xmap.common.core.annotation.Excel;
import com.xmap.common.core.web.domain.BaseEntity;

/**
 * 统计分析对象 jgs_analy_list
 * 
 * @author xmap
 * @date 2023-10-09
 */
public class JgsAnaly extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 统计ID */
    private Long statisticsId;

    /** 游客流量分析 */
    @Excel(name = "游客流量分析")
    private String visitorCount;

    /** 门票销售统计 */
    @Excel(name = "门票销售统计")
    private String ticketSales;

    /** 景点热度统计 */
    @Excel(name = "景点热度统计")
    private Long attractionStatistics;

    /** 员工管理统计 */
    @Excel(name = "员工管理统计")
    private String employeeManage;

    /** 统计时间 */
    @Excel(name = "统计时间")
    private String statisticsTime;

    public void setStatisticsId(Long statisticsId) 
    {
        this.statisticsId = statisticsId;
    }

    public Long getStatisticsId() 
    {
        return statisticsId;
    }
    public void setVisitorCount(String visitorCount) 
    {
        this.visitorCount = visitorCount;
    }

    public String getVisitorCount() 
    {
        return visitorCount;
    }
    public void setTicketSales(String ticketSales) 
    {
        this.ticketSales = ticketSales;
    }

    public String getTicketSales() 
    {
        return ticketSales;
    }
    public void setAttractionStatistics(Long attractionStatistics) 
    {
        this.attractionStatistics = attractionStatistics;
    }

    public Long getAttractionStatistics() 
    {
        return attractionStatistics;
    }
    public void setEmployeeManage(String employeeManage) 
    {
        this.employeeManage = employeeManage;
    }

    public String getEmployeeManage() 
    {
        return employeeManage;
    }
    public void setStatisticsTime(String statisticsTime) 
    {
        this.statisticsTime = statisticsTime;
    }

    public String getStatisticsTime() 
    {
        return statisticsTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("statisticsId", getStatisticsId())
            .append("visitorCount", getVisitorCount())
            .append("ticketSales", getTicketSales())
            .append("attractionStatistics", getAttractionStatistics())
            .append("employeeManage", getEmployeeManage())
            .append("statisticsTime", getStatisticsTime())
            .toString();
    }
}

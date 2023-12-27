package com.xmap.information.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xmap.common.core.annotation.Excel;
import com.xmap.common.core.web.domain.BaseEntity;

/**
 * 旅游新闻列表对象 travel_news_list
 * 
 * @author xmap
 * @date 2023-07-04
 */
public class TravelNews extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 新闻ID */
    private Long newsId;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 创建人 */
    @Excel(name = "创建人")
    private String creator;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 工作同步至 */
    @Excel(name = "工作同步至")
    private String workSynchronization;

    public void setNewsId(Long newsId) 
    {
        this.newsId = newsId;
    }

    public Long getNewsId() 
    {
        return newsId;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setCreator(String creator) 
    {
        this.creator = creator;
    }

    public String getCreator() 
    {
        return creator;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setWorkSynchronization(String workSynchronization) 
    {
        this.workSynchronization = workSynchronization;
    }

    public String getWorkSynchronization() 
    {
        return workSynchronization;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("newsId", getNewsId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("creator", getCreator())
            .append("createTime", getCreateTime())
            .append("status", getStatus())
            .append("workSynchronization", getWorkSynchronization())
            .toString();
    }
}

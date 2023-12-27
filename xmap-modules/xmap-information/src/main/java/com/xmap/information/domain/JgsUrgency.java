package com.xmap.information.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xmap.common.core.annotation.Excel;
import com.xmap.common.core.web.domain.BaseEntity;

/**
 * 紧急插播管理对象 jgs_urgency_list
 *
 * @author xmap
 * @date 2023-07-31
 */
public class JgsUrgency extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 链接 */
    @Excel(name = "链接")
    private String url;

    /** 是否紧急插播 */
    @Excel(name = "是否紧急插播")
    private String isUrgency;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 播放时间 */
    @Excel(name = "播放时间")
    private Long playTime;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
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
    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }
    public void setIsUrgency(String  isUrgency)
    {
        this.isUrgency = isUrgency;
    }

    public String  getIsUrgency()
    {
        return isUrgency;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setPlayTime(Long playTime)
    {
        this.playTime = playTime;
    }

    public Long getPlayTime()
    {
        return playTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("title", getTitle())
            .append("content", getContent())
            .append("url", getUrl())
            .append("isUrgency", getIsUrgency())
            .append("status", getStatus())
            .append("playTime", getPlayTime())
            .toString();
    }
}

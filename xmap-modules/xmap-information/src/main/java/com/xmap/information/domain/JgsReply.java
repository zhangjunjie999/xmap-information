package com.xmap.information.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xmap.common.core.annotation.Excel;
import com.xmap.common.core.web.domain.BaseEntity;

/**
 * 回复表对象 jgs_reply_list
 * 
 * @author xmap
 * @date 2023-07-28
 */
public class JgsReply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 回复ID */
    private Long id;

    /** 建议ID */
    @Excel(name = "建议ID")
    private Long suggestId;

    /** 回复内容 */
    @Excel(name = "回复内容")
    private String content;

    /** 回复时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "回复时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date replyTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSuggestId(Long suggestId) 
    {
        this.suggestId = suggestId;
    }

    public Long getSuggestId() 
    {
        return suggestId;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setReplyTime(Date replyTime) 
    {
        this.replyTime = replyTime;
    }

    public Date getReplyTime() 
    {
        return replyTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("suggestId", getSuggestId())
            .append("content", getContent())
            .append("replyTime", getReplyTime())
            .toString();
    }
}

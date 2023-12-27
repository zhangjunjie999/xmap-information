package com.xmap.information.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xmap.common.core.annotation.Excel;
import com.xmap.common.core.web.domain.BaseEntity;

/**
 * 资讯管理对象 jgs_informine_list
 * 
 * @author xmap
 * @date 2023-10-08
 */
public class JgsInformine extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 咨询ID */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 作者 */
    @Excel(name = "作者")
    private String author;

    /** 标签 */
    @Excel(name = "标签")
    private String tag;

    /** 来源 */
    @Excel(name = "来源")
    private String source;

    /** 网址 */
    @Excel(name = "网址")
    private String url;

    /** 发布日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publicationTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
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
    public void setAuthor(String author) 
    {
        this.author = author;
    }

    public String getAuthor() 
    {
        return author;
    }
    public void setTag(String tag) 
    {
        this.tag = tag;
    }

    public String getTag() 
    {
        return tag;
    }
    public void setSource(String source) 
    {
        this.source = source;
    }

    public String getSource() 
    {
        return source;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setPublicationTime(Date publicationTime) 
    {
        this.publicationTime = publicationTime;
    }

    public Date getPublicationTime() 
    {
        return publicationTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("author", getAuthor())
            .append("tag", getTag())
            .append("source", getSource())
            .append("url", getUrl())
            .append("publicationTime", getPublicationTime())
            .toString();
    }
}

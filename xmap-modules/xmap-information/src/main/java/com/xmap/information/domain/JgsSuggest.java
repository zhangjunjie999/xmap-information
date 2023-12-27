package com.xmap.information.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xmap.common.core.annotation.Excel;
import com.xmap.common.core.web.domain.BaseEntity;

/**
 * 游客建议对象 jgs_suggest_list
 * 
 * @author xmap
 * @date 2023-07-28
 */
public class JgsSuggest extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 建议ID */
    private Long id;

    /** 游客姓名 */
    @Excel(name = "游客姓名")
    private String name;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String phone;

    /** 建议内容 */
    @Excel(name = "建议内容")
    private String content;

    /** 提交日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /** 回复状态 */
    @Excel(name = "回复状态")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setSubmitTime(Date submitTime) 
    {
        this.submitTime = submitTime;
    }

    public Date getSubmitTime() 
    {
        return submitTime;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("phone", getPhone())
            .append("content", getContent())
            .append("submitTime", getSubmitTime())
            .append("status", getStatus())
            .toString();
    }
}

package com.xmap.information.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xmap.common.core.annotation.Excel;
import com.xmap.common.core.web.domain.BaseEntity;

/**
 * 玩转井冈山对象 jgs_play_list
 * 
 * @author xmap
 * @date 2023-07-28
 */
public class JgsPlay extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 娱乐ID */
    private Long id;

    /** 娱乐类型 */
    @Excel(name = "娱乐类型")
    private String type;

    /** 娱乐标题 */
    @Excel(name = "娱乐标题")
    private String title;

    /** 娱乐内容 */
    @Excel(name = "娱乐内容")
    private String content;

    /** 游玩地址 */
    @Excel(name = "游玩地址")
    private String address;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String phone;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date changeTime;

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
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setAddTime(Date addTime) 
    {
        this.addTime = addTime;
    }

    public Date getAddTime() 
    {
        return addTime;
    }
    public void setChangeTime(Date changeTime) 
    {
        this.changeTime = changeTime;
    }

    public Date getChangeTime() 
    {
        return changeTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("title", getTitle())
            .append("content", getContent())
            .append("address", getAddress())
            .append("phone", getPhone())
            .append("addTime", getAddTime())
            .append("changeTime", getChangeTime())
            .toString();
    }
}

package com.xmap.information.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xmap.common.core.annotation.Excel;
import com.xmap.common.core.web.domain.BaseEntity;

/**
 * 特色产品管理对象 jgs_special_list
 * 
 * @author xmap
 * @date 2023-07-28
 */
public class JgsSpecial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 特色名称 */
    @Excel(name = "特色名称")
    private String name;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 价格 */
    @Excel(name = "价格")
    private Long price;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifiedTime;

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
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setPrice(Long price) 
    {
        this.price = price;
    }

    public Long getPrice() 
    {
        return price;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setModifiedTime(Date modifiedTime) 
    {
        this.modifiedTime = modifiedTime;
    }

    public Date getModifiedTime() 
    {
        return modifiedTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("description", getDescription())
            .append("price", getPrice())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("modifiedTime", getModifiedTime())
            .toString();
    }
}

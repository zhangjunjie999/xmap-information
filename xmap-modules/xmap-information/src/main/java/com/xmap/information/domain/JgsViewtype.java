package com.xmap.information.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xmap.common.core.annotation.Excel;
import com.xmap.common.core.web.domain.BaseEntity;

/**
 * 景点类型管理对象 jgs_viewtype_list
 * 
 * @author xmap
 * @date 2023-11-01
 */
public class JgsViewtype extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 类型名称 */
    @Excel(name = "类型名称")
    private String typeName;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 标志 */
    @Excel(name = "标志")
    private String logo;

    /** 景区数量 */
    @Excel(name = "景区数量")
    private Long viewCounts;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTypeName(String typeName) 
    {
        this.typeName = typeName;
    }

    public String getTypeName() 
    {
        return typeName;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setLogo(String logo) 
    {
        this.logo = logo;
    }

    public String getLogo() 
    {
        return logo;
    }
    public void setViewCounts(Long viewCounts) 
    {
        this.viewCounts = viewCounts;
    }

    public Long getViewCounts() 
    {
        return viewCounts;
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
            .append("typeName", getTypeName())
            .append("description", getDescription())
            .append("logo", getLogo())
            .append("viewCounts", getViewCounts())
            .append("createTime", getCreateTime())
            .append("status", getStatus())
            .toString();
    }
}

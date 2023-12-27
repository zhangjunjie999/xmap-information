package com.xmap.information.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xmap.common.core.annotation.Excel;
import com.xmap.common.core.web.domain.BaseEntity;

/**
 * 大屏管理对象 jgs_screen_list
 * 
 * @author xmap
 * @date 2023-10-09
 */
public class JgsScreen extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 大屏ID */
    private Long id;

    /** 大屏名称 */
    @Excel(name = "大屏名称")
    private String screenName;

    /** 大屏尺寸 */
    @Excel(name = "大屏尺寸")
    private String screenSize;

    /** 大屏描述 */
    @Excel(name = "大屏描述")
    private String screenDescreption;

    /** 数据来源 */
    @Excel(name = "数据来源")
    private String screenSource;

    /** 启用状态 */
    @Excel(name = "启用状态")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setScreenName(String screenName) 
    {
        this.screenName = screenName;
    }

    public String getScreenName() 
    {
        return screenName;
    }
    public void setScreenSize(String screenSize) 
    {
        this.screenSize = screenSize;
    }

    public String getScreenSize() 
    {
        return screenSize;
    }
    public void setScreenDescreption(String screenDescreption) 
    {
        this.screenDescreption = screenDescreption;
    }

    public String getScreenDescreption() 
    {
        return screenDescreption;
    }
    public void setScreenSource(String screenSource) 
    {
        this.screenSource = screenSource;
    }

    public String getScreenSource() 
    {
        return screenSource;
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
            .append("screenName", getScreenName())
            .append("screenSize", getScreenSize())
            .append("screenDescreption", getScreenDescreption())
            .append("screenSource", getScreenSource())
            .append("status", getStatus())
            .toString();
    }
}

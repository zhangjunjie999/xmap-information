package com.xmap.screen.domain;

import com.xmap.common.core.annotation.Excel;
import com.xmap.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 屏幕终端-资源对象 screen-resource
 *
 * @author xmap
 * @date 2023-11-27
 */

public class Resource extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /** 材料编号 */
    private Long materialNo;
    /** 播放时间 */
    @Excel(name = "播放时间")
    private Long playTime;

    /** 资源值 */
    @Excel(name = "资源值")
    private String resourceValue;
    public void setMaterialNo(Long materialNo)
    {
        this.materialNo = materialNo;
    }

    public Long getMaterialNo()
    {
        return materialNo;
    }

    public void setPlayTime(Long playTime)
    {
        this.playTime = playTime;
    }

    public Long getplayTime() { return playTime; }

    public void setResourceValue(String resourceValue)
    {
        this.resourceValue = resourceValue;
    }

    public String getResourceValue() { return resourceValue; }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("materialNo", getMaterialNo())
                .append("playTime", getplayTime())
                .append("resourceValue", getResourceValue())


                .toString();
    }
}



package com.xmap.information.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xmap.common.core.annotation.Excel;
import com.xmap.common.core.web.domain.BaseEntity;

/**
 * 停车场信息表对象 jgs_parking_lot
 * 
 * @author xmap
 * @date 2023-11-03
 */
public class JgsParkingLot extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 停车场名称 */
    @Excel(name = "停车场名称")
    private String lotName;

    /** 总停车位 */
    @Excel(name = "总停车位")
    private Long totalSpaces;

    /** 已停车位 */
    @Excel(name = "已停车位")
    private Long occupiedSpaces;

    /** 空余车位 */
    @Excel(name = "空余车位")
    private Long availableSpaces;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setLotName(String lotName) 
    {
        this.lotName = lotName;
    }

    public String getLotName() 
    {
        return lotName;
    }
    public void setTotalSpaces(Long totalSpaces) 
    {
        this.totalSpaces = totalSpaces;
    }

    public Long getTotalSpaces() 
    {
        return totalSpaces;
    }
    public void setOccupiedSpaces(Long occupiedSpaces) 
    {
        this.occupiedSpaces = occupiedSpaces;
    }

    public Long getOccupiedSpaces() 
    {
        return occupiedSpaces;
    }
    public void setAvailableSpaces(Long availableSpaces) 
    {
        this.availableSpaces = availableSpaces;
    }

    public Long getAvailableSpaces() 
    {
        return availableSpaces;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("lotName", getLotName())
            .append("totalSpaces", getTotalSpaces())
            .append("occupiedSpaces", getOccupiedSpaces())
            .append("availableSpaces", getAvailableSpaces())
            .append("remarks", getRemarks())
            .toString();
    }
}

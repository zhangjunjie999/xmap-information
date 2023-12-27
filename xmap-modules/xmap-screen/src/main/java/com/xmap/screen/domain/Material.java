package com.xmap.screen.domain;

import com.xmap.common.core.annotation.Excel;
import com.xmap.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 屏幕终端-材料对象 screen-resource
 *
 * @author xmap
 * @date 2023-11-27
 */
public class Material extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /** 材料独特字段编号 */
    private String materialUniqueFieldValue;
    /** 材料独特字段类型 */
    @Excel(name = "材料独特字段类型")
    private String materialUniqueFieldType;

    /** 资源类型 */
    @Excel(name = "资源类型")
    private String resourceType;
    /** 资源列表 */
    private List<Resource> resourceList;

    public void setMaterialUniqueFieldValue(String materialUniqueFieldValue)
    {
        this.materialUniqueFieldValue = materialUniqueFieldValue;
    }

    public String getMaterialUniqueFieldValue()
    {
        return materialUniqueFieldValue;
    }

    public void setMaterialUniqueFieldType(String materialUniqueFieldType)
    {
        this.materialUniqueFieldType = materialUniqueFieldType;
    }

    public String getMaterialUniqueFieldType() { return materialUniqueFieldType; }

    public void setResourceType(String resourceType)
    {
        this.resourceType = resourceType;
    }

    public String getResourceType() { return resourceType; }

    public void setResourceList(List<Resource> resourceList)
    {
        this.resourceList = resourceList;
    }

    public List<Resource> getResourceList() { return resourceList; }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("materialUniqueFieldValue", getMaterialUniqueFieldValue())
                .append("materialUniqueFieldType", getMaterialUniqueFieldType())
                .append("resourceType", getResourceType())
                .append("resourceList", getResourceList())


                .toString();
    }
}






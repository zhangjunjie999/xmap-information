package com.xmap.screen.domain;

import com.xmap.common.core.annotation.Excel;
import com.xmap.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class RefreshMaterialContent extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /** 编号 */
    private Long id;
    /** 命令行独特字段类型 */
    private String terminalUniqueFieldType;
    /** 命令行独特字段值 */
    private List<String> terminalUniqueFieldValues;
    /** 操作类型 */
    private String operatorType;
    /** 业务数据 */
    private List<Material> businessData;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setTerminalUniqueFieldType(String terminalUniqueFieldType)
    {
        this.terminalUniqueFieldType = terminalUniqueFieldType;
    }

    public String getTerminalUniqueFieldType()
    {
        return terminalUniqueFieldType;
    }

    public void setTerminalUniqueFieldValues(List<String> terminalUniqueFieldValues)
    {
        this.terminalUniqueFieldValues = terminalUniqueFieldValues;
    }

    public List<String> getTerminalUniqueFieldValues()
    {
        return terminalUniqueFieldValues;
    }

    public void setOperatorType(String operatorType)
    {
        this.operatorType = operatorType;
    }

    public String getOperatorType()
    {
        return operatorType;
    }

    public void setBusinessData(List<Material> businessData)
    {
        this.businessData = businessData;
    }

    public List<Material> getBusinessData()
    {
        return businessData;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("terminalUniqueFieldType", getTerminalUniqueFieldType())
                .append("terminalUniqueFieldValues", getTerminalUniqueFieldValues())
                .append("operatorType", getOperatorType())
                .append("businessData", getBusinessData())


                .toString();
    }



}




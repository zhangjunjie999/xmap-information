package com.xmap.screen.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xmap.common.core.annotation.Excel;
import com.xmap.common.core.web.domain.BaseEntity;

/**
 * 设备对接历史对象 screen_history
 * 
 * @author xmap
 * @date 2023-12-05
 */
public class ScreenHistory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long historyId;

    /** 名称 */
    @Excel(name = "名称")
    private String historyName;

    /** IP地址 */
    @Excel(name = "IP地址")
    private String historyIp;

    /** 端口 */
    @Excel(name = "端口")
    private String historyPort;

    /** 接口 */
    @Excel(name = "接口")
    private String historyApi;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;
    /** 创建者 */
    @Excel(name = "创建者")
    private String createBy;
    /** 状态 */
    @Excel(name = "状态")
    private String historyStatus;

    /** 回复 */
    @Excel(name = "回复")
    private String historyResponse;

    /** 字段类型 */
    @Excel(name = "字段类型")
    private String fieldType;

    /** 字段值 */
    @Excel(name = "字段值")
    private String fieldValues;

    /** 操作类型 */
    @Excel(name = "操作类型")
    private String operatorType;

    /** 数据内容 */
    @Excel(name = "数据内容")
    private String businessData;

    public void setHistoryId(Long historyId) 
    {
        this.historyId = historyId;
    }

    public Long getHistoryId() 
    {
        return historyId;
    }
    public void setHistoryName(String historyName) 
    {
        this.historyName = historyName;
    }

    public String getHistoryName() 
    {
        return historyName;
    }
    public void setHistoryIp(String historyIp) 
    {
        this.historyIp = historyIp;
    }

    public String getHistoryIp() 
    {
        return historyIp;
    }
    public void setHistoryPort(String historyPort) 
    {
        this.historyPort = historyPort;
    }

    public String getHistoryPort() 
    {
        return historyPort;
    }
    public void setHistoryApi(String historyApi) 
    {
        this.historyApi = historyApi;
    }

    public String getHistoryApi() 
    {
        return historyApi;
    }
    public void setCreateDate(Date createDate) 
    {
        this.createDate = createDate;
    }

    public Date getCreateDate() 
    {
        return createDate;
    }
    public void setCreateBy(String createBy)
    {
        this.createBy = createBy;
    }

    public String getCreateBy()
    {
        return createBy;
    }
    public void setHistoryStatus(String historyStatus) 
    {
        this.historyStatus = historyStatus;
    }

    public String getHistoryStatus() 
    {
        return historyStatus;
    }
    public void setHistoryResponse(String historyResponse) 
    {
        this.historyResponse = historyResponse;
    }

    public String getHistoryResponse() 
    {
        return historyResponse;
    }
    public void setFieldType(String fieldType) 
    {
        this.fieldType = fieldType;
    }

    public String getFieldType() 
    {
        return fieldType;
    }
    public void setFieldValues(String fieldValues) 
    {
        this.fieldValues = fieldValues;
    }

    public String getFieldValues() 
    {
        return fieldValues;
    }
    public void setOperatorType(String operatorType) 
    {
        this.operatorType = operatorType;
    }

    public String getOperatorType() 
    {
        return operatorType;
    }
    public void setBusinessData(String businessData) 
    {
        this.businessData = businessData;
    }

    public String getBusinessData() 
    {
        return businessData;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("historyId", getHistoryId())
            .append("historyName", getHistoryName())
            .append("historyIp", getHistoryIp())
            .append("historyPort", getHistoryPort())
            .append("historyApi", getHistoryApi())
            .append("createDate", getCreateDate())
            .append("createBy", getCreateBy())
            .append("historyStatus", getHistoryStatus())
            .append("historyResponse", getHistoryResponse())
            .append("fieldType", getFieldType())
            .append("fieldValues", getFieldValues())
            .append("operatorType", getOperatorType())
            .append("businessData", getBusinessData())
            .toString();
    }
}

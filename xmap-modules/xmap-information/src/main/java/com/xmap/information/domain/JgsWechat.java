package com.xmap.information.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xmap.common.core.annotation.Excel;
import com.xmap.common.core.web.domain.BaseEntity;

/**
 * 微信管理对象 jgs_wechat_list
 * 
 * @author xmap
 * @date 2023-10-08
 */
public class JgsWechat extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 微信ID */
    private Long id;

    /** 微信昵称 */
    @Excel(name = "微信昵称")
    private String nickname;

    /** 微信号 */
    @Excel(name = "微信号")
    private String number;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    /** 出生日期 */
    @Excel(name = "出生日期")
    private String birthdate;

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
    public void setNickname(String nickname) 
    {
        this.nickname = nickname;
    }

    public String getNickname() 
    {
        return nickname;
    }
    public void setNumber(String number) 
    {
        this.number = number;
    }

    public String getNumber() 
    {
        return number;
    }
    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }
    public void setBirthdate(String birthdate) 
    {
        this.birthdate = birthdate;
    }

    public String getBirthdate() 
    {
        return birthdate;
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
            .append("nickname", getNickname())
            .append("number", getNumber())
            .append("gender", getGender())
            .append("birthdate", getBirthdate())
            .append("status", getStatus())
            .toString();
    }
}

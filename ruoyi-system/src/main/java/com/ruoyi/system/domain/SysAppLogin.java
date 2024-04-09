package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * sysAppLogin对象 App用户
 *
 * @author wxq
 * @date 2024-03-30
 */
public class SysAppLogin extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 车主编号 */
    @Excel(name = "车主编号")
    private Long ownerId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    /** 密码 */
    @Excel(name = "密码")
    private String passWord;

    /** 车主手机号 */
    @Excel(name = "车主手机号")
    private String ownerPhone;

    public void setOwnerId(Long ownerId)
    {
        this.ownerId = ownerId;
    }

    public Long getOwnerId()
    {
        return ownerId;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setPassWord(String passWord)
    {
        this.passWord = passWord;
    }

    public String getPassWord()
    {
        return passWord;
    }
    public void setOwnerPhone(String ownerPhone)
    {
        this.ownerPhone = ownerPhone;
    }

    public String getOwnerPhone()
    {
        return ownerPhone;
    }

    public SysAppLogin(Long ownerId, String userName, String passWord, String ownerPhone) {
        this.ownerId = ownerId;
        this.userName = userName;
        this.passWord = passWord;
        this.ownerPhone = ownerPhone;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("ownerId", getOwnerId())
                .append("userName", getUserName())
                .append("passWord", getPassWord())
                .append("ownerPhone", getOwnerPhone())
                .toString();
    }
}

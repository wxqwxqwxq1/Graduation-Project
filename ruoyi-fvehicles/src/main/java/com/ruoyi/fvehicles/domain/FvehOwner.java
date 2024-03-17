package com.ruoyi.fvehicles.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车主对象 fveh_owner
 *
 * @author ruoyi
 * @date 2024-03-09
 */
public class FvehOwner extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 车主编号 */
    private Long ownerId;

    /** 车主姓名 */
    @Excel(name = "车主姓名")
    private String ownerName;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String ownerIdCard;

    /** 手机号 */
    @Excel(name = "手机号")
    private String ownerPhone;

    /** 地址 */
    @Excel(name = "地址")
    private String ownerAddress;

    /** 驾驶证编号 */
    @Excel(name = "驾驶证编号")
    private String drivingLicenseNumber;

    /** 车位个数 */
    @Excel(name = "车位个数")
    private Long parkingSpaces;

    /** 已用车位 */
    @Excel(name = "已用车位")
    private Long usedSpaces;

    /** 剩余车位 */
    @Excel(name = "剩余车位")
    private Long remainSpaces;

    public void setOwnerId(Long ownerId)
    {
        this.ownerId = ownerId;
    }

    public Long getOwnerId()
    {
        return ownerId;
    }
    public void setOwnerName(String ownerName)
    {
        this.ownerName = ownerName;
    }

    public String getOwnerName()
    {
        return ownerName;
    }
    public void setOwnerIdCard(String ownerIdCard)
    {
        this.ownerIdCard = ownerIdCard;
    }

    public String getOwnerIdCard()
    {
        return ownerIdCard;
    }
    public void setOwnerPhone(String ownerPhone)
    {
        this.ownerPhone = ownerPhone;
    }

    public String getOwnerPhone()
    {
        return ownerPhone;
    }
    public void setOwnerAddress(String ownerAddress)
    {
        this.ownerAddress = ownerAddress;
    }

    public String getOwnerAddress()
    {
        return ownerAddress;
    }
    public void setDrivingLicenseNumber(String drivingLicenseNumber)
    {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public String getDrivingLicenseNumber()
    {
        return drivingLicenseNumber;
    }
    public void setParkingSpaces(Long parkingSpaces)
    {
        this.parkingSpaces = parkingSpaces;
    }

    public Long getParkingSpaces()
    {
        return parkingSpaces;
    }
    public void setUsedSpaces(Long usedSpaces)
    {
        this.usedSpaces = usedSpaces;
    }

    public Long getUsedSpaces()
    {
        return usedSpaces;
    }
    public void setRemainSpaces(Long remainSpaces)
    {
        this.remainSpaces = remainSpaces;
    }

    public Long getRemainSpaces()
    {
        return remainSpaces;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("ownerId", getOwnerId())
                .append("ownerName", getOwnerName())
                .append("ownerIdCard", getOwnerIdCard())
                .append("ownerPhone", getOwnerPhone())
                .append("ownerAddress", getOwnerAddress())
                .append("drivingLicenseNumber", getDrivingLicenseNumber())
                .append("parkingSpaces", getParkingSpaces())
                .append("usedSpaces", getUsedSpaces())
                .append("remainSpaces", getRemainSpaces())
                .toString();
    }
}

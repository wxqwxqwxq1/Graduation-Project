package com.ruoyi.manage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 *  车库对象 manage_garage
 *
 * @author wxq
 * @date 2024-03-10
 */
public class ManageGarage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 车库编号 */
    private Long garageId;

    /** 车库位置 */
    @Excel(name = "车库位置")
    private String location;

    /** 总车位数 */
    @Excel(name = "总车位数")
    private Long totalCapacity;

    /** 车库类型 */
    @Excel(name = "车库类型")
    private String type;

    /** 车库状态 */
    @Excel(name = "车库状态")
    private Integer isAvailable;

    /** 车库余量 */
    @Excel(name = "车库余量")
    private Long remainingCapacity;

    public void setGarageId(Long garageId)
    {
        this.garageId = garageId;
    }

    public Long getGarageId()
    {
        return garageId;
    }
    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getLocation()
    {
        return location;
    }
    public void setTotalCapacity(Long totalCapacity)
    {
        this.totalCapacity = totalCapacity;
    }

    public Long getTotalCapacity()
    {
        return totalCapacity;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setIsAvailable(Integer isAvailable)
    {
        this.isAvailable = isAvailable;
    }

    public Integer getIsAvailable()
    {
        return isAvailable;
    }
    public void setRemainingCapacity(Long remainingCapacity)
    {
        this.remainingCapacity = remainingCapacity;
    }

    public Long getRemainingCapacity()
    {
        return remainingCapacity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("garageId", getGarageId())
                .append("location", getLocation())
                .append("totalCapacity", getTotalCapacity())
                .append("type", getType())
                .append("isAvailable", getIsAvailable())
                .append("remainingCapacity", getRemainingCapacity())
                .toString();
    }
}

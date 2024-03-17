package com.ruoyi.fvehicles.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车库-车辆关联对象 fveh_garage_vehicle
 *
 * @author ruoyi
 * @date 2024-03-12
 */
public class FvehGarageVehicle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 关联编号 */
    private Long associationId;

    /** 车库ID */
    @Excel(name = "车库ID")
    private Long garageId;

    /** 车辆ID */
    @Excel(name = "车辆ID")
    private Long vehicleId;

    /** 车主id */
    @Excel(name = "车主id")
    private Long ownerId;

    /** start_time */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "start_time", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** end_time */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "end_time", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    public void setAssociationId(Long associationId)
    {
        this.associationId = associationId;
    }

    public Long getAssociationId()
    {
        return associationId;
    }
    public void setGarageId(Long garageId)
    {
        this.garageId = garageId;
    }

    public Long getGarageId()
    {
        return garageId;
    }
    public void setVehicleId(Long vehicleId)
    {
        this.vehicleId = vehicleId;
    }

    public Long getVehicleId()
    {
        return vehicleId;
    }
    public void setOwnerId(Long ownerId)
    {
        this.ownerId = ownerId;
    }

    public Long getOwnerId()
    {
        return ownerId;
    }
    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getStartTime()
    {
        return startTime;
    }
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("associationId", getAssociationId())
                .append("garageId", getGarageId())
                .append("vehicleId", getVehicleId())
                .append("ownerId", getOwnerId())
                .append("startTime", getStartTime())
                .append("endTime", getEndTime())
                .toString();
    }
}

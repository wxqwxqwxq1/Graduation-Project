package com.ruoyi.fvehicles.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车位对象 view_vehicle_parking_info
 *
 * @author wxq
 * @date 2024-03-11
 */
public class ViewVehicleParkingInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @Excel(name = "编号")
    private Long associationId;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String licensePlate;

    /** 车辆型号 */
    @Excel(name = "车辆型号")
    private String vehicleModel;

    /** 车辆编号 */
    @Excel(name = "车辆编号")
    private Long vehicleId;

    /** 车主姓名  */
    @Excel(name = "车主姓名 ")
    private String ownerName;

    /** 车主编号 */
    @Excel(name = "车主编号")
    private Long ownerId;

    /** 车主电话 */
    @Excel(name = "车主电话")
    private String ownerPhone;

    /** 车库位置  */
    @Excel(name = "车库位置 ")
    private String garageLocation;

    /** 车库编号 */
    @Excel(name = "车库编号")
    private Long garageId;

    /** 车库类型 */
    @Excel(name = "车库类型")
    private String garageType;

    /**  开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = " 开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    public void setAssociationId(Long associationId)
    {
        this.associationId = associationId;
    }

    public Long getAssociationId()
    {
        return associationId;
    }
    public void setLicensePlate(String licensePlate)
    {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate()
    {
        return licensePlate;
    }
    public void setVehicleModel(String vehicleModel)
    {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleModel()
    {
        return vehicleModel;
    }
    public void setVehicleId(Long vehicleId)
    {
        this.vehicleId = vehicleId;
    }

    public Long getVehicleId()
    {
        return vehicleId;
    }
    public void setOwnerName(String ownerName)
    {
        this.ownerName = ownerName;
    }

    public String getOwnerName()
    {
        return ownerName;
    }
    public void setOwnerId(Long ownerId)
    {
        this.ownerId = ownerId;
    }

    public Long getOwnerId()
    {
        return ownerId;
    }
    public void setOwnerPhone(String ownerPhone)
    {
        this.ownerPhone = ownerPhone;
    }

    public String getOwnerPhone()
    {
        return ownerPhone;
    }
    public void setGarageLocation(String garageLocation)
    {
        this.garageLocation = garageLocation;
    }

    public String getGarageLocation()
    {
        return garageLocation;
    }
    public void setGarageId(Long garageId)
    {
        this.garageId = garageId;
    }

    public Long getGarageId()
    {
        return garageId;
    }
    public void setGarageType(String garageType)
    {
        this.garageType = garageType;
    }

    public String getGarageType()
    {
        return garageType;
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
                .append("licensePlate", getLicensePlate())
                .append("vehicleModel", getVehicleModel())
                .append("vehicleId", getVehicleId())
                .append("ownerName", getOwnerName())
                .append("ownerId", getOwnerId())
                .append("ownerPhone", getOwnerPhone())
                .append("garageLocation", getGarageLocation())
                .append("garageId", getGarageId())
                .append("garageType", getGarageType())
                .append("startTime", getStartTime())
                .append("endTime", getEndTime())
                .toString();
    }
}

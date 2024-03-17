package com.ruoyi.fvehicles.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车辆对象 fveh_vehicle
 *
 * @author wxq
 * @date 2024-03-07
 */
public class FvehVehicle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 车辆编号 */
    private Long vehicleId;

    /** 车牌照 */
    @Excel(name = "车牌照")
    private String licensePlate;

    /** 车辆类型  */
    @Excel(name = "车辆类型 ")
    private String vehicleType;

    /** 车辆型号 */
    @Excel(name = "车辆型号")
    private String vehicleModel;

    /** 车辆颜色 */
    @Excel(name = "车辆颜色")
    private String vehicleColor;

    /** 车辆许可 */
    @Excel(name = "车辆许可")
    private Integer vehiclePermit;

    /** 车主 */
    @Excel(name = "车主")
    private Long ownerId;

    public void setVehicleId(Long vehicleId)
    {
        this.vehicleId = vehicleId;
    }

    public Long getVehicleId()
    {
        return vehicleId;
    }
    public void setLicensePlate(String licensePlate)
    {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate()
    {
        return licensePlate;
    }
    public void setVehicleType(String vehicleType)
    {
        this.vehicleType = vehicleType;
    }

    public String getVehicleType()
    {
        return vehicleType;
    }
    public void setVehicleModel(String vehicleModel)
    {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleModel()
    {
        return vehicleModel;
    }
    public void setVehicleColor(String vehicleColor)
    {
        this.vehicleColor = vehicleColor;
    }

    public String getVehicleColor()
    {
        return vehicleColor;
    }
    public void setVehiclePermit(Integer vehiclePermit)
    {
        this.vehiclePermit = vehiclePermit;
    }

    public Integer getVehiclePermit()
    {
        return vehiclePermit;
    }
    public void setOwnerId(Long ownerId)
    {
        this.ownerId = ownerId;
    }

    public Long getOwnerId()
    {
        return ownerId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("vehicleId", getVehicleId())
                .append("licensePlate", getLicensePlate())
                .append("vehicleType", getVehicleType())
                .append("vehicleModel", getVehicleModel())
                .append("vehicleColor", getVehicleColor())
                .append("vehiclePermit", getVehiclePermit())
                .append("ownerId", getOwnerId())
                .toString();
    }
}

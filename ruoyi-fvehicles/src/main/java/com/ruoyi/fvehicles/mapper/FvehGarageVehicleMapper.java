package com.ruoyi.fvehicles.mapper;

import java.util.List;
import com.ruoyi.fvehicles.domain.FvehGarageVehicle;

/**
 * 车库-车辆关联Mapper接口
 *
 * @author ruoyi
 * @date 2024-03-12
 */
public interface FvehGarageVehicleMapper
{
    /**
     * 查询车库-车辆关联
     *
     * @param associationId 车库-车辆关联主键
     * @return 车库-车辆关联
     */
    public FvehGarageVehicle selectFvehGarageVehicleByAssociationId(Long associationId);

    /**
     * 查询车库-车辆关联列表
     *
     * @param fvehGarageVehicle 车库-车辆关联
     * @return 车库-车辆关联集合
     */
    public List<FvehGarageVehicle> selectFvehGarageVehicleList(FvehGarageVehicle fvehGarageVehicle);

    /**
     * 新增车库-车辆关联
     *
     * @param fvehGarageVehicle 车库-车辆关联
     * @return 结果
     */
    public int insertFvehGarageVehicle(FvehGarageVehicle fvehGarageVehicle);

    /**
     * 修改车库-车辆关联
     *
     * @param fvehGarageVehicle 车库-车辆关联
     * @return 结果
     */
    public int updateFvehGarageVehicle(FvehGarageVehicle fvehGarageVehicle);

    /**
     * 删除车库-车辆关联
     *
     * @param associationId 车库-车辆关联主键
     * @return 结果
     */
    public int deleteFvehGarageVehicleByAssociationId(Long associationId);

    /**
     * 批量删除车库-车辆关联
     *
     * @param associationIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFvehGarageVehicleByAssociationIds(Long[] associationIds);


    /**
     * 根据车辆id删除车库-车辆关联
     *
     * @param vehicleId 车辆id
     * @return 结果
     */
    void deleteByVehicleId(Integer vehicleId);

}

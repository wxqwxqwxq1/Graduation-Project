package com.ruoyi.fvehicles.mapper;

import java.util.List;
import com.ruoyi.fvehicles.domain.FvehVehicle;

/**
 * 车辆Mapper接口
 *
 * @author wxq
 * @date 2024-03-07
 */
public interface FvehVehicleMapper
{
    /**
     * 查询车辆
     *
     * @param vehicleId 车辆主键
     * @return 车辆
     */
    public FvehVehicle selectFvehVehicleByVehicleId(Long vehicleId);

    /**
     * 查询车辆列表
     *
     * @param fvehVehicle 车辆
     * @return 车辆集合
     */
    public List<FvehVehicle> selectFvehVehicleList(FvehVehicle fvehVehicle);

    /**
     * 新增车辆
     *
     * @param fvehVehicle 车辆
     * @return 结果
     */
    public int insertFvehVehicle(FvehVehicle fvehVehicle);

    /**
     * 修改车辆
     *
     * @param fvehVehicle 车辆
     * @return 结果
     */
    public int updateFvehVehicle(FvehVehicle fvehVehicle);

    /**
     * 删除车辆
     *
     * @param vehicleId 车辆主键
     * @return 结果
     */
    public int deleteFvehVehicleByVehicleId(Long vehicleId);

    /**
     * 批量删除车辆
     *
     * @param vehicleIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFvehVehicleByVehicleIds(Long[] vehicleIds);

    int deleteFvehVehicleByOwnerId(Long ownerId);
}

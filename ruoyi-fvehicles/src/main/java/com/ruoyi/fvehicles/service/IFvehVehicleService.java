package com.ruoyi.fvehicles.service;

import java.util.List;
import com.ruoyi.fvehicles.domain.FvehVehicle;

/**
 * 车辆Service接口
 *
 * @author wxq
 * @date 2024-03-07
 */
public interface IFvehVehicleService
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


    //    剥离出这个方法----获取剩余车位并更新车位信息
    Long getRemainSpaces(Long ownerId);

    /**
     * 修改车辆
     *
     * @param fvehVehicle 车辆
     * @return 结果
     */
    public int updateFvehVehicle(FvehVehicle fvehVehicle);

    /**
     * 批量删除车辆
     *
     * @param vehicleIds 需要删除的车辆主键集合
     * @return 结果
     */
    public int deleteFvehVehicleByVehicleIds(Long[] vehicleIds);

    /**
     * 删除车辆信息
     *
     * @param vehicleId 车辆主键
     * @return 结果
     */
    public int deleteFvehVehicleByOwnerId(Long ownerId);

    int deleteFvehVehicleByVehicleId(Long vehicleId);
}

package com.ruoyi.fvehicles.mapper;

import java.util.List;
import com.ruoyi.fvehicles.domain.ViewVehicleParkingInfo;

/**
 * 车位Mapper接口
 *
 * @author wxq
 * @date 2024-03-17
 */
public interface ViewVehicleParkingInfoMapper
{
    /**
     * 查询车位
     *
     * @param associationId 车位主键
     * @return 车位
     */
    public ViewVehicleParkingInfo selectViewVehicleParkingInfoByAssociationId(Long associationId);

    /**
     * 查询车位列表
     *
     * @param viewVehicleParkingInfo 车位
     * @return 车位集合
     */
    public List<ViewVehicleParkingInfo> selectViewVehicleParkingInfoList(ViewVehicleParkingInfo viewVehicleParkingInfo);

    /**
     * 新增车位
     *
     * @param viewVehicleParkingInfo 车位
     * @return 结果
     */
    public int insertViewVehicleParkingInfo(ViewVehicleParkingInfo viewVehicleParkingInfo);

    /**
     * 修改车位
     *
     * @param viewVehicleParkingInfo 车位
     * @return 结果
     */
    public int updateViewVehicleParkingInfo(ViewVehicleParkingInfo viewVehicleParkingInfo);

    /**
     * 删除车位
     *
     * @param associationId 车位主键
     * @return 结果
     */
    public int deleteViewVehicleParkingInfoByAssociationId(Long associationId);

    /**
     * 批量删除车位
     *
     * @param associationIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteViewVehicleParkingInfoByAssociationIds(Long[] associationIds);
}

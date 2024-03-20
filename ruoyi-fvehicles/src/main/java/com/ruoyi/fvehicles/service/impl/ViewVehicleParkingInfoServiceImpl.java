package com.ruoyi.fvehicles.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fvehicles.mapper.ViewVehicleParkingInfoMapper;
import com.ruoyi.fvehicles.domain.ViewVehicleParkingInfo;
import com.ruoyi.fvehicles.service.IViewVehicleParkingInfoService;

/**
 * 车位Service业务层处理
 *
 * @author wxq
 * @date 2024-03-17
 */
@Service
public class ViewVehicleParkingInfoServiceImpl implements IViewVehicleParkingInfoService
{
    @Autowired
    private ViewVehicleParkingInfoMapper viewVehicleParkingInfoMapper;

    /**
     * 查询车位
     *
     * @param associationId 车位主键
     * @return 车位
     */
    @Override
    public ViewVehicleParkingInfo selectViewVehicleParkingInfoByAssociationId(Long associationId)
    {
        return viewVehicleParkingInfoMapper.selectViewVehicleParkingInfoByAssociationId(associationId);
    }

    /**
     * 查询车位列表
     *
     * @param viewVehicleParkingInfo 车位
     * @return 车位
     */
    @Override
    public List<ViewVehicleParkingInfo> selectViewVehicleParkingInfoList(ViewVehicleParkingInfo viewVehicleParkingInfo)
    {
        return viewVehicleParkingInfoMapper.selectViewVehicleParkingInfoList(viewVehicleParkingInfo);
    }

    /**
     * 新增车位
     *
     * @param viewVehicleParkingInfo 车位
     * @return 结果
     */
    @Override
    public int insertViewVehicleParkingInfo(ViewVehicleParkingInfo viewVehicleParkingInfo)
    {
        return viewVehicleParkingInfoMapper.insertViewVehicleParkingInfo(viewVehicleParkingInfo);
    }

    /**
     * 修改车位
     *
     * @param viewVehicleParkingInfo 车位
     * @return 结果
     */
    @Override
    public int updateViewVehicleParkingInfo(ViewVehicleParkingInfo viewVehicleParkingInfo)
    {
        return viewVehicleParkingInfoMapper.updateViewVehicleParkingInfo(viewVehicleParkingInfo);
    }

    /**
     * 批量删除车位
     *
     * @param associationIds 需要删除的车位主键
     * @return 结果
     */
    @Override
    public int deleteViewVehicleParkingInfoByAssociationIds(Long[] associationIds)
    {
        return viewVehicleParkingInfoMapper.deleteViewVehicleParkingInfoByAssociationIds(associationIds);
    }

    /**
     * 删除车位信息
     *
     * @param associationId 车位主键
     * @return 结果
     */
    @Override
    public int deleteViewVehicleParkingInfoByAssociationId(Long associationId)
    {
        return viewVehicleParkingInfoMapper.deleteViewVehicleParkingInfoByAssociationId(associationId);
    }
}

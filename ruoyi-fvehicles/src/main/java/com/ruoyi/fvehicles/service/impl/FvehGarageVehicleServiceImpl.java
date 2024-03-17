package com.ruoyi.fvehicles.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fvehicles.mapper.FvehGarageVehicleMapper;
import com.ruoyi.fvehicles.domain.FvehGarageVehicle;
import com.ruoyi.fvehicles.service.IFvehGarageVehicleService;

/**
 * 车库-车辆关联Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-12
 */
@Service
public class FvehGarageVehicleServiceImpl implements IFvehGarageVehicleService
{
    @Autowired
    private FvehGarageVehicleMapper fvehGarageVehicleMapper;

    /**
     * 查询车库-车辆关联
     *
     * @param associationId 车库-车辆关联主键
     * @return 车库-车辆关联
     */
    @Override
    public FvehGarageVehicle selectFvehGarageVehicleByAssociationId(Long associationId)
    {
        return fvehGarageVehicleMapper.selectFvehGarageVehicleByAssociationId(associationId);
    }

    /**
     * 查询车库-车辆关联列表
     *
     * @param fvehGarageVehicle 车库-车辆关联
     * @return 车库-车辆关联
     */
    @Override
    public List<FvehGarageVehicle> selectFvehGarageVehicleList(FvehGarageVehicle fvehGarageVehicle)
    {
        return fvehGarageVehicleMapper.selectFvehGarageVehicleList(fvehGarageVehicle);
    }

    /**
     * 新增车库-车辆关联
     *
     * @param fvehGarageVehicle 车库-车辆关联
     * @return 结果
     */
    @Override
    public int insertFvehGarageVehicle(FvehGarageVehicle fvehGarageVehicle)
    {
        return fvehGarageVehicleMapper.insertFvehGarageVehicle(fvehGarageVehicle);
    }

    /**
     * 修改车库-车辆关联
     *
     * @param fvehGarageVehicle 车库-车辆关联
     * @return 结果
     */
    @Override
    public int updateFvehGarageVehicle(FvehGarageVehicle fvehGarageVehicle)
    {
        return fvehGarageVehicleMapper.updateFvehGarageVehicle(fvehGarageVehicle);
    }

    /**
     * 批量删除车库-车辆关联
     *
     * @param associationIds 需要删除的车库-车辆关联主键
     * @return 结果
     */
    @Override
    public int deleteFvehGarageVehicleByAssociationIds(Long[] associationIds)
    {
        return fvehGarageVehicleMapper.deleteFvehGarageVehicleByAssociationIds(associationIds);
    }

    /**
     * 删除车库-车辆关联信息
     *
     * @param associationId 车库-车辆关联主键
     * @return 结果
     */
    @Override
    public int deleteFvehGarageVehicleByAssociationId(Long associationId)
    {
        return fvehGarageVehicleMapper.deleteFvehGarageVehicleByAssociationId(associationId);
    }
}

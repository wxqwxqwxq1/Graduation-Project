package com.ruoyi.fvehicles.service.impl;

import java.util.List;

import com.ruoyi.common.exception.GlobalException;
import com.ruoyi.fvehicles.domain.FvehVehicle;
import com.ruoyi.fvehicles.mapper.FvehVehicleMapper;
import com.ruoyi.manage.mapper.ManageGarageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fvehicles.mapper.FvehGarageVehicleMapper;
import com.ruoyi.fvehicles.domain.FvehGarageVehicle;
import com.ruoyi.fvehicles.service.IFvehGarageVehicleService;

/**
 * 车库-车辆关联Service业务层处理
 *
 * @author wxq
 * @date 2024-03-17
 */
@Service
public class FvehGarageVehicleServiceImpl implements IFvehGarageVehicleService
{
    @Autowired
    private FvehGarageVehicleMapper fvehGarageVehicleMapper;

    @Autowired
    private FvehVehicleMapper fvehVehicleMapper;

    @Autowired
    private ManageGarageMapper manageGarageMapper;

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
//        根据车辆编号判断该车库是否存在该关联
        FvehGarageVehicle fvehGarageVehicle1 = new FvehGarageVehicle();
        fvehGarageVehicle1.setVehicleId(fvehGarageVehicle.getVehicleId());
//        车辆许可为开时，才允许添加
        fvehGarageVehicle1.setGarageId(fvehGarageVehicle.getGarageId());
        if(!fvehGarageVehicleMapper.selectFvehGarageVehicleList(fvehGarageVehicle1).isEmpty() ){
            throw new GlobalException("该车库已存在该车辆");
        }
//        添加车 位关联信息时，若开关为开，修改车位数-1，若开关为关，不进行修改；
        if(fvehGarageVehicle.getIsAvailable() == 1){
            fvehGarageVehicleMapper.insertFvehGarageVehicle(fvehGarageVehicle);
            manageGarageMapper.updateRemainingCapacity(fvehGarageVehicle.getGarageId());
            return 1;
        }
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
//        如果车辆许可为关闭，则不允许修改
        FvehVehicle fvehVehicle = new FvehVehicle();
        fvehVehicle = fvehVehicleMapper.selectFvehVehicleByVehicleId(fvehGarageVehicle.getVehicleId());
        if(fvehVehicle.getVehiclePermit() == 0){
            throw new GlobalException("该车辆许可已关闭，不允许修改");
        }
//      在修改车位关联时，若打开，修改为-1，若关闭，修改为-1
        if(fvehGarageVehicle.getIsAvailable() == 1){
            fvehGarageVehicleMapper.updateFvehGarageVehicle(fvehGarageVehicle);
            manageGarageMapper.updateRemainingCapacity(fvehGarageVehicle.getGarageId());
            return 1;
        }
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
//        在删除车位关联时，修改车位数,删除后对所有数据进行全部更新
        int result = fvehGarageVehicleMapper.deleteFvehGarageVehicleByAssociationIds(associationIds);
        if(result > 0){
            List<FvehGarageVehicle> fvehGarageVehicles = fvehGarageVehicleMapper.selectFvehGarageVehicleList(new FvehGarageVehicle());
            for (FvehGarageVehicle fvehGarageVehicle : fvehGarageVehicles) {
                manageGarageMapper.updateRemainingCapacity(fvehGarageVehicle.getGarageId());
            }
        }
        return result;
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
//        在删除车位关联时，修改车位数
        int result = fvehGarageVehicleMapper.deleteFvehGarageVehicleByAssociationId(associationId);
        if(result > 0){
            manageGarageMapper.updateRemainingCapacity(fvehGarageVehicleMapper.selectFvehGarageVehicleByAssociationId(associationId).getGarageId());
        }
        return result;
    }

    /**
     * 根据车辆编号更新车位状态
     *
     * @param VehicleId 车辆编号
     * @return 结果
     */
    @Override
    public int updateFvehGarageVehicleByVehicleId(Long VehicleId, int isAvailable){
        if(isAvailable == 1){
            manageGarageMapper.updateRemainingCapacity(fvehGarageVehicleMapper.selectFvehGarageVehicleByAssociationId(VehicleId).getGarageId());
            fvehGarageVehicleMapper.updateFvehGarageVehicleByVehicleId(VehicleId,isAvailable);
            return 1;
        }
        return fvehGarageVehicleMapper.updateFvehGarageVehicleByVehicleId(VehicleId,isAvailable);
    }
}

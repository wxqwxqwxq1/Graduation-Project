package com.ruoyi.fvehicles.service.impl;

import java.util.List;

import com.ruoyi.common.exception.fvehicles.UserException;
import com.ruoyi.fvehicles.mapper.FvehGarageVehicleMapper;
import com.ruoyi.fvehicles.mapper.FvehOwnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fvehicles.mapper.FvehVehicleMapper;
import com.ruoyi.fvehicles.domain.FvehVehicle;
import com.ruoyi.fvehicles.domain.FvehOwner;
import com.ruoyi.fvehicles.service.IFvehVehicleService;

/**
 * 车辆Service业务层处理
 *
 * @author wxq
 * @date 2024-03-07
 */
@Service
public class FvehVehicleServiceImpl implements IFvehVehicleService
{
    @Autowired
    private FvehVehicleMapper fvehVehicleMapper;
    @Autowired
    private FvehOwnerMapper fvehOwnerMapper;
    @Autowired
    private FvehGarageVehicleMapper fvehGarageVehicleMapper;


    /**
     * 查询车辆
     *
     * @param vehicleId 车辆主键
     * @return 车辆
     */
    @Override
    public FvehVehicle selectFvehVehicleByVehicleId(Long vehicleId)
    {
        return fvehVehicleMapper.selectFvehVehicleByVehicleId(vehicleId);
    }

    /**
     * 查询车辆列表
     *
     * @param fvehVehicle 车辆
     * @return 车辆
     */
    @Override
    public List<FvehVehicle> selectFvehVehicleList(FvehVehicle fvehVehicle)
    {
        return fvehVehicleMapper.selectFvehVehicleList(fvehVehicle);
    }

    /**
     * 新增车辆
     *
     * @param fvehVehicle 车辆
     * @return 结果
     */
    @Override
    public int insertFvehVehicle(FvehVehicle fvehVehicle)
    {
//        获取剩余车位信息
        Long remainSpaces = getRemainSpaces(fvehVehicle.getOwnerId());
        if (remainSpaces <= 0){
            throw new UserException("车位已满");
        }
        return fvehVehicleMapper.insertFvehVehicle(fvehVehicle);
    }

//    剥离出这个方法----获取剩余车位并更新车位信息
    @Override
    public Long getRemainSpaces(Long ownerId){
        FvehOwner fvehOwner = fvehOwnerMapper.selectFvehOwnerByOwnerId(ownerId);
        Long parkingSpaces = fvehOwner.getParkingSpaces();
        FvehVehicle fvehVehicle1 = new FvehVehicle();
        fvehVehicle1.setOwnerId(ownerId);
        fvehVehicle1.setVehiclePermit(1);
        List<FvehVehicle> fvehVehicles = fvehVehicleMapper.selectFvehVehicleList(fvehVehicle1);
        Long usedSpaces = (long) fvehVehicles.size();
//        更新车位信息
        fvehOwner.setUsedSpaces(usedSpaces);
        fvehOwner.setRemainSpaces(parkingSpaces - usedSpaces);
        fvehOwnerMapper.updateFvehOwner(fvehOwner);
        return parkingSpaces - usedSpaces;
    }

    /**
     * 修改车辆
     *
     * @param fvehVehicle 车辆
     * @return 结果
     */
    @Override
    public int updateFvehVehicle(FvehVehicle fvehVehicle)
    {
//        判断打开开关后车位是否已满
        Long remainSpaces1 = getRemainSpaces(fvehVehicle.getOwnerId());
//        修改了车位信息--打开开关
        if (fvehVehicle.getVehiclePermit() == 1){
            if (remainSpaces1 <= 0){
                throw new UserException("车位已满");
            }
        }
        return fvehVehicleMapper.updateFvehVehicle(fvehVehicle);
    }

    /**
     * 批量删除车辆
     *
     * @param vehicleIds 需要删除的车辆主键
     * @return 结果
     */
    @Override
    public int deleteFvehVehicleByVehicleIds(Long[] vehicleIds)
    {
        return fvehVehicleMapper.deleteFvehVehicleByVehicleIds(vehicleIds);
    }

    @Override
    public int deleteFvehVehicleByOwnerId(Long ownerId) {
        return fvehVehicleMapper.deleteFvehVehicleByOwnerId(ownerId);
    }

    /**
     * 删除车辆信息
     *
     * @param vehicleId 车辆主键
     * @return 结果
     */
    @Override
    public int deleteFvehVehicleByVehicleId(Long vehicleId)
    {
        return fvehVehicleMapper.deleteFvehVehicleByVehicleId(vehicleId);
    }
}

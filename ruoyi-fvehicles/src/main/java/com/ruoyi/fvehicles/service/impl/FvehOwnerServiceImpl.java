package com.ruoyi.fvehicles.service.impl;

import java.util.List;

import com.ruoyi.common.exception.GlobalException;
import com.ruoyi.fvehicles.domain.FvehVehicle;
import com.ruoyi.fvehicles.mapper.FvehVehicleMapper;
import com.ruoyi.fvehicles.service.IFvehVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fvehicles.mapper.FvehOwnerMapper;
import com.ruoyi.fvehicles.domain.FvehOwner;
import com.ruoyi.fvehicles.service.IFvehOwnerService;

/**
 * 车主Service业务层处理
 *
 * @author wxq
 * @date 2024-03-07
 */
@Service
public class FvehOwnerServiceImpl implements IFvehOwnerService
{
    @Autowired
    private FvehOwnerMapper fvehOwnerMapper;

    @Autowired
    private FvehVehicleMapper fvehVehicleMapper;

    @Autowired
    private IFvehVehicleService fvehVehicleService;

    /**
     * 查询车主
     *
     * @param ownerId 车主主键
     * @return 车主
     */
    @Override
    public FvehOwner selectFvehOwnerByOwnerId(Long ownerId)
    {

        return fvehOwnerMapper.selectFvehOwnerByOwnerId(ownerId);
    }

//    根据身份证号查询车主信息
    @Override
    public FvehOwner selectFvehOwnerByOwnerIdCard(String ownerIdCard) {
        return fvehOwnerMapper.selectFvehOwnerByOwnerIdCard(ownerIdCard);
    }
//    更新全部车位信息
    @Override
    public void updateAllSpaces() {
        List<FvehOwner> fvehOwners = fvehOwnerMapper.selectFvehOwnerList(new FvehOwner());
        for (FvehOwner fvehOwner : fvehOwners) {
            fvehVehicleService.syncFvehOwner(fvehOwner.getOwnerId());
        }
    }

//    更新车位信息
//    @Override
//    public void updateSpaces(Long ownerId) {
////        获取车主信息
//        FvehOwner fvehOwner = fvehOwnerMapper.selectFvehOwnerByOwnerId(ownerId);
////        获取车位信息
//        Long spaces = fvehOwner.getParkingSpaces();
////        获取已使用车位信息---查询车辆表
//        FvehVehicle fvehVehicle = new FvehVehicle();
//        fvehVehicle.setOwnerId(ownerId);
//        fvehVehicle.setVehiclePermit(1);
//        List<FvehVehicle> fvehVehicles = fvehVehicleMapper.selectFvehVehicleList(fvehVehicle);
//        Long usedSpaces = (long) fvehVehicles.size();
////        计算剩余车位
//        Long remainSpaces = spaces - usedSpaces;
////        更新车位信息
//        fvehOwner.setUsedSpaces(usedSpaces);
//        fvehOwner.setRemainSpaces(remainSpaces);
//        fvehOwnerMapper.updateFvehOwner(fvehOwner);
//    }

    /**
     * 查询车主列表
     *
     * @param fvehOwner 车主
     * @return 车主
     */
    @Override
    public List<FvehOwner> selectFvehOwnerList(FvehOwner fvehOwner)
    {
        updateAllSpaces();
        return fvehOwnerMapper.selectFvehOwnerList(fvehOwner);
    }

    /**
     * 新增车主
     *
     * @param fvehOwner 车主
     * @return 结果
     */
    @Override
    public int insertFvehOwner(FvehOwner fvehOwner)
    {
//        在添加车主信息时，设置车主剩余车位号为车位数
        fvehOwner.setRemainSpaces(fvehOwner.getParkingSpaces());
        return fvehOwnerMapper.insertFvehOwner(fvehOwner);
    }

    /**
     * 修改车主
     *
     * @param f vehOwner 车主
     * @return 结果
     */
    @Override
    public int updateFvehOwner(FvehOwner fvehOwner_new)
    {
//        修改车主信息时，若修改车位数小于已有车位数，则关闭所有车辆许可，更新剩余车位数，若大于已用车位数，则只修改车位数
        FvehOwner fvehOwner_old = fvehOwnerMapper.selectFvehOwnerByOwnerId(fvehOwner_new.getOwnerId());
        if(fvehOwner_new.getParkingSpaces() < fvehOwner_old.getUsedSpaces()) {
//            关闭所有车辆许可
            FvehVehicle fvehVehicle = new FvehVehicle();
            fvehVehicle.setOwnerId(fvehOwner_new.getOwnerId());
            fvehVehicle.setVehiclePermit(1);
            List<FvehVehicle> fvehVehicles = fvehVehicleMapper.selectFvehVehicleList(fvehVehicle);
            for (FvehVehicle fvehVehicle1 : fvehVehicles) {
                fvehVehicle1.setVehiclePermit(0);
                fvehVehicleMapper.updateFvehVehicle(fvehVehicle1);
            }
//            返回消息提示-1表示关闭车位许可
            fvehOwner_new.setRemainSpaces(fvehOwner_new.getParkingSpaces());
            fvehOwner_new.setUsedSpaces(0L);
            fvehOwnerMapper.updateFvehOwner(fvehOwner_new);
            return -1;
        }
//            更新车位信息
        fvehOwner_new.setRemainSpaces(fvehOwner_new.getParkingSpaces() - fvehOwner_new.getUsedSpaces());
        return fvehOwnerMapper.updateFvehOwner(fvehOwner_new);
    }

    /**
     * 批量删除车主
     *
     * @param ownerIds 需要删除的车主主键
     * @return 结果
     */
    @Override
    public int deleteFvehOwnerByOwnerIds(Long[] ownerIds)
    {
//        删除车主id下的所有车辆信息
        for (Long ownerId : ownerIds) {
            deleteFvehVehicleByOwnerId(ownerId);
        }
        return fvehOwnerMapper.deleteFvehOwnerByOwnerIds(ownerIds);
    }

//    根据车主id删除汽车信息
    @Override
    public int deleteFvehVehicleByOwnerId(Long ownerId) {
        return fvehVehicleMapper.deleteFvehVehicleByOwnerId(ownerId);
    }

    /**
     * 删除车主信息
     *
     * @param ownerId 车主主键
     * @return 结果
     */
    @Override
    public int deleteFvehOwnerByOwnerId(Long ownerId)
    {
//        删除该车主id下的所有车辆信息
        deleteFvehVehicleByOwnerId(ownerId);
        return fvehOwnerMapper.deleteFvehOwnerByOwnerId(ownerId);
    }

    //    车主id模糊查询，返回车主列表
    @Override
    public List<FvehOwner> selectFvehOwnerByOwnerIdCardLike(Long ownerId){
        return fvehOwnerMapper.selectFvehOwnerByOwnerIdCardLike(ownerId);
    }
}

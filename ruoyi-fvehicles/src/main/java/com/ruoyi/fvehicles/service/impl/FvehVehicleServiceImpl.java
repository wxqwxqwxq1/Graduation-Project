package com.ruoyi.fvehicles.service.impl;

import java.util.List;
import java.util.Objects;

import com.ruoyi.common.exception.GlobalException;
import com.ruoyi.fvehicles.mapper.FvehGarageVehicleMapper;
import com.ruoyi.fvehicles.mapper.FvehOwnerMapper;
import com.ruoyi.manage.mapper.ManageGarageMapper;
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
    @Autowired
    private ManageGarageMapper manageGarageService;
    @Autowired
    private IFvehVehicleService fvehVehicleService;


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
            throw new GlobalException("该用户购买车位已满");
        }
//        如果添加成功，更新车位信息
        if (0 < fvehVehicleMapper.insertFvehVehicle(fvehVehicle)){
            return syncFvehOwner(fvehVehicle.getOwnerId());
        }
        return 0 ;
    }

//    剥离出这个方法----获取车主剩余车位并更新车位信息
    @Override
    public Long getRemainSpaces(Long ownerId){
//        获取车主信息
        FvehOwner fvehOwner = fvehOwnerMapper.selectFvehOwnerByOwnerId(ownerId);
        Long parkingSpaces = fvehOwner.getParkingSpaces();
//        获取已使用车位信息---查询车辆表
        FvehVehicle fvehVehicle1 = new FvehVehicle();
        fvehVehicle1.setOwnerId(ownerId);
        fvehVehicle1.setVehiclePermit(1);
        List<FvehVehicle> fvehVehicles = fvehVehicleMapper.selectFvehVehicleList(fvehVehicle1);
        Long usedSpaces = (long) fvehVehicles.size();
        System.out.println("usedSpaces = " + usedSpaces);
//        更新车位信息
        fvehOwner.setUsedSpaces(usedSpaces);
        fvehOwner.setRemainSpaces(parkingSpaces - usedSpaces);
//        如果剩余车位小于0，更新车位许可状态ru
        if (fvehOwner.getRemainSpaces() < 0){
//            关闭所有车辆许可
            FvehVehicle fvehVehicle = new FvehVehicle();
            fvehVehicle.setOwnerId(ownerId);
            fvehVehicle.setVehiclePermit(1);
            List<FvehVehicle> fvehVehicles1 = fvehVehicleMapper.selectFvehVehicleList(fvehVehicle);
            for (FvehVehicle fvehVehicle2 : fvehVehicles1) {
                fvehVehicle2.setVehiclePermit(0);
                fvehVehicleMapper.updateFvehVehicle(fvehVehicle2);
            }
        }
        fvehOwnerMapper.updateFvehOwner(fvehOwner);
        return parkingSpaces - usedSpaces;
    }

    /**
     * 修改车辆
     *
     * @param   fvehVehicle_new 车辆
     * @return 结果
     */
    @Override
    public int updateFvehVehicle(FvehVehicle fvehVehicle_new) {
//        判断打开开关后用户车位是否已满
        Long remainSpaces1 = getRemainSpaces(fvehVehicle_new.getOwnerId());
//        获取用户数据库信息
        FvehVehicle fvehVehicle_old = fvehVehicleMapper.selectFvehVehicleByVehicleId(fvehVehicle_new.getVehicleId());
//      判断车位许可是否变化
        if (!Objects.equals(fvehVehicle_old.getVehiclePermit(), fvehVehicle_new.getVehiclePermit())) {
//            车位许可变化---1.关闭车位许可
            if (fvehVehicle_new.getVehiclePermit() == 0) {
//                在修改车辆许可为关时，要同步设置该车位的许可状态为关
                fvehGarageVehicleMapper.updateFvehGarageVehicleByVehicleId(fvehVehicle_new.getVehicleId(),0);
            } else {
//                在修改车辆许可为开时，要判断车位是否已满
                if (remainSpaces1 <= 0) {
//                判断车主购买车位已满
                    throw new GlobalException("该用户购买车位已满");
                }
            }
        }
        if(fvehVehicleMapper.updateFvehVehicle(fvehVehicle_new)>0){
            syncFvehOwner(fvehVehicle_old.getOwnerId());
            return syncFvehOwner(fvehVehicle_new.getOwnerId());
        }
        return 0;
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
//        记录车辆主人id
        Long ownerId = fvehVehicleMapper.selectFvehVehicleByVehicleId(vehicleIds[0]).getOwnerId();
//        如果删除成功，更新车位信息
        if (0 < fvehVehicleMapper.deleteFvehVehicleByVehicleIds(vehicleIds)){
            return syncFvehOwner(ownerId);
        }
        return 0;
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
//        如果删除成功，更新车位信息
        if (0 < fvehVehicleMapper.deleteFvehVehicleByVehicleId(vehicleId)){
            return syncFvehOwner(fvehVehicleMapper.selectFvehVehicleByVehicleId(vehicleId).getOwnerId());
        }
        return 0;
    }

    //    写一个方法，传入车主id，去车辆表中查询车辆个数，然后更新车主表的剩余车位和已用车位
    /**
     * 同步车位信息
     *
     * @param  ownerId 车辆主键
     * @return 结果
     */
    public int syncFvehOwner(Long ownerId){
//        去车辆表中查询车辆个数，然后更新车主表的剩余车位和已用车位
        Long remainSpaces = getRemainSpaces(ownerId);
        return 1;
    }
}

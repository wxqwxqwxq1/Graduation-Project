package com.ruoyi.fvehicles.service;

import java.util.List;
import com.ruoyi.fvehicles.domain.FvehOwner;

/**
 * 车主Service接口
 *
 * @author ruoyi
 * @date 2024-03-07
 */
public interface IFvehOwnerService
{
    /**
     * 查询车主
     *
     * @param ownerId 车主主键
     * @return 车主
     */
    public FvehOwner selectFvehOwnerByOwnerId(Long ownerId);

    // 根据身份证号判断用户是否存在
    FvehOwner selectFvehOwnerByOwnerIdCard(String ownerIdCard);

    //    更新全部车位信息
    void updateAllSpaces();


    /**
     * 查询车主列表
     *
     * @param fvehOwner 车主
     * @return 车主集合
     */
    public List<FvehOwner> selectFvehOwnerList(FvehOwner fvehOwner);

    /**
     * 新增车主
     *
     * @param fvehOwner 车主
     * @return 结果
     */
    public int insertFvehOwner(FvehOwner fvehOwner);

    /**
     * 修改车主
     *
     * @param fvehOwner 车主
     * @return 结果
     */
    public int updateFvehOwner(FvehOwner fvehOwner);

    /**
     * 批量删除车主
     *
     * @param ownerIds 需要删除的车主主键集合
     * @return 结果
     */
    public int deleteFvehOwnerByOwnerIds(Long[] ownerIds);

    //    根据车主id删除汽车信息
    int deleteFvehVehicleByOwnerId(Long ownerId);

    /**
     * 删除车主信息
     *
     * @param ownerId 车主主键
     * @return 结果
     */
    public int deleteFvehOwnerByOwnerId(Long ownerId);

//    车主id模糊查询，返回车主列表
    List<FvehOwner> selectFvehOwnerByOwnerIdCardLike(Long ownerId);
}

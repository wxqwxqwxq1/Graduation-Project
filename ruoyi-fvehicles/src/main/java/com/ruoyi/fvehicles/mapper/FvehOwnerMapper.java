package com.ruoyi.fvehicles.mapper;

import java.util.List;
import com.ruoyi.fvehicles.domain.FvehOwner;

/**
 * 车主Mapper接口
 */
public interface FvehOwnerMapper
{
    /**
     * 查询车主
     *
     * @param ownerId 车主主键
     * @return 车主
     */
    public FvehOwner selectFvehOwnerByOwnerId(Long ownerId);

    /**
     * 根据身份证号查询车主
     *
     * @param ownerIdCard 车主主键
     * @return 车主
     */
    public FvehOwner selectFvehOwnerByOwnerIdCard(String ownerIdCard);

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
     * 删除车主
     *
     * @param ownerId 车主主键
     * @return 结果
     */
    public int deleteFvehOwnerByOwnerId(Long ownerId);

    /**
     * 批量删除车主
     *
     * @param ownerIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFvehOwnerByOwnerIds(Long[] ownerIds);

    void updateAllSpaces();

//    编号模糊查询，返回车主列表
    List<FvehOwner> selectFvehOwnerByOwnerIdCardLike(Long ownerIdCard);
}

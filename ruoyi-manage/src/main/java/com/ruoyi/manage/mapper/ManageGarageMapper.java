package com.ruoyi.manage.mapper;

import java.util.List;
import com.ruoyi.manage.domain.ManageGarage;

/**
 *  车库Mapper接口
 *
 * @author wxq
 * @date 2024-03-10
 */
public interface ManageGarageMapper
{
    /**
     * 查询 车库
     *
     * @param garageId  车库主键
     * @return  车库
     */
    public ManageGarage selectManageGarageByGarageId(Long garageId);

    /**
     * 查询 车库列表
     *
     * @param manageGarage  车库
     * @return  车库集合
     */
    public List<ManageGarage> selectManageGarageList(ManageGarage manageGarage);

    /**
     * 新增 车库
     *
     * @param manageGarage  车库
     * @return 结果
     */
    public int insertManageGarage(ManageGarage manageGarage);

    /**
     * 修改 车库
     *
     * @param manageGarage  车库
     * @return 结果
     */
    public int updateManageGarage(ManageGarage manageGarage);

    /**
     * 删除 车库
     *
     * @param garageId  车库主键
     * @return 结果
     */
    public int deleteManageGarageByGarageId(Long garageId);

    /**
     * 批量删除 车库
     *
     * @param garageIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteManageGarageByGarageIds(Long[] garageIds);

    /**
     * 更新车库剩余容量
     *
     * @param garageId 车库ID
     */
    public int updateRemainingCapacity(Long garageId);

}

package com.ruoyi.manage.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.ManageGarageMapper;
import com.ruoyi.manage.domain.ManageGarage;
import com.ruoyi.manage.service.IManageGarageService;

/**
 *  车库Service业务层处理
 *
 * @author wxq
 * @date 2024-03-10
 */
@Service
public class ManageGarageServiceImpl implements IManageGarageService
{
    @Autowired
    private ManageGarageMapper manageGarageMapper;

    /**
     * 查询 车库
     *
     * @param garageId  车库主键
     * @return  车库
     */
    @Override
    public ManageGarage selectManageGarageByGarageId(Long garageId)
    {
        return manageGarageMapper.selectManageGarageByGarageId(garageId);
    }

    /**
     * 查询 车库列表
     *
     * @param manageGarage  车库
     * @return  车库
     */
    @Override
    public List<ManageGarage> selectManageGarageList(ManageGarage manageGarage)
    {
        return manageGarageMapper.selectManageGarageList(manageGarage);
    }

    /**
     * 新增 车库
     *
     * @param manageGarage  车库
     * @return 结果
     */
    @Override
    public int insertManageGarage(ManageGarage manageGarage)
    {
        return manageGarageMapper.insertManageGarage(manageGarage);
    }

    /**
     * 修改 车库
     *
     * @param manageGarage  车库
     * @return 结果
     */
    @Override
    public int updateManageGarage(ManageGarage manageGarage)
    {
        return manageGarageMapper.updateManageGarage(manageGarage);
    }

    /**
     * 批量删除 车库
     *
     * @param garageIds 需要删除的 车库主键
     * @return 结果
     */
    @Override
    public int deleteManageGarageByGarageIds(Long[] garageIds)
    {
        return manageGarageMapper.deleteManageGarageByGarageIds(garageIds);
    }

    /**
     * 删除 车库信息
     *
     * @param garageId  车库主键
     * @return 结果
     */
    @Override
    public int deleteManageGarageByGarageId(Long garageId)
    {
        return manageGarageMapper.deleteManageGarageByGarageId(garageId);
    }
}

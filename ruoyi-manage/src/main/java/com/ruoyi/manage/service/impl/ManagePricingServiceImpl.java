package com.ruoyi.manage.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.ManagePricingMapper;
import com.ruoyi.manage.domain.ManagePricing;
import com.ruoyi.manage.service.IManagePricingService;

/**
 * 价格表Service业务层处理
 *
 * @author wxq
 * @date 2024-03-20
 */
@Service
public class ManagePricingServiceImpl implements IManagePricingService
{
    @Autowired
    private ManagePricingMapper managePricingMapper;

    /**
     * 查询价格表
     *
     * @param pricingId 价格表主键
     * @return 价格表
     */
    @Override
    public ManagePricing selectManagePricingByPricingId(Long pricingId)
    {
        return managePricingMapper.selectManagePricingByPricingId(pricingId);
    }

    /**
     * 查询价格表列表
     *
     * @param managePricing 价格表
     * @return 价格表
     */
    @Override
    public List<ManagePricing> selectManagePricingList(ManagePricing managePricing)
    {
        return managePricingMapper.selectManagePricingList(managePricing);
    }

    /**
     * 新增价格表
     *
     * @param managePricing 价格表
     * @return 结果
     */
    @Override
    public int insertManagePricing(ManagePricing managePricing)
    {
        return managePricingMapper.insertManagePricing(managePricing);
    }

    /**
     * 修改价格表
     *
     * @param managePricing 价格表
     * @return 结果
     */
    @Override
    public int updateManagePricing(ManagePricing managePricing)
    {
        return managePricingMapper.updateManagePricing(managePricing);
    }

    /**
     * 批量删除价格表
     *
     * @param pricingIds 需要删除的价格表主键
     * @return 结果
     */
    @Override
    public int deleteManagePricingByPricingIds(Long[] pricingIds)
    {
        return managePricingMapper.deleteManagePricingByPricingIds(pricingIds);
    }

    /**
     * 删除价格表信息
     *
     * @param pricingId 价格表主键
     * @return 结果
     */
    @Override
    public int deleteManagePricingByPricingId(Long pricingId)
    {
        return managePricingMapper.deleteManagePricingByPricingId(pricingId);
    }
}

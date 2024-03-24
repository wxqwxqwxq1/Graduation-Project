package com.ruoyi.manage.mapper;

import java.util.List;
import com.ruoyi.manage.domain.ManagePricing;

/**
 * 价格表Mapper接口
 *
 * @author wxq
 * @date 2024-03-20
 */
public interface ManagePricingMapper
{
    /**
     * 查询价格表
     *
     * @param pricingId 价格表主键
     * @return 价格表
     */
    public ManagePricing selectManagePricingByPricingId(Long pricingId);

    /**
     * 查询价格表列表
     *
     * @param managePricing 价格表
     * @return 价格表集合
     */
    public List<ManagePricing> selectManagePricingList(ManagePricing managePricing);

    /**
     * 新增价格表
     *
     * @param managePricing 价格表
     * @return 结果
     */
    public int insertManagePricing(ManagePricing managePricing);

    /**
     * 修改价格表
     *
     * @param managePricing 价格表
     * @return 结果
     */
    public int updateManagePricing(ManagePricing managePricing);

    /**
     * 删除价格表
     *
     * @param pricingId 价格表主键
     * @return 结果
     */
    public int deleteManagePricingByPricingId(Long pricingId);

    /**
     * 批量删除价格表
     *
     * @param pricingIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteManagePricingByPricingIds(Long[] pricingIds);
}

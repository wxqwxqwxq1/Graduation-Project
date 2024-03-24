package com.ruoyi.manage.service;

import java.util.List;
import com.ruoyi.manage.domain.ViewPricingGarageInfo;

/**
 * 车库-价格视图Service接口
 *
 * @author wxq
 * @date 2024-03-20
 */
public interface IViewPricingGarageInfoService
{
    /**
     * 查询车库-价格视图
     *
     * @param pricingId 车库-价格视图主键
     * @return 车库-价格视图
     */
    public ViewPricingGarageInfo selectViewPricingGarageInfoByPricingId(Long pricingId);

    /**
     * 查询车库-价格视图列表
     *
     * @param viewPricingGarageInfo 车库-价格视图
     * @return 车库-价格视图集合
     */
    public List<ViewPricingGarageInfo> selectViewPricingGarageInfoList(ViewPricingGarageInfo viewPricingGarageInfo);

    /**
     * 新增车库-价格视图
     *
     * @param viewPricingGarageInfo 车库-价格视图
     * @return 结果
     */
    public int insertViewPricingGarageInfo(ViewPricingGarageInfo viewPricingGarageInfo);

    /**
     * 修改车库-价格视图
     *
     * @param viewPricingGarageInfo 车库-价格视图
     * @return 结果
     */
    public int updateViewPricingGarageInfo(ViewPricingGarageInfo viewPricingGarageInfo);

    /**
     * 批量删除车库-价格视图
     *
     * @param pricingIds 需要删除的车库-价格视图主键集合
     * @return 结果
     */
    public int deleteViewPricingGarageInfoByPricingIds(Long[] pricingIds);

    /**
     * 删除车库-价格视图信息
     *
     * @param pricingId 车库-价格视图主键
     * @return 结果
     */
    public int deleteViewPricingGarageInfoByPricingId(Long pricingId);
}

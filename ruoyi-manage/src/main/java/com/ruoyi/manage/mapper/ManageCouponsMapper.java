package com.ruoyi.manage.mapper;

import java.util.List;
import com.ruoyi.manage.domain.ManageCoupons;

/**
 * 优惠券表Mapper接口
 *
 * @author wxq
 * @date 2024-03-20
 */
public interface ManageCouponsMapper
{
    /**
     * 查询优惠券表
     *
     * @param couponId 优惠券表主键
     * @return 优惠券表
     */
    public ManageCoupons selectManageCouponsByCouponId(Long couponId);

    /**
     * 查询优惠券表列表
     *
     * @param manageCoupons 优惠券表
     * @return 优惠券表集合
     */
    public List<ManageCoupons> selectManageCouponsList(ManageCoupons manageCoupons);

    /**
     * 新增优惠券表
     *
     * @param manageCoupons 优惠券表
     * @return 结果
     */
    public int insertManageCoupons(ManageCoupons manageCoupons);

    /**
     * 修改优惠券表
     *
     * @param manageCoupons 优惠券表
     * @return 结果
     */
    public int updateManageCoupons(ManageCoupons manageCoupons);

    /**
     * 删除优惠券表
     *
     * @param couponId 优惠券表主键
     * @return 结果
     */
    public int deleteManageCouponsByCouponId(Long couponId);

    /**
     * 批量删除优惠券表
     *
     * @param couponIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteManageCouponsByCouponIds(Long[] couponIds);
}

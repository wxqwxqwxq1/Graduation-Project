package com.ruoyi.manage.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.ManageCouponsMapper;
import com.ruoyi.manage.domain.ManageCoupons;
import com.ruoyi.manage.service.IManageCouponsService;

/**
 * 优惠券表Service业务层处理
 *
 * @author wxq
 * @date 2024-03-20
 */
@Service
public class ManageCouponsServiceImpl implements IManageCouponsService
{
    @Autowired
    private ManageCouponsMapper manageCouponsMapper;

    /**
     * 查询优惠券表
     *
     * @param couponId 优惠券表主键
     * @return 优惠券表
     */
    @Override
    public ManageCoupons selectManageCouponsByCouponId(Long couponId)
    {
        return manageCouponsMapper.selectManageCouponsByCouponId(couponId);
    }

    /**
     * 查询优惠券表列表
     *
     * @param manageCoupons 优惠券表
     * @return 优惠券表
     */
    @Override
    public List<ManageCoupons> selectManageCouponsList(ManageCoupons manageCoupons)
    {
        return manageCouponsMapper.selectManageCouponsList(manageCoupons);
    }

    /**
     * 新增优惠券表
     *
     * @param manageCoupons 优惠券表
     * @return 结果
     */
    @Override
    public int insertManageCoupons(ManageCoupons manageCoupons)
    {
        return manageCouponsMapper.insertManageCoupons(manageCoupons);
    }

    /**
     * 修改优惠券表
     *
     * @param manageCoupons 优惠券表
     * @return 结果
     */
    @Override
    public int updateManageCoupons(ManageCoupons manageCoupons)
    {
        return manageCouponsMapper.updateManageCoupons(manageCoupons);
    }

    /**
     * 批量删除优惠券表
     *
     * @param couponIds 需要删除的优惠券表主键
     * @return 结果
     */
    @Override
    public int deleteManageCouponsByCouponIds(Long[] couponIds)
    {
        return manageCouponsMapper.deleteManageCouponsByCouponIds(couponIds);
    }

    /**
     * 删除优惠券表信息
     *
     * @param couponId 优惠券表主键
     * @return 结果
     */
    @Override
    public int deleteManageCouponsByCouponId(Long couponId)
    {
        return manageCouponsMapper.deleteManageCouponsByCouponId(couponId);
    }
}

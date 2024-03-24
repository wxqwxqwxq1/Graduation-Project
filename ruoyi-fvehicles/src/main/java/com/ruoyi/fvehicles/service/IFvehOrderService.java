package com.ruoyi.fvehicles.service;

import java.util.List;
import com.ruoyi.fvehicles.domain.FvehOrder;

/**
 * 订单Service接口
 *
 * @author wxq
 * @date 2024-03-20
 */
public interface IFvehOrderService
{
    /**
     * 查询订单
     *
     * @param orderId 订单主键
     * @return 订单
     */
    public FvehOrder selectFvehOrderByOrderId(String orderId);

    /**
     * 查询订单列表
     *
     * @param fvehOrder 订单
     * @return 订单集合
     */
    public List<FvehOrder> selectFvehOrderList(FvehOrder fvehOrder);

    /**
     * 新增订单
     *
     * @param fvehOrder 订单
     * @return 结果
     */
    public int insertFvehOrder(FvehOrder fvehOrder);

    /**
     * 修改订单
     *
     * @param fvehOrder 订单
     * @return 结果
     */
    public int updateFvehOrder(FvehOrder fvehOrder);

    /**
     * 批量删除订单
     *
     * @param orderIds 需要删除的订单主键集合
     * @return 结果
     */
    public int deleteFvehOrderByOrderIds(String[] orderIds);

    /**
     * 删除订单信息
     *
     * @param orderId 订单主键
     * @return 结果
     */
    public int deleteFvehOrderByOrderId(String orderId);
}

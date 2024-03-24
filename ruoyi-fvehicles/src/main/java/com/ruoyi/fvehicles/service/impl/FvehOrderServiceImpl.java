package com.ruoyi.fvehicles.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fvehicles.mapper.FvehOrderMapper;
import com.ruoyi.fvehicles.domain.FvehOrder;
import com.ruoyi.fvehicles.service.IFvehOrderService;

/**
 * 订单Service业务层处理
 *
 * @author wxq
 * @date 2024-03-20
 */
@Service
public class FvehOrderServiceImpl implements IFvehOrderService
{
    @Autowired
    private FvehOrderMapper fvehOrderMapper;

    /**
     * 查询订单
     *
     * @param orderId 订单主键
     * @return 订单
     */
    @Override
    public FvehOrder selectFvehOrderByOrderId(String orderId)
    {
        return fvehOrderMapper.selectFvehOrderByOrderId(orderId);
    }

    /**
     * 查询订单列表
     *
     * @param fvehOrder 订单
     * @return 订单
     */
    @Override
    public List<FvehOrder> selectFvehOrderList(FvehOrder fvehOrder)
    {
        return fvehOrderMapper.selectFvehOrderList(fvehOrder);
    }

    /**
     * 新增订单
     *
     * @param fvehOrder 订单
     * @return 结果
     */
    @Override
    public int insertFvehOrder(FvehOrder fvehOrder)
    {
        return fvehOrderMapper.insertFvehOrder(fvehOrder);
    }

    /**
     * 修改订单
     *
     * @param fvehOrder 订单
     * @return 结果
     */
    @Override
    public int updateFvehOrder(FvehOrder fvehOrder)
    {
        return fvehOrderMapper.updateFvehOrder(fvehOrder);
    }

    /**
     * 批量删除订单
     *
     * @param orderIds 需要删除的订单主键
     * @return 结果
     */
    @Override
    public int deleteFvehOrderByOrderIds(String[] orderIds)
    {
        return fvehOrderMapper.deleteFvehOrderByOrderIds(orderIds);
    }

    /**
     * 删除订单信息
     *
     * @param orderId 订单主键
     * @return 结果
     */
    @Override
    public int deleteFvehOrderByOrderId(String orderId)
    {
        return fvehOrderMapper.deleteFvehOrderByOrderId(orderId);
    }
}

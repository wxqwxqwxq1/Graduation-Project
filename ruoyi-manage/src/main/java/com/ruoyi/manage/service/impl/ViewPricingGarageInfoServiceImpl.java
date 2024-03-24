package com.ruoyi.manage.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.ViewPricingGarageInfoMapper;
import com.ruoyi.manage.domain.ViewPricingGarageInfo;
import com.ruoyi.manage.service.IViewPricingGarageInfoService;

/**
 * 车库-价格视图Service业务层处理
 *
 * @author wxq
 * @date 2024-03-20
 */
@Service
public class ViewPricingGarageInfoServiceImpl implements IViewPricingGarageInfoService
{
    @Autowired
    private ViewPricingGarageInfoMapper viewPricingGarageInfoMapper;

    /**
     * 查询车库-价格视图
     *
     * @param pricingId 车库-价格视图主键
     * @return 车库-价格视图
     */
    @Override
    public ViewPricingGarageInfo selectViewPricingGarageInfoByPricingId(Long pricingId)
    {
        return viewPricingGarageInfoMapper.selectViewPricingGarageInfoByPricingId(pricingId);
    }

    /**
     * 查询车库-价格视图列表
     *
     * @param viewPricingGarageInfo 车库-价格视图
     * @return 车库-价格视图
     */
    @Override
    public List<ViewPricingGarageInfo> selectViewPricingGarageInfoList(ViewPricingGarageInfo viewPricingGarageInfo)
    {
        return viewPricingGarageInfoMapper.selectViewPricingGarageInfoList(viewPricingGarageInfo);
    }

    /**
     * 新增车库-价格视图
     *
     * @param viewPricingGarageInfo 车库-价格视图
     * @return 结果
     */
    @Override
    public int insertViewPricingGarageInfo(ViewPricingGarageInfo viewPricingGarageInfo)
    {
        return viewPricingGarageInfoMapper.insertViewPricingGarageInfo(viewPricingGarageInfo);
    }

    /**
     * 修改车库-价格视图
     *
     * @param viewPricingGarageInfo 车库-价格视图
     * @return 结果
     */
    @Override
    public int updateViewPricingGarageInfo(ViewPricingGarageInfo viewPricingGarageInfo)
    {
        return viewPricingGarageInfoMapper.updateViewPricingGarageInfo(viewPricingGarageInfo);
    }

    /**
     * 批量删除车库-价格视图
     *
     * @param pricingIds 需要删除的车库-价格视图主键
     * @return 结果
     */
    @Override
    public int deleteViewPricingGarageInfoByPricingIds(Long[] pricingIds)
    {
        return viewPricingGarageInfoMapper.deleteViewPricingGarageInfoByPricingIds(pricingIds);
    }

    /**
     * 删除车库-价格视图信息
     *
     * @param pricingId 车库-价格视图主键
     * @return 结果
     */
    @Override
    public int deleteViewPricingGarageInfoByPricingId(Long pricingId)
    {
        return viewPricingGarageInfoMapper.deleteViewPricingGarageInfoByPricingId(pricingId);
    }
}

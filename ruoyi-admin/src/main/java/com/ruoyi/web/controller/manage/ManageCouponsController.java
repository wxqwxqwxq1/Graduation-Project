package com.ruoyi.manage.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.manage.domain.ManageCoupons;
import com.ruoyi.manage.service.IManageCouponsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 优惠券表Controller
 *
 * @author wxq
 * @date 2024-03-20
 */
@RestController
@RequestMapping("/manage/coupons")
public class ManageCouponsController extends BaseController
{
    @Autowired
    private IManageCouponsService manageCouponsService;

    /**
     * 查询优惠券表列表
     */
    @PreAuthorize("@ss.hasPermi('manage:coupons:list')")
    @GetMapping("/list")
    public TableDataInfo list(ManageCoupons manageCoupons)
    {
        startPage();
        List<ManageCoupons> list = manageCouponsService.selectManageCouponsList(manageCoupons);
        return getDataTable(list);
    }

    /**
     * 导出优惠券表列表
     */
    @PreAuthorize("@ss.hasPermi('manage:coupons:export')")
    @Log(title = "优惠券表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ManageCoupons manageCoupons)
    {
        List<ManageCoupons> list = manageCouponsService.selectManageCouponsList(manageCoupons);
        ExcelUtil<ManageCoupons> util = new ExcelUtil<ManageCoupons>(ManageCoupons.class);
        util.exportExcel(response, list, "优惠券表数据");
    }

    /**
     * 获取优惠券表详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:coupons:query')")
    @GetMapping(value = "/{couponId}")
    public AjaxResult getInfo(@PathVariable("couponId") Long couponId)
    {
        return success(manageCouponsService.selectManageCouponsByCouponId(couponId));
    }

    /**
     * 新增优惠券表
     */
    @PreAuthorize("@ss.hasPermi('manage:coupons:add')")
    @Log(title = "优惠券表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ManageCoupons manageCoupons)
    {
        return toAjax(manageCouponsService.insertManageCoupons(manageCoupons));
    }

    /**
     * 修改优惠券表
     */
    @PreAuthorize("@ss.hasPermi('manage:coupons:edit')")
    @Log(title = "优惠券表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ManageCoupons manageCoupons)
    {
        return toAjax(manageCouponsService.updateManageCoupons(manageCoupons));
    }

    /**
     * 删除优惠券表
     */
    @PreAuthorize("@ss.hasPermi('manage:coupons:remove')")
    @Log(title = "优惠券表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{couponIds}")
    public AjaxResult remove(@PathVariable Long[] couponIds)
    {
        return toAjax(manageCouponsService.deleteManageCouponsByCouponIds(couponIds));
    }
}

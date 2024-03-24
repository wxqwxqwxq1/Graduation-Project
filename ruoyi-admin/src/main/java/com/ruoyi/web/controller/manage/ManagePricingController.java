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
import com.ruoyi.manage.domain.ManagePricing;
import com.ruoyi.manage.service.IManagePricingService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 价格表Controller
 *
 * @author wxq
 * @date 2024-03-20
 */
@RestController
@RequestMapping("/manage/pricing")
public class ManagePricingController extends BaseController
{
    @Autowired
    private IManagePricingService managePricingService;

    /**
     * 查询价格表列表
     */
    @PreAuthorize("@ss.hasPermi('manage:pricing:list')")
    @GetMapping("/list")
    public TableDataInfo list(ManagePricing managePricing)
    {
        startPage();
        List<ManagePricing> list = managePricingService.selectManagePricingList(managePricing);
        return getDataTable(list);
    }

    /**
     * 导出价格表列表
     */
    @PreAuthorize("@ss.hasPermi('manage:pricing:export')")
    @Log(title = "价格表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ManagePricing managePricing)
    {
        List<ManagePricing> list = managePricingService.selectManagePricingList(managePricing);
        ExcelUtil<ManagePricing> util = new ExcelUtil<ManagePricing>(ManagePricing.class);
        util.exportExcel(response, list, "价格表数据");
    }

    /**
     * 获取价格表详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:pricing:query')")
    @GetMapping(value = "/{pricingId}")
    public AjaxResult getInfo(@PathVariable("pricingId") Long pricingId)
    {
        return success(managePricingService.selectManagePricingByPricingId(pricingId));
    }

    /**
     * 新增价格表
     */
    @PreAuthorize("@ss.hasPermi('manage:pricing:add')")
    @Log(title = "价格表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ManagePricing managePricing)
    {
        return toAjax(managePricingService.insertManagePricing(managePricing));
    }

    /**
     * 修改价格表
     */
    @PreAuthorize("@ss.hasPermi('manage:pricing:edit')")
    @Log(title = "价格表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ManagePricing managePricing)
    {
        return toAjax(managePricingService.updateManagePricing(managePricing));
    }

    /**
     * 删除价格表
     */
    @PreAuthorize("@ss.hasPermi('manage:pricing:remove')")
    @Log(title = "价格表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{pricingIds}")
    public AjaxResult remove(@PathVariable Long[] pricingIds)
    {
        return toAjax(managePricingService.deleteManagePricingByPricingIds(pricingIds));
    }
}

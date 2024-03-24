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
import com.ruoyi.manage.domain.ViewPricingGarageInfo;
import com.ruoyi.manage.service.IViewPricingGarageInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 车库-价格视图Controller
 *
 * @author wxq
 * @date 2024-03-20
 */
@RestController
@RequestMapping("/manage/pricingGarage")
public class ViewPricingGarageInfoController extends BaseController
{
    @Autowired
    private IViewPricingGarageInfoService viewPricingGarageInfoService;

    /**
     * 查询车库-价格视图列表
     */
    @PreAuthorize("@ss.hasPermi('manage:pricingGarage:list')")
    @GetMapping("/list")
    public TableDataInfo list(ViewPricingGarageInfo viewPricingGarageInfo)
    {
        startPage();
        List<ViewPricingGarageInfo> list = viewPricingGarageInfoService.selectViewPricingGarageInfoList(viewPricingGarageInfo);
        return getDataTable(list);
    }

    /**
     * 导出车库-价格视图列表
     */
    @PreAuthorize("@ss.hasPermi('manage:pricingGarage:export')")
    @Log(title = "车库-价格视图", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ViewPricingGarageInfo viewPricingGarageInfo)
    {
        List<ViewPricingGarageInfo> list = viewPricingGarageInfoService.selectViewPricingGarageInfoList(viewPricingGarageInfo);
        ExcelUtil<ViewPricingGarageInfo> util = new ExcelUtil<ViewPricingGarageInfo>(ViewPricingGarageInfo.class);
        util.exportExcel(response, list, "车库-价格视图数据");
    }

    /**
     * 获取车库-价格视图详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:pricingGarage:query')")
    @GetMapping(value = "/{pricingId}")
    public AjaxResult getInfo(@PathVariable("pricingId") Long pricingId)
    {
        return success(viewPricingGarageInfoService.selectViewPricingGarageInfoByPricingId(pricingId));
    }

    /**
     * 新增车库-价格视图
     */
    @PreAuthorize("@ss.hasPermi('manage:pricingGarage:add')")
    @Log(title = "车库-价格视图", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ViewPricingGarageInfo viewPricingGarageInfo)
    {
        return toAjax(viewPricingGarageInfoService.insertViewPricingGarageInfo(viewPricingGarageInfo));
    }

    /**
     * 修改车库-价格视图
     */
    @PreAuthorize("@ss.hasPermi('manage:pricingGarage:edit')")
    @Log(title = "车库-价格视图", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ViewPricingGarageInfo viewPricingGarageInfo)
    {
        return toAjax(viewPricingGarageInfoService.updateViewPricingGarageInfo(viewPricingGarageInfo));
    }

    /**
     * 删除车库-价格视图
     */
    @PreAuthorize("@ss.hasPermi('manage:pricingGarage:remove')")
    @Log(title = "车库-价格视图", businessType = BusinessType.DELETE)
    @DeleteMapping("/{pricingIds}")
    public AjaxResult remove(@PathVariable Long[] pricingIds)
    {
        return toAjax(viewPricingGarageInfoService.deleteViewPricingGarageInfoByPricingIds(pricingIds));
    }
}

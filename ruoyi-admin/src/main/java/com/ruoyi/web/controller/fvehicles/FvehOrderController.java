package com.ruoyi.fvehicles.controller;

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
import com.ruoyi.fvehicles.domain.FvehOrder;
import com.ruoyi.fvehicles.service.IFvehOrderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订单Controller
 *
 * @author wxq
 * @date 2024-03-20
 */
@RestController
@RequestMapping("/fvehicles/order")
public class FvehOrderController extends BaseController
{
    @Autowired
    private IFvehOrderService fvehOrderService;

    /**
     * 查询订单列表
     */
    @PreAuthorize("@ss.hasPermi('fvehicles:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(FvehOrder fvehOrder)
    {
        startPage();
        List<FvehOrder> list = fvehOrderService.selectFvehOrderList(fvehOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单列表
     */
    @PreAuthorize("@ss.hasPermi('fvehicles:order:export')")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FvehOrder fvehOrder)
    {
        List<FvehOrder> list = fvehOrderService.selectFvehOrderList(fvehOrder);
        ExcelUtil<FvehOrder> util = new ExcelUtil<FvehOrder>(FvehOrder.class);
        util.exportExcel(response, list, "订单数据");
    }

    /**
     * 获取订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('fvehicles:order:query')")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") String orderId)
    {
        return success(fvehOrderService.selectFvehOrderByOrderId(orderId));
    }

    /**
     * 新增订单
     */
    @PreAuthorize("@ss.hasPermi('fvehicles:order:add')")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FvehOrder fvehOrder)
    {
        return toAjax(fvehOrderService.insertFvehOrder(fvehOrder));
    }

    /**
     * 修改订单
     */
    @PreAuthorize("@ss.hasPermi('fvehicles:order:edit')")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FvehOrder fvehOrder)
    {
        return toAjax(fvehOrderService.updateFvehOrder(fvehOrder));
    }

    /**
     * 删除订单
     */
    @PreAuthorize("@ss.hasPermi('fvehicles:order:remove')")
    @Log(title = "订单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable String[] orderIds)
    {
        return toAjax(fvehOrderService.deleteFvehOrderByOrderIds(orderIds));
    }
}

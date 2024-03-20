package com.ruoyi.web.controller.fvehicles;

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
import com.ruoyi.fvehicles.domain.ViewVehicleParkingInfo;
import com.ruoyi.fvehicles.service.IViewVehicleParkingInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 车位Controller
 *
 * @author wxq
 * @date 2024-03-11
 */
@RestController
@RequestMapping("/fvehicles/park")
public class ViewVehicleParkingInfoController extends BaseController
{
    @Autowired
    private IViewVehicleParkingInfoService viewVehicleParkingInfoService;

    /**
     * 查询车位列表
     */
    @PreAuthorize("@ss.hasPermi('fvehicles:park:list')")
    @GetMapping("/list")
    public TableDataInfo list(ViewVehicleParkingInfo viewVehicleParkingInfo)
    {
        startPage();
        List<ViewVehicleParkingInfo> list = viewVehicleParkingInfoService.selectViewVehicleParkingInfoList(viewVehicleParkingInfo);
        return getDataTable(list);
    }

    /**
     * 导出车位列表
     */
    @PreAuthorize("@ss.hasPermi('fvehicles:park:export')")
    @Log(title = "车位", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ViewVehicleParkingInfo viewVehicleParkingInfo)
    {
        List<ViewVehicleParkingInfo> list = viewVehicleParkingInfoService.selectViewVehicleParkingInfoList(viewVehicleParkingInfo);
        ExcelUtil<ViewVehicleParkingInfo> util = new ExcelUtil<ViewVehicleParkingInfo>(ViewVehicleParkingInfo.class);
        util.exportExcel(response, list, "车位数据");
    }

    /**
     * 获取车位详细信息
     */
    @PreAuthorize("@ss.hasPermi('fvehicles:park:query')")
    @GetMapping(value = "/{associationId}")
    public AjaxResult getInfo(@PathVariable("associationId") Long associationId)
    {
//        System.out.println(viewVehicleParkingInfoService.selectViewVehicleParkingInfoByAssociationId(associationId));
        return success(viewVehicleParkingInfoService.selectViewVehicleParkingInfoByAssociationId(associationId));
    }

    /**
     * 新增车位
     */
    @PreAuthorize("@ss.hasPermi('fvehicles:park:add')")
    @Log(title = "车位", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ViewVehicleParkingInfo viewVehicleParkingInfo)
    {
        return toAjax(viewVehicleParkingInfoService.insertViewVehicleParkingInfo(viewVehicleParkingInfo));
    }

    /**
     * 修改车位
     */
    @PreAuthorize("@ss.hasPermi('fvehicles:park:edit')")
    @Log(title = "车位", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ViewVehicleParkingInfo viewVehicleParkingInfo)
    {
        return toAjax(viewVehicleParkingInfoService.updateViewVehicleParkingInfo(viewVehicleParkingInfo));
    }

    /**
     * 删除车位
     */
    @PreAuthorize("@ss.hasPermi('fvehicles:park:remove')")
    @Log(title = "车位", businessType = BusinessType.DELETE)
    @DeleteMapping("/{associationIds}")
    public AjaxResult remove(@PathVariable Long[] associationIds)
    {
        return toAjax(viewVehicleParkingInfoService.deleteViewVehicleParkingInfoByAssociationIds(associationIds));
    }

    /**
     * 查找车主信息
     */
    @PreAuthorize("@ss.hasPermi('fvehicles:park:query')")
    @GetMapping(value = "/owner/{ownerId}")
    public AjaxResult getOwnerInfo(@PathVariable("ownerId") Long ownerId)
    {
        return null;
    }
}

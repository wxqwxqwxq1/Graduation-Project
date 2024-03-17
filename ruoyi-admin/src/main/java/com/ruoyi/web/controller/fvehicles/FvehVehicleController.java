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
import com.ruoyi.fvehicles.domain.FvehVehicle;
import com.ruoyi.fvehicles.service.IFvehVehicleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 车辆Controller
 *
 * @author wxq
 * @date 2024-03-07
 */
@RestController
@RequestMapping("/fvehicles/fvehicle")
public class FvehVehicleController extends BaseController
{
    @Autowired
    private IFvehVehicleService fvehVehicleService;

    /**
     * 查询车辆列表
     */
    @PreAuthorize("@ss.hasPermi('fvehicles:fvehicle:list')")
    @GetMapping("/list")
    public TableDataInfo list(FvehVehicle fvehVehicle)
    {
        System.out.println(fvehVehicle.toString());
        startPage();
        List<FvehVehicle> list = fvehVehicleService.selectFvehVehicleList(fvehVehicle);
//        System.out.println("list: " + list);
        return getDataTable(list);
    }

    /**
     * 导出车辆列表
     */
    @PreAuthorize("@ss.hasPermi('fvehicles:fvehicle:export')")
    @Log(title = "车辆", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FvehVehicle fvehVehicle)
    {
        List<FvehVehicle> list = fvehVehicleService.selectFvehVehicleList(fvehVehicle);
        ExcelUtil<FvehVehicle> util = new ExcelUtil<FvehVehicle>(FvehVehicle.class);
        util.exportExcel(response, list, "车辆数据");
    }

    /**
     * 获取车辆详细信息
     */
    @PreAuthorize("@ss.hasPermi('fvehicles:fvehicle:query')")
    @GetMapping(value = "/{vehicleId}")
    public AjaxResult getInfo(@PathVariable("vehicleId") Long vehicleId)
    {
        return success(fvehVehicleService.selectFvehVehicleByVehicleId(vehicleId));
    }

    /**
     * 新增车辆
     */
    @PreAuthorize("@ss.hasPermi('fvehicles:fvehicle:add')")
    @Log(title = "车辆", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FvehVehicle fvehVehicle)
    {
        return toAjax(fvehVehicleService.insertFvehVehicle(fvehVehicle));
    }

    /**
     * 修改车辆
     */
    @PreAuthorize("@ss.hasPermi('fvehicles:fvehicle:edit')")
    @Log(title = "车辆", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FvehVehicle fvehVehicle)
    {
        return toAjax(fvehVehicleService.updateFvehVehicle(fvehVehicle));
    }

    /**
     * 删除车辆
     */
    @PreAuthorize("@ss.hasPermi('fvehicles:fvehicle:remove')")
    @Log(title = "车辆", businessType = BusinessType.DELETE)
    @DeleteMapping("/{vehicleIds}")
    public AjaxResult remove(@PathVariable Long[] vehicleIds)
    {
        return toAjax(fvehVehicleService.deleteFvehVehicleByVehicleIds(vehicleIds));
    }

    /**
     * 根据车主id查询车辆
     */
//    '/fvehicles/fvehicle/querySearchLicensePlateInfo/'+query,
    @GetMapping("/querySearchLicensePlateInfo/{query}")
    public TableDataInfo querySearchLicensePlateInfo(@PathVariable String query)
    {
        startPage();
        FvehVehicle fvehVehicle = new FvehVehicle();
        fvehVehicle.setOwnerId(Long.parseLong(query));
        List<FvehVehicle> list = fvehVehicleService.selectFvehVehicleList(fvehVehicle);
        return getDataTable(list);
    }
}

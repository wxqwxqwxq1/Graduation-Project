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
import com.ruoyi.fvehicles.domain.FvehGarageVehicle;
import com.ruoyi.fvehicles.service.IFvehGarageVehicleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 车库-车辆关联Controller
 *
 * @author ruoyi
 * @date 2024-03-12
 */
@RestController
@RequestMapping("/garage_fvehicle/garage_fvehicle")
public class FvehGarageVehicleController extends BaseController
{
    @Autowired
    private IFvehGarageVehicleService fvehGarageVehicleService;


    /**
     * 查询车库-车辆关联列表
     */
    @PreAuthorize("@ss.hasPermi('garage_fvehicle:garage_fvehicle:list')")
    @GetMapping("/list")
    public TableDataInfo list(FvehGarageVehicle fvehGarageVehicle)
    {
        startPage();
        List<FvehGarageVehicle> list = fvehGarageVehicleService.selectFvehGarageVehicleList(fvehGarageVehicle);
        return getDataTable(list);
    }

    /**
     * 导出车库-车辆关联列表
     */
    @PreAuthorize("@ss.hasPermi('garage_fvehicle:garage_fvehicle:export')")
    @Log(title = "车库-车辆关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FvehGarageVehicle fvehGarageVehicle)
    {
        List<FvehGarageVehicle> list = fvehGarageVehicleService.selectFvehGarageVehicleList(fvehGarageVehicle);
        ExcelUtil<FvehGarageVehicle> util = new ExcelUtil<FvehGarageVehicle>(FvehGarageVehicle.class);
        util.exportExcel(response, list, "车库-车辆关联数据");
    }

    /**
     * 获取车库-车辆关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('garage_fvehicle:garage_fvehicle:query')")
    @GetMapping(value = "/{associationId}")
    public AjaxResult getInfo(@PathVariable("associationId") Long associationId)
    {
        return success(fvehGarageVehicleService.selectFvehGarageVehicleByAssociationId(associationId));
    }

    /**
     * 新增车库-车辆关联
     */
    @PreAuthorize("@ss.hasPermi('garage_fvehicle:garage_fvehicle:add')")
    @Log(title = "车库-车辆关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FvehGarageVehicle fvehGarageVehicle)
    {
//        System.out.println(fvehGarageVehicle.toString());
        return toAjax(fvehGarageVehicleService.insertFvehGarageVehicle(fvehGarageVehicle));
    }

    /**
     * 修改车库-车辆关联
     */
    @PreAuthorize("@ss.hasPermi('garage_fvehicle:garage_fvehicle:edit')")
    @Log(title = "车库-车辆关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FvehGarageVehicle fvehGarageVehicle)
    {
        return toAjax(fvehGarageVehicleService.updateFvehGarageVehicle(fvehGarageVehicle));
    }

    /**
     * 删除车库-车辆关联
     */
    @PreAuthorize("@ss.hasPermi('garage_fvehicle:garage_fvehicle:remove')")
    @Log(title = "车库-车辆关联", businessType = BusinessType.DELETE)
    @DeleteMapping("/{associationIds}")
    public AjaxResult remove(@PathVariable Long[] associationIds)
    {
        return toAjax(fvehGarageVehicleService.deleteFvehGarageVehicleByAssociationIds(associationIds));
    }
}

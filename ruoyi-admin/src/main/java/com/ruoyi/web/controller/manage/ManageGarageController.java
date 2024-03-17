package com.ruoyi.web.controller.manage;

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
import com.ruoyi.manage.domain.ManageGarage;
import com.ruoyi.manage.service.IManageGarageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 *  车库Controller
 *
 * @author wxq
 * @date 2024-03-10
 */
@RestController
@RequestMapping("/manage/garage")
public class ManageGarageController extends BaseController
{
    @Autowired
    private IManageGarageService manageGarageService;

    /**
     * 查询 车库列表
     */
    @PreAuthorize("@ss.hasPermi('manage:garage:list')")
    @GetMapping("/list")
    public TableDataInfo list(ManageGarage manageGarage)
    {
        startPage();
        List<ManageGarage> list = manageGarageService.selectManageGarageList(manageGarage);
        return getDataTable(list);
    }

    /**
     * 导出 车库列表
     */
    @PreAuthorize("@ss.hasPermi('manage:garage:export')")
    @Log(title = " 车库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ManageGarage manageGarage)
    {
        List<ManageGarage> list = manageGarageService.selectManageGarageList(manageGarage);
        ExcelUtil<ManageGarage> util = new ExcelUtil<ManageGarage>(ManageGarage.class);
        util.exportExcel(response, list, " 车库数据");
    }

    /**
     * 获取 车库详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:garage:query')")
    @GetMapping(value = "/{garageId}")
    public AjaxResult getInfo(@PathVariable("garageId") Long garageId)
    {
        return success(manageGarageService.selectManageGarageByGarageId(garageId));
    }

    /**
     * 新增 车库
     */
    @PreAuthorize("@ss.hasPermi('manage:garage:add')")
    @Log(title = " 车库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ManageGarage manageGarage)
    {
        return toAjax(manageGarageService.insertManageGarage(manageGarage));
    }

    /**
     * 修改 车库
     */
    @PreAuthorize("@ss.hasPermi('manage:garage:edit')")
    @Log(title = " 车库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ManageGarage manageGarage)
    {
        return toAjax(manageGarageService.updateManageGarage(manageGarage));
    }

    /**
     * 删除 车库
     */
    @PreAuthorize("@ss.hasPermi('manage:garage:remove')")
    @Log(title = " 车库", businessType = BusinessType.DELETE)
    @DeleteMapping("/{garageIds}")
    public AjaxResult remove(@PathVariable Long[] garageIds)
    {
        return toAjax(manageGarageService.deleteManageGarageByGarageIds(garageIds));
    }

    /**
     * 获取车库位置信息
     */
//     '/fvehicles/garage/querySearchGarageLocation'+query
    @GetMapping("/querySearchGarageLocation/{query}")
    public AjaxResult querySearchGarageLocation(@PathVariable String query)
    {
        ManageGarage manageGarage = new ManageGarage();
//        只允许修改固定车位
        manageGarage.setType("fixed");
        manageGarage.setLocation(query);
        return success(manageGarageService.selectManageGarageList(manageGarage));
    }
}


package com.ruoyi.web.controller.fvehicles;


import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.exception.fvehicles.UserException;
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
import com.ruoyi.fvehicles.domain.FvehOwner;
import com.ruoyi.fvehicles.service.IFvehOwnerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 车主Controller
 *
 */
@RestController
@RequestMapping("/fvehicles/owner")
public class FvehOwnerController extends BaseController
{
    @Autowired
    private IFvehOwnerService fvehOwnerService;

    /**
     * 查询车主列表
     */
    @PreAuthorize("@ss.hasPermi('fvehicles:owner:list')")
    @GetMapping("/list")
    public TableDataInfo list(FvehOwner fvehOwner)
    {
//        System.out.println(fvehOwner.toString());
        fvehOwnerService.updateAllSpaces();
        startPage();
        List<FvehOwner> list = fvehOwnerService.selectFvehOwnerList(fvehOwner);
        return getDataTable(list);
    }

    /**
     * 导出车主列表
     */
    @PreAuthorize("@ss.hasPermi('fvehicles:owner:export')")
    @Log(title = "车主", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FvehOwner fvehOwner)
    {
        List<FvehOwner> list = fvehOwnerService.selectFvehOwnerList(fvehOwner);
        ExcelUtil<FvehOwner> util = new ExcelUtil<FvehOwner>(FvehOwner.class);
        util.exportExcel(response, list, "车主数据");
    }

    /**
     * 获取车主详细信息
     */
    @PreAuthorize("@ss.hasPermi('fvehicles:owner:query')")
    @GetMapping(value = "/{ownerId}")
    public AjaxResult getInfo(@PathVariable("ownerId") Long ownerId)
    {
//        更新车位信息
        fvehOwnerService.updateSpaces(ownerId);
        return success(fvehOwnerService.selectFvehOwnerByOwnerId(ownerId));
    }

    /**
     * 新增车主
     */
    @PreAuthorize("@ss.hasPermi('fvehicles:owner:add')")
    @Log(title = "车主", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FvehOwner fvehOwner)
    {
        //         判断身份证号是否存在
        if (fvehOwnerService.selectFvehOwnerByOwnerIdCard(fvehOwner.getOwnerIdCard()) != null) {
//            System.out.println("身份证号已存在。");
            // 身份证号码存在
            throw new UserException("身份证号已存在。");
        } else {
            // 身份证号码不存在
//            更新车位信息
            return toAjax(fvehOwnerService.insertFvehOwner(fvehOwner));
        }
    }

    /**
     * 修改车主
     */
    @PreAuthorize("@ss.hasPermi('fvehicles:owner:edit')")
    @Log(title = "车主", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FvehOwner fvehOwner)
    {
//        更新车位信息
//        fvehOwnerService.updateSpaces(fvehOwner.getOwnerId());
        return toAjax(fvehOwnerService.updateFvehOwner(fvehOwner));
    }

    /**
     * 删除车主
     */
    @PreAuthorize("@ss.hasPermi('fvehicles:owner:remove')")
    @Log(title = "车主", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ownerIds}")
    public AjaxResult remove(@PathVariable Long[] ownerIds)
    {
        return toAjax(fvehOwnerService.deleteFvehOwnerByOwnerIds(ownerIds));
    }

    /**
     * 查找车主信息--编号，姓名，电话，留给车位管理模块使用
     */
    @PreAuthorize("@ss.hasPermi('fvehicles:park:query')")
    @GetMapping(value = "/querySearchOwner/{query}")
    public AjaxResult getOwnerInfo(@PathVariable("query") Long ownerId)
    {
//        根据输入的编号，进行模糊查询，返回编号，姓名，电话
        return success(fvehOwnerService.selectFvehOwnerByOwnerIdCardLike(ownerId));
    }
}


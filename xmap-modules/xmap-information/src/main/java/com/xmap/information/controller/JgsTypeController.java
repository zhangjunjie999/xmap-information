package com.xmap.information.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xmap.common.log.annotation.Log;
import com.xmap.common.log.enums.BusinessType;
import com.xmap.common.security.annotation.RequiresPermissions;
import com.xmap.information.domain.JgsType;
import com.xmap.information.service.IJgsTypeService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 类型管理Controller
 * 
 * @author xmap
 * @date 2023-10-08
 */
@RestController
@RequestMapping("/type")
public class JgsTypeController extends BaseController
{
    @Autowired
    private IJgsTypeService jgsTypeService;

    /**
     * 查询类型管理列表
     */
    @RequiresPermissions("information:type:list")
    @GetMapping("/list")
    public TableDataInfo list(JgsType jgsType)
    {
        startPage();
        List<JgsType> list = jgsTypeService.selectJgsTypeList(jgsType);
        return getDataTable(list);
    }

    /**
     * 导出类型管理列表
     */
    @RequiresPermissions("information:type:export")
    @Log(title = "类型管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JgsType jgsType)
    {
        List<JgsType> list = jgsTypeService.selectJgsTypeList(jgsType);
        ExcelUtil<JgsType> util = new ExcelUtil<JgsType>(JgsType.class);
        util.exportExcel(response, list, "类型管理数据");
    }

    /**
     * 获取类型管理详细信息
     */
    @RequiresPermissions("information:type:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jgsTypeService.selectJgsTypeById(id));
    }

    /**
     * 新增类型管理
     */
    @RequiresPermissions("information:type:add")
    @Log(title = "类型管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JgsType jgsType)
    {
        return toAjax(jgsTypeService.insertJgsType(jgsType));
    }

    /**
     * 修改类型管理
     */
    @RequiresPermissions("information:type:edit")
    @Log(title = "类型管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JgsType jgsType)
    {
        return toAjax(jgsTypeService.updateJgsType(jgsType));
    }

    /**
     * 删除类型管理
     */
    @RequiresPermissions("information:type:remove")
    @Log(title = "类型管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jgsTypeService.deleteJgsTypeByIds(ids));
    }
}

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
import com.xmap.information.domain.JgsInformine;
import com.xmap.information.service.IJgsInformineService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 资讯管理Controller
 * 
 * @author xmap
 * @date 2023-10-08
 */
@RestController
@RequestMapping("/informine")
public class JgsInformineController extends BaseController
{
    @Autowired
    private IJgsInformineService jgsInformineService;

    /**
     * 查询资讯管理列表
     */
    @RequiresPermissions("information:informine:list")
    @GetMapping("/list")
    public TableDataInfo list(JgsInformine jgsInformine)
    {
        startPage();
        List<JgsInformine> list = jgsInformineService.selectJgsInformineList(jgsInformine);
        return getDataTable(list);
    }

    /**
     * 导出资讯管理列表
     */
    @RequiresPermissions("information:informine:export")
    @Log(title = "资讯管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JgsInformine jgsInformine)
    {
        List<JgsInformine> list = jgsInformineService.selectJgsInformineList(jgsInformine);
        ExcelUtil<JgsInformine> util = new ExcelUtil<JgsInformine>(JgsInformine.class);
        util.exportExcel(response, list, "资讯管理数据");
    }

    /**
     * 获取资讯管理详细信息
     */
    @RequiresPermissions("information:informine:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jgsInformineService.selectJgsInformineById(id));
    }

    /**
     * 新增资讯管理
     */
    @RequiresPermissions("information:informine:add")
    @Log(title = "资讯管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JgsInformine jgsInformine)
    {
        return toAjax(jgsInformineService.insertJgsInformine(jgsInformine));
    }

    /**
     * 修改资讯管理
     */
    @RequiresPermissions("information:informine:edit")
    @Log(title = "资讯管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JgsInformine jgsInformine)
    {
        return toAjax(jgsInformineService.updateJgsInformine(jgsInformine));
    }

    /**
     * 删除资讯管理
     */
    @RequiresPermissions("information:informine:remove")
    @Log(title = "资讯管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jgsInformineService.deleteJgsInformineByIds(ids));
    }
}

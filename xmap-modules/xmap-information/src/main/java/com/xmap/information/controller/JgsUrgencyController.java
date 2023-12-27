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
import com.xmap.information.domain.JgsUrgency;
import com.xmap.information.service.IJgsUrgencyService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 紧急插播管理Controller
 * 
 * @author xmap
 * @date 2023-07-31
 */
@RestController
@RequestMapping("/urgency")
public class JgsUrgencyController extends BaseController
{
    @Autowired
    private IJgsUrgencyService jgsUrgencyService;

    /**
     * 查询紧急插播管理列表
     */
    @RequiresPermissions("information:urgency:list")
    @GetMapping("/list")
    public TableDataInfo list(JgsUrgency jgsUrgency)
    {
        startPage();
        List<JgsUrgency> list = jgsUrgencyService.selectJgsUrgencyList(jgsUrgency);
        return getDataTable(list);
    }

    /**
     * 导出紧急插播管理列表
     */
    @RequiresPermissions("information:urgency:export")
    @Log(title = "紧急插播管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JgsUrgency jgsUrgency)
    {
        List<JgsUrgency> list = jgsUrgencyService.selectJgsUrgencyList(jgsUrgency);
        ExcelUtil<JgsUrgency> util = new ExcelUtil<JgsUrgency>(JgsUrgency.class);
        util.exportExcel(response, list, "紧急插播管理数据");
    }

    /**
     * 获取紧急插播管理详细信息
     */
    @RequiresPermissions("information:urgency:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jgsUrgencyService.selectJgsUrgencyById(id));
    }

    /**
     * 新增紧急插播管理
     */
    @RequiresPermissions("information:urgency:add")
    @Log(title = "紧急插播管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JgsUrgency jgsUrgency)
    {
        return toAjax(jgsUrgencyService.insertJgsUrgency(jgsUrgency));
    }

    /**
     * 修改紧急插播管理
     */
    @RequiresPermissions("information:urgency:edit")
    @Log(title = "紧急插播管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JgsUrgency jgsUrgency)
    {
        return toAjax(jgsUrgencyService.updateJgsUrgency(jgsUrgency));
    }

    /**
     * 删除紧急插播管理
     */
    @RequiresPermissions("information:urgency:remove")
    @Log(title = "紧急插播管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jgsUrgencyService.deleteJgsUrgencyByIds(ids));
    }
}

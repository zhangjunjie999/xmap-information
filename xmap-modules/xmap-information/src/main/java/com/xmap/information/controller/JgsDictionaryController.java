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
import com.xmap.information.domain.JgsDictionary;
import com.xmap.information.service.IJgsDictionaryService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 字典管理Controller
 * 
 * @author xmap
 * @date 2023-08-15
 */
@RestController
@RequestMapping("/dictionary")
public class JgsDictionaryController extends BaseController
{
    @Autowired
    private IJgsDictionaryService jgsDictionaryService;

    /**
     * 查询字典管理列表
     */
    @RequiresPermissions("information:dictionary:list")
    @GetMapping("/list")
    public TableDataInfo list(JgsDictionary jgsDictionary)
    {
        startPage();
        List<JgsDictionary> list = jgsDictionaryService.selectJgsDictionaryList(jgsDictionary);
        return getDataTable(list);
    }

    /**
     * 导出字典管理列表
     */
    @RequiresPermissions("information:dictionary:export")
    @Log(title = "字典管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JgsDictionary jgsDictionary)
    {
        List<JgsDictionary> list = jgsDictionaryService.selectJgsDictionaryList(jgsDictionary);
        ExcelUtil<JgsDictionary> util = new ExcelUtil<JgsDictionary>(JgsDictionary.class);
        util.exportExcel(response, list, "字典管理数据");
    }

    /**
     * 获取字典管理详细信息
     */
    @RequiresPermissions("information:dictionary:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jgsDictionaryService.selectJgsDictionaryById(id));
    }

    /**
     * 新增字典管理
     */
    @RequiresPermissions("information:dictionary:add")
    @Log(title = "字典管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JgsDictionary jgsDictionary)
    {
        return toAjax(jgsDictionaryService.insertJgsDictionary(jgsDictionary));
    }

    /**
     * 修改字典管理
     */
    @RequiresPermissions("information:dictionary:edit")
    @Log(title = "字典管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JgsDictionary jgsDictionary)
    {
        return toAjax(jgsDictionaryService.updateJgsDictionary(jgsDictionary));
    }

    /**
     * 删除字典管理
     */
    @RequiresPermissions("information:dictionary:remove")
    @Log(title = "字典管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jgsDictionaryService.deleteJgsDictionaryByIds(ids));
    }
}

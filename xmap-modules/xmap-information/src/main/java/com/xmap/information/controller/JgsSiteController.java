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
import com.xmap.information.domain.JgsSite;
import com.xmap.information.service.IJgsSiteService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 地点标志管理Controller
 *
 * @author xmap
 * @date 2023-11-01
 */
@RestController
@RequestMapping("/site")
public class JgsSiteController extends BaseController
{
    @Autowired
    private IJgsSiteService jgsSiteService;

    /**
     * 查询地点标志管理列表
     */
    @RequiresPermissions("information:site:list")
    @GetMapping("/list")
    public TableDataInfo list(JgsSite jgsSite)
    {
//        startPage();
        List<JgsSite> list = jgsSiteService.selectJgsSiteList(jgsSite);
        return getDataTable(list);
    }

    /**
     * 导出地点标志管理列表
     */
    @RequiresPermissions("information:site:export")
    @Log(title = "地点标志管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JgsSite jgsSite)
    {
        List<JgsSite> list = jgsSiteService.selectJgsSiteList(jgsSite);
        ExcelUtil<JgsSite> util = new ExcelUtil<JgsSite>(JgsSite.class);
        util.exportExcel(response, list, "地点标志管理数据");
    }

    /**
     * 获取地点标志管理详细信息
     */
    @RequiresPermissions("information:site:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jgsSiteService.selectJgsSiteById(id));
    }

    /**
     * 新增地点标志管理
     */
    @RequiresPermissions("information:site:add")
    @Log(title = "地点标志管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JgsSite jgsSite)
    {
        return toAjax(jgsSiteService.insertJgsSite(jgsSite));
    }

    /**
     * 修改地点标志管理
     */
    @RequiresPermissions("information:site:edit")
    @Log(title = "地点标志管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JgsSite jgsSite)
    {
        return toAjax(jgsSiteService.updateJgsSite(jgsSite));
    }

    /**
     * 删除地点标志管理
     */
    @RequiresPermissions("information:site:remove")
    @Log(title = "地点标志管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jgsSiteService.deleteJgsSiteByIds(ids));
    }
}

package com.xmap.information.controller;

import java.util.List;
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
import com.xmap.information.domain.JgsScenic1;
import com.xmap.information.service.IJgsScenic1Service;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 景点引导牌Controller
 *
 * @author xmap
 * @date 2023-11-23
 */
@RestController
@RequestMapping("/scenic1")
public class JgsScenic1Controller extends BaseController
{
    @Autowired
    private IJgsScenic1Service jgsScenic1Service;

    /**
     * 查询景点引导牌列表
     */
    @RequiresPermissions("information:scenic1:list")
    @GetMapping("/list")
    public TableDataInfo list(JgsScenic1 jgsScenic1)
    {
        startPage();
        List<JgsScenic1> list = jgsScenic1Service.selectJgsScenic1List(jgsScenic1);
        return getDataTable(list);
    }

    /**
     * 导出景点引导牌列表
     */
    @RequiresPermissions("information:scenic1:export")
    @Log(title = "景点引导牌", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JgsScenic1 jgsScenic1)
    {
        List<JgsScenic1> list = jgsScenic1Service.selectJgsScenic1List(jgsScenic1);
        ExcelUtil<JgsScenic1> util = new ExcelUtil<JgsScenic1>(JgsScenic1.class);
        util.exportExcel(response, list, "景点引导牌数据");
    }

    /**
     * 获取景点引导牌详细信息
     */
    @RequiresPermissions("information:scenic1:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jgsScenic1Service.selectJgsScenic1ById(id));
    }

    /**
     * 新增景点引导牌
     */
    @RequiresPermissions("information:scenic1:add")
    @Log(title = "景点引导牌", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JgsScenic1 jgsScenic1)
    {
        return toAjax(jgsScenic1Service.insertJgsScenic1(jgsScenic1));
    }

    /**
     * 修改景点引导牌
     */
    @RequiresPermissions("information:scenic1:edit")
    @Log(title = "景点引导牌", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JgsScenic1 jgsScenic1)
    {
        return toAjax(jgsScenic1Service.updateJgsScenic1(jgsScenic1));
    }

    /**
     * 删除景点引导牌
     */
    @RequiresPermissions("information:scenic1:remove")
    @Log(title = "景点引导牌", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jgsScenic1Service.deleteJgsScenic1ByIds(ids));
    }
}

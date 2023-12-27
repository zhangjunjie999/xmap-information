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
import com.xmap.information.domain.JgsView;
import com.xmap.information.service.IJgsViewService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 景点引导牌Controller
 *
 * @author xmap
 * @date 2023-11-22
 */
@RestController
@RequestMapping("/view")
public class JgsViewController extends BaseController
{
    @Autowired
    private IJgsViewService jgsViewService;

    /**
     * 查询景点引导牌列表
     */
    @RequiresPermissions("information:view:list")
    @GetMapping("/list")
    public TableDataInfo list(JgsView jgsView)
    {
        startPage();
        List<JgsView> list = jgsViewService.selectJgsViewList(jgsView);
        return getDataTable(list);
    }

    /**
     * 导出景点引导牌列表
     */
    @RequiresPermissions("information:view:export")
    @Log(title = "景点引导牌", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JgsView jgsView)
    {
        List<JgsView> list = jgsViewService.selectJgsViewList(jgsView);
        ExcelUtil<JgsView> util = new ExcelUtil<JgsView>(JgsView.class);
        util.exportExcel(response, list, "景点引导牌数据");
    }

    /**
     * 获取景点引导牌详细信息
     */
    @RequiresPermissions("information:view:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jgsViewService.selectJgsViewById(id));
    }

    /**
     * 新增景点引导牌
     */
    @RequiresPermissions("information:view:add")
    @Log(title = "景点引导牌", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JgsView jgsView)
    {
        return toAjax(jgsViewService.insertJgsView(jgsView));
    }

    /**
     * 修改景点引导牌
     */
    @RequiresPermissions("information:view:edit")
    @Log(title = "景点引导牌", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JgsView jgsView)
    {
        return toAjax(jgsViewService.updateJgsView(jgsView));
    }

    /**
     * 删除景点引导牌
     */
    @RequiresPermissions("information:view:remove")
    @Log(title = "景点引导牌", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jgsViewService.deleteJgsViewByIds(ids));
    }
}

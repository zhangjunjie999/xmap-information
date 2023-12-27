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
import com.xmap.information.domain.JgsScreen;
import com.xmap.information.service.IJgsScreenService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 大屏管理Controller
 * 
 * @author xmap
 * @date 2023-10-09
 */
@RestController
@RequestMapping("/screen")
public class JgsScreenController extends BaseController
{
    @Autowired
    private IJgsScreenService jgsScreenService;

    /**
     * 查询大屏管理列表
     */
    @RequiresPermissions("information:screen:list")
    @GetMapping("/list")
    public TableDataInfo list(JgsScreen jgsScreen)
    {
        startPage();
        List<JgsScreen> list = jgsScreenService.selectJgsScreenList(jgsScreen);
        return getDataTable(list);
    }

    /**
     * 导出大屏管理列表
     */
    @RequiresPermissions("information:screen:export")
    @Log(title = "大屏管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JgsScreen jgsScreen)
    {
        List<JgsScreen> list = jgsScreenService.selectJgsScreenList(jgsScreen);
        ExcelUtil<JgsScreen> util = new ExcelUtil<JgsScreen>(JgsScreen.class);
        util.exportExcel(response, list, "大屏管理数据");
    }

    /**
     * 获取大屏管理详细信息
     */
    @RequiresPermissions("information:screen:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jgsScreenService.selectJgsScreenById(id));
    }

    /**
     * 新增大屏管理
     */
    @RequiresPermissions("information:screen:add")
    @Log(title = "大屏管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JgsScreen jgsScreen)
    {
        return toAjax(jgsScreenService.insertJgsScreen(jgsScreen));
    }

    /**
     * 修改大屏管理
     */
    @RequiresPermissions("information:screen:edit")
    @Log(title = "大屏管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JgsScreen jgsScreen)
    {
        return toAjax(jgsScreenService.updateJgsScreen(jgsScreen));
    }

    /**
     * 删除大屏管理
     */
    @RequiresPermissions("information:screen:remove")
    @Log(title = "大屏管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jgsScreenService.deleteJgsScreenByIds(ids));
    }
}

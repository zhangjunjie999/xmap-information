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
import com.xmap.information.domain.InfoWork;
import com.xmap.information.service.IInfoWorkService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 工作动态列Controller
 * 
 * @author xmap
 * @date 2023-07-03
 */
@RestController
@RequestMapping("/work")
public class InfoWorkController extends BaseController
{
    @Autowired
    private IInfoWorkService infoWorkService;

    /**
     * 查询工作动态列列表
     */
    @RequiresPermissions("information:work:list")
    @GetMapping("/list")
    public TableDataInfo list(InfoWork infoWork)
    {
        startPage();
        List<InfoWork> list = infoWorkService.selectInfoWorkList(infoWork);
        return getDataTable(list);
    }

    /**
     * 导出工作动态列列表
     */
    @RequiresPermissions("information:work:export")
    @Log(title = "工作动态列", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InfoWork infoWork)
    {
        List<InfoWork> list = infoWorkService.selectInfoWorkList(infoWork);
        ExcelUtil<InfoWork> util = new ExcelUtil<InfoWork>(InfoWork.class);
        util.exportExcel(response, list, "工作动态列数据");
    }

    /**
     * 获取工作动态列详细信息
     */
    @RequiresPermissions("information:work:query")
    @GetMapping(value = "/{dynamicId}")
    public AjaxResult getInfo(@PathVariable("dynamicId") Long dynamicId)
    {
        return success(infoWorkService.selectInfoWorkByDynamicId(dynamicId));
    }

    /**
     * 新增工作动态列
     */
    @RequiresPermissions("information:work:add")
    @Log(title = "工作动态列", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InfoWork infoWork)
    {
        return toAjax(infoWorkService.insertInfoWork(infoWork));
    }

    /**
     * 修改工作动态列
     */
    @RequiresPermissions("information:work:edit")
    @Log(title = "工作动态列", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InfoWork infoWork)
    {
        return toAjax(infoWorkService.updateInfoWork(infoWork));
    }

    /**
     * 删除工作动态列
     */
    @RequiresPermissions("information:work:remove")
    @Log(title = "工作动态列", businessType = BusinessType.DELETE)
	@DeleteMapping("/{dynamicIds}")
    public AjaxResult remove(@PathVariable Long[] dynamicIds)
    {
        return toAjax(infoWorkService.deleteInfoWorkByDynamicIds(dynamicIds));
    }
}

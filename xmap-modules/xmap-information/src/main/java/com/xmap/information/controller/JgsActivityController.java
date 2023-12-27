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
import com.xmap.information.domain.JgsActivity;
import com.xmap.information.service.IJgsActivityService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 活动盛事管理Controller
 * 
 * @author xmap
 * @date 2023-07-28
 */
@RestController
@RequestMapping("/activity")
public class JgsActivityController extends BaseController
{
    @Autowired
    private IJgsActivityService jgsActivityService;

    /**
     * 查询活动盛事管理列表
     */
    @RequiresPermissions("information:activity:list")
    @GetMapping("/list")
    public TableDataInfo list(JgsActivity jgsActivity)
    {
        startPage();
        List<JgsActivity> list = jgsActivityService.selectJgsActivityList(jgsActivity);
        return getDataTable(list);
    }

    /**
     * 导出活动盛事管理列表
     */
    @RequiresPermissions("information:activity:export")
    @Log(title = "活动盛事管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JgsActivity jgsActivity)
    {
        List<JgsActivity> list = jgsActivityService.selectJgsActivityList(jgsActivity);
        ExcelUtil<JgsActivity> util = new ExcelUtil<JgsActivity>(JgsActivity.class);
        util.exportExcel(response, list, "活动盛事管理数据");
    }

    /**
     * 获取活动盛事管理详细信息
     */
    @RequiresPermissions("information:activity:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jgsActivityService.selectJgsActivityById(id));
    }

    /**
     * 新增活动盛事管理
     */
    @RequiresPermissions("information:activity:add")
    @Log(title = "活动盛事管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JgsActivity jgsActivity)
    {
        return toAjax(jgsActivityService.insertJgsActivity(jgsActivity));
    }

    /**
     * 修改活动盛事管理
     */
    @RequiresPermissions("information:activity:edit")
    @Log(title = "活动盛事管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JgsActivity jgsActivity)
    {
        return toAjax(jgsActivityService.updateJgsActivity(jgsActivity));
    }

    /**
     * 删除活动盛事管理
     */
    @RequiresPermissions("information:activity:remove")
    @Log(title = "活动盛事管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jgsActivityService.deleteJgsActivityByIds(ids));
    }
}

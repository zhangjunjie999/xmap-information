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
import com.xmap.information.domain.JgsAdvertise;
import com.xmap.information.service.IJgsAdvertiseService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 广告管理Controller
 * 
 * @author xmap
 * @date 2023-11-15
 */
@RestController
@RequestMapping("/advertise")
public class JgsAdvertiseController extends BaseController
{
    @Autowired
    private IJgsAdvertiseService jgsAdvertiseService;

    /**
     * 查询广告管理列表
     */
    @RequiresPermissions("information:advertise:list")
    @GetMapping("/list")
    public TableDataInfo list(JgsAdvertise jgsAdvertise)
    {
        startPage();
        List<JgsAdvertise> list = jgsAdvertiseService.selectJgsAdvertiseList(jgsAdvertise);
        return getDataTable(list);
    }

    /**
     * 导出广告管理列表
     */
    @RequiresPermissions("information:advertise:export")
    @Log(title = "广告管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JgsAdvertise jgsAdvertise)
    {
        List<JgsAdvertise> list = jgsAdvertiseService.selectJgsAdvertiseList(jgsAdvertise);
        ExcelUtil<JgsAdvertise> util = new ExcelUtil<JgsAdvertise>(JgsAdvertise.class);
        util.exportExcel(response, list, "广告管理数据");
    }

    /**
     * 获取广告管理详细信息
     */
    @RequiresPermissions("information:advertise:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jgsAdvertiseService.selectJgsAdvertiseById(id));
    }

    /**
     * 新增广告管理
     */
    @RequiresPermissions("information:advertise:add")
    @Log(title = "广告管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JgsAdvertise jgsAdvertise)
    {
        return toAjax(jgsAdvertiseService.insertJgsAdvertise(jgsAdvertise));
    }

    /**
     * 修改广告管理
     */
    @RequiresPermissions("information:advertise:edit")
    @Log(title = "广告管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JgsAdvertise jgsAdvertise)
    {
        return toAjax(jgsAdvertiseService.updateJgsAdvertise(jgsAdvertise));
    }

    /**
     * 删除广告管理
     */
    @RequiresPermissions("information:advertise:remove")
    @Log(title = "广告管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jgsAdvertiseService.deleteJgsAdvertiseByIds(ids));
    }
}

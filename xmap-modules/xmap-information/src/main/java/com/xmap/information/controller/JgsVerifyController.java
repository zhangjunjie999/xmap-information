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
import com.xmap.information.domain.JgsVerify;
import com.xmap.information.service.IJgsVerifyService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 审核管理列Controller
 * 
 * @author xmap
 * @date 2023-11-07
 */
@RestController
@RequestMapping("/verify")
public class JgsVerifyController extends BaseController
{
    @Autowired
    private IJgsVerifyService jgsVerifyService;

    /**
     * 查询审核管理列列表
     */
    @RequiresPermissions("information:verify:list")
    @GetMapping("/list")
    public TableDataInfo list(JgsVerify jgsVerify)
    {
        startPage();
        List<JgsVerify> list = jgsVerifyService.selectJgsVerifyList(jgsVerify);
        return getDataTable(list);
    }

    /**
     * 导出审核管理列列表
     */
    @RequiresPermissions("information:verify:export")
    @Log(title = "审核管理列", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JgsVerify jgsVerify)
    {
        List<JgsVerify> list = jgsVerifyService.selectJgsVerifyList(jgsVerify);
        ExcelUtil<JgsVerify> util = new ExcelUtil<JgsVerify>(JgsVerify.class);
        util.exportExcel(response, list, "审核管理列数据");
    }

    /**
     * 获取审核管理列详细信息
     */
    @RequiresPermissions("information:verify:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jgsVerifyService.selectJgsVerifyById(id));
    }

    /**
     * 新增审核管理列
     */
    @RequiresPermissions("information:verify:add")
    @Log(title = "审核管理列", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JgsVerify jgsVerify)
    {
        return toAjax(jgsVerifyService.insertJgsVerify(jgsVerify));
    }

    /**
     * 修改审核管理列
     */
    @RequiresPermissions("information:verify:edit")
    @Log(title = "审核管理列", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JgsVerify jgsVerify)
    {
        return toAjax(jgsVerifyService.updateJgsVerify(jgsVerify));
    }

    /**
     * 删除审核管理列
     */
    @RequiresPermissions("information:verify:remove")
    @Log(title = "审核管理列", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jgsVerifyService.deleteJgsVerifyByIds(ids));
    }
}

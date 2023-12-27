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
import com.xmap.information.domain.JgsRole;
import com.xmap.information.service.IJgsRoleService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 角色管理Controller
 * 
 * @author xmap
 * @date 2023-08-15
 */
@RestController
@RequestMapping("/role")
public class JgsRoleController extends BaseController
{
    @Autowired
    private IJgsRoleService jgsRoleService;

    /**
     * 查询角色管理列表
     */
    @RequiresPermissions("information:role:list")
    @GetMapping("/list")
    public TableDataInfo list(JgsRole jgsRole)
    {
        startPage();
        List<JgsRole> list = jgsRoleService.selectJgsRoleList(jgsRole);
        return getDataTable(list);
    }

    /**
     * 导出角色管理列表
     */
    @RequiresPermissions("information:role:export")
    @Log(title = "角色管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JgsRole jgsRole)
    {
        List<JgsRole> list = jgsRoleService.selectJgsRoleList(jgsRole);
        ExcelUtil<JgsRole> util = new ExcelUtil<JgsRole>(JgsRole.class);
        util.exportExcel(response, list, "角色管理数据");
    }

    /**
     * 获取角色管理详细信息
     */
    @RequiresPermissions("information:role:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jgsRoleService.selectJgsRoleById(id));
    }

    /**
     * 新增角色管理
     */
    @RequiresPermissions("information:role:add")
    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JgsRole jgsRole)
    {
        return toAjax(jgsRoleService.insertJgsRole(jgsRole));
    }

    /**
     * 修改角色管理
     */
    @RequiresPermissions("information:role:edit")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JgsRole jgsRole)
    {
        return toAjax(jgsRoleService.updateJgsRole(jgsRole));
    }

    /**
     * 删除角色管理
     */
    @RequiresPermissions("information:role:remove")
    @Log(title = "角色管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jgsRoleService.deleteJgsRoleByIds(ids));
    }
}

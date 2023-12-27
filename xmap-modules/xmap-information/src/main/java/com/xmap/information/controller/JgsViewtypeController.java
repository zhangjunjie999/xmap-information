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
import com.xmap.information.domain.JgsViewtype;
import com.xmap.information.service.IJgsViewtypeService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 景点类型管理Controller
 * 
 * @author xmap
 * @date 2023-11-01
 */
@RestController
@RequestMapping("/views")
public class JgsViewtypeController extends BaseController
{
    @Autowired
    private IJgsViewtypeService jgsViewtypeService;

    /**
     * 查询景点类型管理列表
     */
    @RequiresPermissions("information:views:list")
    @GetMapping("/list")
    public TableDataInfo list(JgsViewtype jgsViewtype)
    {
        startPage();
        List<JgsViewtype> list = jgsViewtypeService.selectJgsViewtypeList(jgsViewtype);
        return getDataTable(list);
    }

    /**
     * 导出景点类型管理列表
     */
    @RequiresPermissions("information:views:export")
    @Log(title = "景点类型管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JgsViewtype jgsViewtype)
    {
        List<JgsViewtype> list = jgsViewtypeService.selectJgsViewtypeList(jgsViewtype);
        ExcelUtil<JgsViewtype> util = new ExcelUtil<JgsViewtype>(JgsViewtype.class);
        util.exportExcel(response, list, "景点类型管理数据");
    }

    /**
     * 获取景点类型管理详细信息
     */
    @RequiresPermissions("information:views:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jgsViewtypeService.selectJgsViewtypeById(id));
    }

    /**
     * 新增景点类型管理
     */
    @RequiresPermissions("information:views:add")
    @Log(title = "景点类型管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JgsViewtype jgsViewtype)
    {
        return toAjax(jgsViewtypeService.insertJgsViewtype(jgsViewtype));
    }

    /**
     * 修改景点类型管理
     */
    @RequiresPermissions("information:views:edit")
    @Log(title = "景点类型管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JgsViewtype jgsViewtype)
    {
        return toAjax(jgsViewtypeService.updateJgsViewtype(jgsViewtype));
    }

    /**
     * 删除景点类型管理
     */
    @RequiresPermissions("information:views:remove")
    @Log(title = "景点类型管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jgsViewtypeService.deleteJgsViewtypeByIds(ids));
    }
}

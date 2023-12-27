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
import com.xmap.information.domain.JgsSpecial;
import com.xmap.information.service.IJgsSpecialService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 特色产品管理Controller
 * 
 * @author xmap
 * @date 2023-07-28
 */
@RestController
@RequestMapping("/special")
public class JgsSpecialController extends BaseController
{
    @Autowired
    private IJgsSpecialService jgsSpecialService;

    /**
     * 查询特色产品管理列表
     */
    @RequiresPermissions("information:special:list")
    @GetMapping("/list")
    public TableDataInfo list(JgsSpecial jgsSpecial)
    {
        startPage();
        List<JgsSpecial> list = jgsSpecialService.selectJgsSpecialList(jgsSpecial);
        return getDataTable(list);
    }

    /**
     * 导出特色产品管理列表
     */
    @RequiresPermissions("information:special:export")
    @Log(title = "特色产品管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JgsSpecial jgsSpecial)
    {
        List<JgsSpecial> list = jgsSpecialService.selectJgsSpecialList(jgsSpecial);
        ExcelUtil<JgsSpecial> util = new ExcelUtil<JgsSpecial>(JgsSpecial.class);
        util.exportExcel(response, list, "特色产品管理数据");
    }

    /**
     * 获取特色产品管理详细信息
     */
    @RequiresPermissions("information:special:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jgsSpecialService.selectJgsSpecialById(id));
    }

    /**
     * 新增特色产品管理
     */
    @RequiresPermissions("information:special:add")
    @Log(title = "特色产品管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JgsSpecial jgsSpecial)
    {
        return toAjax(jgsSpecialService.insertJgsSpecial(jgsSpecial));
    }

    /**
     * 修改特色产品管理
     */
    @RequiresPermissions("information:special:edit")
    @Log(title = "特色产品管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JgsSpecial jgsSpecial)
    {
        return toAjax(jgsSpecialService.updateJgsSpecial(jgsSpecial));
    }

    /**
     * 删除特色产品管理
     */
    @RequiresPermissions("information:special:remove")
    @Log(title = "特色产品管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jgsSpecialService.deleteJgsSpecialByIds(ids));
    }
}

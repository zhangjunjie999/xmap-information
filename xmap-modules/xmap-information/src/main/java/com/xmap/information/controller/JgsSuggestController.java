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
import com.xmap.information.domain.JgsSuggest;
import com.xmap.information.service.IJgsSuggestService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 游客建议Controller
 * 
 * @author xmap
 * @date 2023-07-28
 */
@RestController
@RequestMapping("/suggest")
public class JgsSuggestController extends BaseController
{
    @Autowired
    private IJgsSuggestService jgsSuggestService;

    /**
     * 查询游客建议列表
     */
    @RequiresPermissions("information:suggest:list")
    @GetMapping("/list")
    public TableDataInfo list(JgsSuggest jgsSuggest)
    {
        startPage();
        List<JgsSuggest> list = jgsSuggestService.selectJgsSuggestList(jgsSuggest);
        return getDataTable(list);
    }

    /**
     * 导出游客建议列表
     */
    @RequiresPermissions("information:suggest:export")
    @Log(title = "游客建议", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JgsSuggest jgsSuggest)
    {
        List<JgsSuggest> list = jgsSuggestService.selectJgsSuggestList(jgsSuggest);
        ExcelUtil<JgsSuggest> util = new ExcelUtil<JgsSuggest>(JgsSuggest.class);
        util.exportExcel(response, list, "游客建议数据");
    }

    /**
     * 获取游客建议详细信息
     */
    @RequiresPermissions("information:suggest:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jgsSuggestService.selectJgsSuggestById(id));
    }

    /**
     * 新增游客建议
     */
    @RequiresPermissions("information:suggest:add")
    @Log(title = "游客建议", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JgsSuggest jgsSuggest)
    {
        return toAjax(jgsSuggestService.insertJgsSuggest(jgsSuggest));
    }

    /**
     * 修改游客建议
     */
    @RequiresPermissions("information:suggest:edit")
    @Log(title = "游客建议", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JgsSuggest jgsSuggest)
    {
        return toAjax(jgsSuggestService.updateJgsSuggest(jgsSuggest));
    }

    /**
     * 删除游客建议
     */
    @RequiresPermissions("information:suggest:remove")
    @Log(title = "游客建议", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jgsSuggestService.deleteJgsSuggestByIds(ids));
    }
}

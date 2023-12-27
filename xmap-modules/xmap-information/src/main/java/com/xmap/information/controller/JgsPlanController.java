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
import com.xmap.information.domain.JgsPlan;
import com.xmap.information.service.IJgsPlanService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 旅游攻略表Controller
 * 
 * @author xmap
 * @date 2023-07-28
 */
@RestController
@RequestMapping("/plan")
public class JgsPlanController extends BaseController
{
    @Autowired
    private IJgsPlanService jgsPlanService;

    /**
     * 查询旅游攻略表列表
     */
    @RequiresPermissions("information:plan:list")
    @GetMapping("/list")
    public TableDataInfo list(JgsPlan jgsPlan)
    {
        startPage();
        List<JgsPlan> list = jgsPlanService.selectJgsPlanList(jgsPlan);
        return getDataTable(list);
    }

    /**
     * 导出旅游攻略表列表
     */
    @RequiresPermissions("information:plan:export")
    @Log(title = "旅游攻略表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JgsPlan jgsPlan)
    {
        List<JgsPlan> list = jgsPlanService.selectJgsPlanList(jgsPlan);
        ExcelUtil<JgsPlan> util = new ExcelUtil<JgsPlan>(JgsPlan.class);
        util.exportExcel(response, list, "旅游攻略表数据");
    }

    /**
     * 获取旅游攻略表详细信息
     */
    @RequiresPermissions("information:plan:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jgsPlanService.selectJgsPlanById(id));
    }

    /**
     * 新增旅游攻略表
     */
    @RequiresPermissions("information:plan:add")
    @Log(title = "旅游攻略表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JgsPlan jgsPlan)
    {
        return toAjax(jgsPlanService.insertJgsPlan(jgsPlan));
    }

    /**
     * 修改旅游攻略表
     */
    @RequiresPermissions("information:plan:edit")
    @Log(title = "旅游攻略表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JgsPlan jgsPlan)
    {
        return toAjax(jgsPlanService.updateJgsPlan(jgsPlan));
    }

    /**
     * 删除旅游攻略表
     */
    @RequiresPermissions("information:plan:remove")
    @Log(title = "旅游攻略表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jgsPlanService.deleteJgsPlanByIds(ids));
    }
}

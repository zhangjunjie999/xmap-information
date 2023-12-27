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
import com.xmap.information.domain.JgsAnaly;
import com.xmap.information.service.IJgsAnalyService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 统计分析Controller
 * 
 * @author xmap
 * @date 2023-10-09
 */
@RestController
@RequestMapping("/analy")
public class JgsAnalyController extends BaseController
{
    @Autowired
    private IJgsAnalyService jgsAnalyService;

    /**
     * 查询统计分析列表
     */
    @RequiresPermissions("information:analy:list")
    @GetMapping("/list")
    public TableDataInfo list(JgsAnaly jgsAnaly)
    {
        startPage();
        List<JgsAnaly> list = jgsAnalyService.selectJgsAnalyList(jgsAnaly);
        return getDataTable(list);
    }

    /**
     * 导出统计分析列表
     */
    @RequiresPermissions("information:analy:export")
    @Log(title = "统计分析", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JgsAnaly jgsAnaly)
    {
        List<JgsAnaly> list = jgsAnalyService.selectJgsAnalyList(jgsAnaly);
        ExcelUtil<JgsAnaly> util = new ExcelUtil<JgsAnaly>(JgsAnaly.class);
        util.exportExcel(response, list, "统计分析数据");
    }

    /**
     * 获取统计分析详细信息
     */
    @RequiresPermissions("information:analy:query")
    @GetMapping(value = "/{statisticsId}")
    public AjaxResult getInfo(@PathVariable("statisticsId") Long statisticsId)
    {
        return success(jgsAnalyService.selectJgsAnalyByStatisticsId(statisticsId));
    }

    /**
     * 新增统计分析
     */
    @RequiresPermissions("information:analy:add")
    @Log(title = "统计分析", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JgsAnaly jgsAnaly)
    {
        return toAjax(jgsAnalyService.insertJgsAnaly(jgsAnaly));
    }

    /**
     * 修改统计分析
     */
    @RequiresPermissions("information:analy:edit")
    @Log(title = "统计分析", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JgsAnaly jgsAnaly)
    {
        return toAjax(jgsAnalyService.updateJgsAnaly(jgsAnaly));
    }

    /**
     * 删除统计分析
     */
    @RequiresPermissions("information:analy:remove")
    @Log(title = "统计分析", businessType = BusinessType.DELETE)
	@DeleteMapping("/{statisticsIds}")
    public AjaxResult remove(@PathVariable Long[] statisticsIds)
    {
        return toAjax(jgsAnalyService.deleteJgsAnalyByStatisticsIds(statisticsIds));
    }
}

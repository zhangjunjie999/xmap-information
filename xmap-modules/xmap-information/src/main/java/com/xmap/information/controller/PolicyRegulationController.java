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
import com.xmap.information.domain.PolicyRegulation;
import com.xmap.information.service.IPolicyRegulationService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 政策法规列表Controller
 * 
 * @author xmap
 * @date 2023-07-12
 */
@RestController
@RequestMapping("/policy")
public class PolicyRegulationController extends BaseController
{
    @Autowired
    private IPolicyRegulationService policyRegulationService;

    /**
     * 查询政策法规列表列表
     */
    @RequiresPermissions("information:policy:list")
    @GetMapping("/list")
    public TableDataInfo list(PolicyRegulation policyRegulation)
    {
        startPage();
        List<PolicyRegulation> list = policyRegulationService.selectPolicyRegulationList(policyRegulation);
        return getDataTable(list);
    }

    /**
     * 导出政策法规列表列表
     */
    @RequiresPermissions("information:policy:export")
    @Log(title = "政策法规列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PolicyRegulation policyRegulation)
    {
        List<PolicyRegulation> list = policyRegulationService.selectPolicyRegulationList(policyRegulation);
        ExcelUtil<PolicyRegulation> util = new ExcelUtil<PolicyRegulation>(PolicyRegulation.class);
        util.exportExcel(response, list, "政策法规列表数据");
    }

    /**
     * 获取政策法规列表详细信息
     */
    @RequiresPermissions("information:policy:query")
    @GetMapping(value = "/{policesId}")
    public AjaxResult getInfo(@PathVariable("policesId") Long policesId)
    {
        return success(policyRegulationService.selectPolicyRegulationByPolicesId(policesId));
    }

    /**
     * 新增政策法规列表
     */
    @RequiresPermissions("information:policy:add")
    @Log(title = "政策法规列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PolicyRegulation policyRegulation)
    {
        return toAjax(policyRegulationService.insertPolicyRegulation(policyRegulation));
    }

    /**
     * 修改政策法规列表
     */
    @RequiresPermissions("information:policy:edit")
    @Log(title = "政策法规列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PolicyRegulation policyRegulation)
    {
        return toAjax(policyRegulationService.updatePolicyRegulation(policyRegulation));
    }

    /**
     * 删除政策法规列表
     */
    @RequiresPermissions("information:policy:remove")
    @Log(title = "政策法规列表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{policesIds}")
    public AjaxResult remove(@PathVariable Long[] policesIds)
    {
        return toAjax(policyRegulationService.deletePolicyRegulationByPolicesIds(policesIds));
    }
}

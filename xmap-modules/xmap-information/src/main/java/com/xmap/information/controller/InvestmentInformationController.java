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
import com.xmap.information.domain.InvestmentInformation;
import com.xmap.information.service.IInvestmentInformationService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 招商信息列Controller
 * 
 * @author xmap
 * @date 2023-07-04
 */
@RestController
@RequestMapping("/invitation")
public class InvestmentInformationController extends BaseController
{
    @Autowired
    private IInvestmentInformationService investmentInformationService;

    /**
     * 查询招商信息列列表
     */
    @RequiresPermissions("information:invitation:list")
    @GetMapping("/list")
    public TableDataInfo list(InvestmentInformation investmentInformation)
    {
        startPage();
        List<InvestmentInformation> list = investmentInformationService.selectInvestmentInformationList(investmentInformation);
        return getDataTable(list);
    }

    /**
     * 导出招商信息列列表
     */
    @RequiresPermissions("information:invitation:export")
    @Log(title = "招商信息列", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InvestmentInformation investmentInformation)
    {
        List<InvestmentInformation> list = investmentInformationService.selectInvestmentInformationList(investmentInformation);
        ExcelUtil<InvestmentInformation> util = new ExcelUtil<InvestmentInformation>(InvestmentInformation.class);
        util.exportExcel(response, list, "招商信息列数据");
    }

    /**
     * 获取招商信息列详细信息
     */
    @RequiresPermissions("information:invitation:query")
    @GetMapping(value = "/{investmentId}")
    public AjaxResult getInfo(@PathVariable("investmentId") Long investmentId)
    {
        return success(investmentInformationService.selectInvestmentInformationByInvestmentId(investmentId));
    }

    /**
     * 新增招商信息列
     */
    @RequiresPermissions("information:invitation:add")
    @Log(title = "招商信息列", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InvestmentInformation investmentInformation)
    {
        return toAjax(investmentInformationService.insertInvestmentInformation(investmentInformation));
    }

    /**
     * 修改招商信息列
     */
    @RequiresPermissions("information:invitation:edit")
    @Log(title = "招商信息列", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InvestmentInformation investmentInformation)
    {
        return toAjax(investmentInformationService.updateInvestmentInformation(investmentInformation));
    }

    /**
     * 删除招商信息列
     */
    @RequiresPermissions("information:invitation:remove")
    @Log(title = "招商信息列", businessType = BusinessType.DELETE)
	@DeleteMapping("/{investmentIds}")
    public AjaxResult remove(@PathVariable Long[] investmentIds)
    {
        return toAjax(investmentInformationService.deleteInvestmentInformationByInvestmentIds(investmentIds));
    }
}

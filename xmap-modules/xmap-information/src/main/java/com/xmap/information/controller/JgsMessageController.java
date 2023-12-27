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
import com.xmap.information.domain.JgsMessage;
import com.xmap.information.service.IJgsMessageService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 井冈山基本信息管理Controller
 * 
 * @author xmap
 * @date 2023-07-28
 */
@RestController
@RequestMapping("/message")
public class JgsMessageController extends BaseController
{
    @Autowired
    private IJgsMessageService jgsMessageService;

    /**
     * 查询井冈山基本信息管理列表
     */
    @RequiresPermissions("information:message:list")
    @GetMapping("/list")
    public TableDataInfo list(JgsMessage jgsMessage)
    {
        startPage();
        List<JgsMessage> list = jgsMessageService.selectJgsMessageList(jgsMessage);
        return getDataTable(list);
    }

    /**
     * 导出井冈山基本信息管理列表
     */
    @RequiresPermissions("information:message:export")
    @Log(title = "井冈山基本信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JgsMessage jgsMessage)
    {
        List<JgsMessage> list = jgsMessageService.selectJgsMessageList(jgsMessage);
        ExcelUtil<JgsMessage> util = new ExcelUtil<JgsMessage>(JgsMessage.class);
        util.exportExcel(response, list, "井冈山基本信息管理数据");
    }

    /**
     * 获取井冈山基本信息管理详细信息
     */
    @RequiresPermissions("information:message:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jgsMessageService.selectJgsMessageById(id));
    }

    /**
     * 新增井冈山基本信息管理
     */
    @RequiresPermissions("information:message:add")
    @Log(title = "井冈山基本信息管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JgsMessage jgsMessage)
    {
        return toAjax(jgsMessageService.insertJgsMessage(jgsMessage));
    }

    /**
     * 修改井冈山基本信息管理
     */
    @RequiresPermissions("information:message:edit")
    @Log(title = "井冈山基本信息管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JgsMessage jgsMessage)
    {
        return toAjax(jgsMessageService.updateJgsMessage(jgsMessage));
    }

    /**
     * 删除井冈山基本信息管理
     */
    @RequiresPermissions("information:message:remove")
    @Log(title = "井冈山基本信息管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jgsMessageService.deleteJgsMessageByIds(ids));
    }
}

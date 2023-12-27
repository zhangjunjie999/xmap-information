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
import com.xmap.information.domain.JgsWechat;
import com.xmap.information.service.IJgsWechatService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 微信管理Controller
 * 
 * @author xmap
 * @date 2023-10-08
 */
@RestController
@RequestMapping("/wechat")
public class JgsWechatController extends BaseController
{
    @Autowired
    private IJgsWechatService jgsWechatService;

    /**
     * 查询微信管理列表
     */
    @RequiresPermissions("information:wechat:list")
    @GetMapping("/list")
    public TableDataInfo list(JgsWechat jgsWechat)
    {
        startPage();
        List<JgsWechat> list = jgsWechatService.selectJgsWechatList(jgsWechat);
        return getDataTable(list);
    }

    /**
     * 导出微信管理列表
     */
    @RequiresPermissions("information:wechat:export")
    @Log(title = "微信管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JgsWechat jgsWechat)
    {
        List<JgsWechat> list = jgsWechatService.selectJgsWechatList(jgsWechat);
        ExcelUtil<JgsWechat> util = new ExcelUtil<JgsWechat>(JgsWechat.class);
        util.exportExcel(response, list, "微信管理数据");
    }

    /**
     * 获取微信管理详细信息
     */
    @RequiresPermissions("information:wechat:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jgsWechatService.selectJgsWechatById(id));
    }

    /**
     * 新增微信管理
     */
    @RequiresPermissions("information:wechat:add")
    @Log(title = "微信管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JgsWechat jgsWechat)
    {
        return toAjax(jgsWechatService.insertJgsWechat(jgsWechat));
    }

    /**
     * 修改微信管理
     */
    @RequiresPermissions("information:wechat:edit")
    @Log(title = "微信管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JgsWechat jgsWechat)
    {
        return toAjax(jgsWechatService.updateJgsWechat(jgsWechat));
    }

    /**
     * 删除微信管理
     */
    @RequiresPermissions("information:wechat:remove")
    @Log(title = "微信管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jgsWechatService.deleteJgsWechatByIds(ids));
    }
}

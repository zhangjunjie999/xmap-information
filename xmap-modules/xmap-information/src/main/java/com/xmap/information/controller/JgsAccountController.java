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
import com.xmap.information.domain.JgsAccount;
import com.xmap.information.service.IJgsAccountService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 账户管理Controller
 * 
 * @author xmap
 * @date 2023-08-15
 */
@RestController
@RequestMapping("/account")
public class JgsAccountController extends BaseController
{
    @Autowired
    private IJgsAccountService jgsAccountService;

    /**
     * 查询账户管理列表
     */
    @RequiresPermissions("information:account:list")
    @GetMapping("/list")
    public TableDataInfo list(JgsAccount jgsAccount)
    {
        startPage();
        List<JgsAccount> list = jgsAccountService.selectJgsAccountList(jgsAccount);
        return getDataTable(list);
    }

    /**
     * 导出账户管理列表
     */
    @RequiresPermissions("information:account:export")
    @Log(title = "账户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JgsAccount jgsAccount)
    {
        List<JgsAccount> list = jgsAccountService.selectJgsAccountList(jgsAccount);
        ExcelUtil<JgsAccount> util = new ExcelUtil<JgsAccount>(JgsAccount.class);
        util.exportExcel(response, list, "账户管理数据");
    }

    /**
     * 获取账户管理详细信息
     */
    @RequiresPermissions("information:account:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jgsAccountService.selectJgsAccountById(id));
    }

    /**
     * 新增账户管理
     */
    @RequiresPermissions("information:account:add")
    @Log(title = "账户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JgsAccount jgsAccount)
    {
        return toAjax(jgsAccountService.insertJgsAccount(jgsAccount));
    }

    /**
     * 修改账户管理
     */
    @RequiresPermissions("information:account:edit")
    @Log(title = "账户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JgsAccount jgsAccount)
    {
        return toAjax(jgsAccountService.updateJgsAccount(jgsAccount));
    }

    /**
     * 删除账户管理
     */
    @RequiresPermissions("information:account:remove")
    @Log(title = "账户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jgsAccountService.deleteJgsAccountByIds(ids));
    }
}

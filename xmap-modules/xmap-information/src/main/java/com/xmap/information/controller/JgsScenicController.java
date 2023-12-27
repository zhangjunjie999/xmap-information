package com.xmap.information.controller;

import java.util.List;
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
import com.xmap.information.domain.JgsScenicList;
import com.xmap.information.service.IJgsScenicListService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 景点引导牌Controller
 *
 * @author xmap
 * @date 2023-11-22
 */
@RestController
@RequestMapping("/scenic")
public class JgsScenicController extends BaseController
{
    @Autowired
    private IJgsScenicListService jgsScenicListService;

    /**
     * 查询景点引导牌列表
     */
    @RequiresPermissions("information:scenic:list")
    @GetMapping("/list")
    public TableDataInfo list(JgsScenicList jgsScenicList)
    {
        startPage();
        List<JgsScenicList> list = jgsScenicListService.selectJgsScenicListList(jgsScenicList);
        return getDataTable(list);
    }

    /**
     * 导出景点引导牌列表
     */
    @RequiresPermissions("information:scenic:export")
    @Log(title = "景点引导牌", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JgsScenicList jgsScenicList)
    {
        List<JgsScenicList> list = jgsScenicListService.selectJgsScenicListList(jgsScenicList);
        ExcelUtil<JgsScenicList> util = new ExcelUtil<JgsScenicList>(JgsScenicList.class);
        util.exportExcel(response, list, "景点引导牌数据");
    }

    /**
     * 获取景点引导牌详细信息
     */
    @RequiresPermissions("information:scenic:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jgsScenicListService.selectJgsScenicListById(id));
    }

    /**
     * 新增景点引导牌
     */
    @RequiresPermissions("information:scenic:add")
    @Log(title = "景点引导牌", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JgsScenicList jgsScenicList)
    {
        return toAjax(jgsScenicListService.insertJgsScenicList(jgsScenicList));
    }

    /**
     * 修改景点引导牌
     */
    @RequiresPermissions("information:scenic:edit")
    @Log(title = "景点引导牌", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JgsScenicList jgsScenicList)
    {
        return toAjax(jgsScenicListService.updateJgsScenicList(jgsScenicList));
    }

    /**
     * 删除景点引导牌
     */
    @RequiresPermissions("information:scenic:remove")
    @Log(title = "景点引导牌", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jgsScenicListService.deleteJgsScenicListByIds(ids));
    }
}

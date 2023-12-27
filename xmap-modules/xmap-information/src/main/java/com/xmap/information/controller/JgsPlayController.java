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
import com.xmap.information.domain.JgsPlay;
import com.xmap.information.service.IJgsPlayService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 玩转井冈山Controller
 * 
 * @author xmap
 * @date 2023-07-28
 */
@RestController
@RequestMapping("/play")
public class JgsPlayController extends BaseController
{
    @Autowired
    private IJgsPlayService jgsPlayService;

    /**
     * 查询玩转井冈山列表
     */
    @RequiresPermissions("information:play:list")
    @GetMapping("/list")
    public TableDataInfo list(JgsPlay jgsPlay)
    {
        startPage();
        List<JgsPlay> list = jgsPlayService.selectJgsPlayList(jgsPlay);
        return getDataTable(list);
    }

    /**
     * 导出玩转井冈山列表
     */
    @RequiresPermissions("information:play:export")
    @Log(title = "玩转井冈山", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JgsPlay jgsPlay)
    {
        List<JgsPlay> list = jgsPlayService.selectJgsPlayList(jgsPlay);
        ExcelUtil<JgsPlay> util = new ExcelUtil<JgsPlay>(JgsPlay.class);
        util.exportExcel(response, list, "玩转井冈山数据");
    }

    /**
     * 获取玩转井冈山详细信息
     */
    @RequiresPermissions("information:play:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jgsPlayService.selectJgsPlayById(id));
    }

    /**
     * 新增玩转井冈山
     */
    @RequiresPermissions("information:play:add")
    @Log(title = "玩转井冈山", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JgsPlay jgsPlay)
    {
        return toAjax(jgsPlayService.insertJgsPlay(jgsPlay));
    }

    /**
     * 修改玩转井冈山
     */
    @RequiresPermissions("information:play:edit")
    @Log(title = "玩转井冈山", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JgsPlay jgsPlay)
    {
        return toAjax(jgsPlayService.updateJgsPlay(jgsPlay));
    }

    /**
     * 删除玩转井冈山
     */
    @RequiresPermissions("information:play:remove")
    @Log(title = "玩转井冈山", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jgsPlayService.deleteJgsPlayByIds(ids));
    }
}

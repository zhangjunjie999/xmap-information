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
import com.xmap.information.domain.JgsBoard;
import com.xmap.information.service.IJgsBoardService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 公告信息管理Controller
 * 
 * @author xmap
 * @date 2023-08-01
 */
@RestController
@RequestMapping("/board")
public class JgsBoardController extends BaseController
{
    @Autowired
    private IJgsBoardService jgsBoardService;

    /**
     * 查询公告信息管理列表
     */
    @RequiresPermissions("information:board:list")
    @GetMapping("/list")
    public TableDataInfo list(JgsBoard jgsBoard)
    {
        startPage();
        List<JgsBoard> list = jgsBoardService.selectJgsBoardList(jgsBoard);
        return getDataTable(list);
    }

    /**
     * 导出公告信息管理列表
     */
    @RequiresPermissions("information:board:export")
    @Log(title = "公告信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JgsBoard jgsBoard)
    {
        List<JgsBoard> list = jgsBoardService.selectJgsBoardList(jgsBoard);
        ExcelUtil<JgsBoard> util = new ExcelUtil<JgsBoard>(JgsBoard.class);
        util.exportExcel(response, list, "公告信息管理数据");
    }

    /**
     * 获取公告信息管理详细信息
     */
    @RequiresPermissions("information:board:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jgsBoardService.selectJgsBoardById(id));
    }

    /**
     * 新增公告信息管理
     */
    @RequiresPermissions("information:board:add")
    @Log(title = "公告信息管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JgsBoard jgsBoard)
    {
        return toAjax(jgsBoardService.insertJgsBoard(jgsBoard));
    }

    /**
     * 修改公告信息管理
     */
    @RequiresPermissions("information:board:edit")
    @Log(title = "公告信息管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JgsBoard jgsBoard)
    {
        return toAjax(jgsBoardService.updateJgsBoard(jgsBoard));
    }

    /**
     * 删除公告信息管理
     */
    @RequiresPermissions("information:board:remove")
    @Log(title = "公告信息管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jgsBoardService.deleteJgsBoardByIds(ids));
    }
}

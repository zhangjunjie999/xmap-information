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
import com.xmap.information.domain.FileRead;
import com.xmap.information.service.IFileReadService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 文件解读列Controller
 * 
 * @author xmap
 * @date 2023-07-04
 */
@RestController
@RequestMapping("/file")
public class FileReadController extends BaseController
{
    @Autowired
    private IFileReadService fileReadService;

    /**
     * 查询文件解读列列表
     */
    @RequiresPermissions("information:file:list")
    @GetMapping("/list")
    public TableDataInfo list(FileRead fileRead)
    {
        startPage();
        List<FileRead> list = fileReadService.selectFileReadList(fileRead);
        return getDataTable(list);
    }

    /**
     * 导出文件解读列列表
     */
    @RequiresPermissions("information:file:export")
    @Log(title = "文件解读列", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FileRead fileRead)
    {
        List<FileRead> list = fileReadService.selectFileReadList(fileRead);
        ExcelUtil<FileRead> util = new ExcelUtil<FileRead>(FileRead.class);
        util.exportExcel(response, list, "文件解读列数据");
    }

    /**
     * 获取文件解读列详细信息
     */
    @RequiresPermissions("information:file:query")
    @GetMapping(value = "/{docId}")
    public AjaxResult getInfo(@PathVariable("docId") Long docId)
    {
        return success(fileReadService.selectFileReadByDocId(docId));
    }

    /**
     * 新增文件解读列
     */
    @RequiresPermissions("information:file:add")
    @Log(title = "文件解读列", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FileRead fileRead)
    {
        return toAjax(fileReadService.insertFileRead(fileRead));
    }

    /**
     * 修改文件解读列
     */
    @RequiresPermissions("information:file:edit")
    @Log(title = "文件解读列", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FileRead fileRead)
    {
        return toAjax(fileReadService.updateFileRead(fileRead));
    }

    /**
     * 删除文件解读列
     */
    @RequiresPermissions("information:file:remove")
    @Log(title = "文件解读列", businessType = BusinessType.DELETE)
	@DeleteMapping("/{docIds}")
    public AjaxResult remove(@PathVariable Long[] docIds)
    {
        return toAjax(fileReadService.deleteFileReadByDocIds(docIds));
    }
}

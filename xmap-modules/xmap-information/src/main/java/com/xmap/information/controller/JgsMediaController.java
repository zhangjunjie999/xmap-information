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
import com.xmap.information.domain.JgsMedia;
import com.xmap.information.service.IJgsMediaService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 多媒体管理Controller
 * 
 * @author xmap
 * @date 2023-07-31
 */
@RestController
@RequestMapping("/video")
public class JgsMediaController extends BaseController
{
    @Autowired
    private IJgsMediaService jgsMediaService;

    /**
     * 查询多媒体管理列表
     */
    @RequiresPermissions("information:video:list")
    @GetMapping("/list")
    public TableDataInfo list(JgsMedia jgsMedia)
    {
        startPage();
        List<JgsMedia> list = jgsMediaService.selectJgsMediaList(jgsMedia);
        return getDataTable(list);
    }

    /**
     * 导出多媒体管理列表
     */
    @RequiresPermissions("information:video:export")
    @Log(title = "多媒体管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JgsMedia jgsMedia)
    {
        List<JgsMedia> list = jgsMediaService.selectJgsMediaList(jgsMedia);
        ExcelUtil<JgsMedia> util = new ExcelUtil<JgsMedia>(JgsMedia.class);
        util.exportExcel(response, list, "多媒体管理数据");
    }

    /**
     * 获取多媒体管理详细信息
     */
    @RequiresPermissions("information:video:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jgsMediaService.selectJgsMediaById(id));
    }

    /**
     * 新增多媒体管理
     */
    @RequiresPermissions("information:video:add")
    @Log(title = "多媒体管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JgsMedia jgsMedia)
    {
        return toAjax(jgsMediaService.insertJgsMedia(jgsMedia));
    }

    /**
     * 修改多媒体管理
     */
    @RequiresPermissions("information:video:edit")
    @Log(title = "多媒体管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JgsMedia jgsMedia)
    {
        return toAjax(jgsMediaService.updateJgsMedia(jgsMedia));
    }

    /**
     * 删除多媒体管理
     */
    @RequiresPermissions("information:video:remove")
    @Log(title = "多媒体管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jgsMediaService.deleteJgsMediaByIds(ids));
    }
}

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
import com.xmap.information.domain.JgsImage;
import com.xmap.information.service.IJgsImageService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 图片管理Controller
 * 
 * @author xmap
 * @date 2023-07-28
 */
@RestController
@RequestMapping("/image")
public class JgsImageController extends BaseController
{
    @Autowired
    private IJgsImageService jgsImageService;

    /**
     * 查询图片管理列表
     */
    @RequiresPermissions("information:image:list")
    @GetMapping("/list")
    public TableDataInfo list(JgsImage jgsImage)
    {
        startPage();
        List<JgsImage> list = jgsImageService.selectJgsImageList(jgsImage);
        return getDataTable(list);
    }

    /**
     * 导出图片管理列表
     */
    @RequiresPermissions("information:image:export")
    @Log(title = "图片管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JgsImage jgsImage)
    {
        List<JgsImage> list = jgsImageService.selectJgsImageList(jgsImage);
        ExcelUtil<JgsImage> util = new ExcelUtil<JgsImage>(JgsImage.class);
        util.exportExcel(response, list, "图片管理数据");
    }

    /**
     * 获取图片管理详细信息
     */
    @RequiresPermissions("information:image:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jgsImageService.selectJgsImageById(id));
    }

    /**
     * 新增图片管理
     */
    @RequiresPermissions("information:image:add")
    @Log(title = "图片管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JgsImage jgsImage)
    {
        return toAjax(jgsImageService.insertJgsImage(jgsImage));
    }

    /**
     * 修改图片管理
     */
    @RequiresPermissions("information:image:edit")
    @Log(title = "图片管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JgsImage jgsImage)
    {
        return toAjax(jgsImageService.updateJgsImage(jgsImage));
    }

    /**
     * 删除图片管理
     */
    @RequiresPermissions("information:image:remove")
    @Log(title = "图片管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jgsImageService.deleteJgsImageByIds(ids));
    }
}

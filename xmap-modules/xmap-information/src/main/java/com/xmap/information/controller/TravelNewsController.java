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
import com.xmap.information.domain.TravelNews;
import com.xmap.information.service.ITravelNewsService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 旅游新闻列表Controller
 * 
 * @author xmap
 * @date 2023-07-04
 */
@RestController
@RequestMapping("/news")
public class TravelNewsController extends BaseController
{
    @Autowired
    private ITravelNewsService travelNewsService;

    /**
     * 查询旅游新闻列表列表
     */
    @RequiresPermissions("information:news:list")
    @GetMapping("/list")
    public TableDataInfo list(TravelNews travelNews)
    {
        startPage();
        List<TravelNews> list = travelNewsService.selectTravelNewsList(travelNews);
        return getDataTable(list);
    }

    /**
     * 导出旅游新闻列表列表
     */
    @RequiresPermissions("information:news:export")
    @Log(title = "旅游新闻列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TravelNews travelNews)
    {
        List<TravelNews> list = travelNewsService.selectTravelNewsList(travelNews);
        ExcelUtil<TravelNews> util = new ExcelUtil<TravelNews>(TravelNews.class);
        util.exportExcel(response, list, "旅游新闻列表数据");
    }

    /**
     * 获取旅游新闻列表详细信息
     */
    @RequiresPermissions("information:news:query")
    @GetMapping(value = "/{newsId}")
    public AjaxResult getInfo(@PathVariable("newsId") Long newsId)
    {
        return success(travelNewsService.selectTravelNewsByNewsId(newsId));
    }

    /**
     * 新增旅游新闻列表
     */
    @RequiresPermissions("information:news:add")
    @Log(title = "旅游新闻列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TravelNews travelNews)
    {
        return toAjax(travelNewsService.insertTravelNews(travelNews));
    }

    /**
     * 修改旅游新闻列表
     */
    @RequiresPermissions("information:news:edit")
    @Log(title = "旅游新闻列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TravelNews travelNews)
    {
        return toAjax(travelNewsService.updateTravelNews(travelNews));
    }

    /**
     * 删除旅游新闻列表
     */
    @RequiresPermissions("information:news:remove")
    @Log(title = "旅游新闻列表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{newsIds}")
    public AjaxResult remove(@PathVariable Long[] newsIds)
    {
        return toAjax(travelNewsService.deleteTravelNewsByNewsIds(newsIds));
    }
}

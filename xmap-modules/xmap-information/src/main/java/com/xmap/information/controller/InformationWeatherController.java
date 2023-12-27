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
import com.xmap.information.domain.InformationWeather;
import com.xmap.information.service.IInformationWeatherService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 天气Controller
 * 
 * @author xmap
 * @date 2023-11-17
 */
@RestController
@RequestMapping("/weather")
public class InformationWeatherController extends BaseController
{
    @Autowired
    private IInformationWeatherService informationWeatherService;

    /**
     * 查询天气列表
     */
    @RequiresPermissions("information:weather:list")
    @GetMapping("/list")
    public TableDataInfo list(InformationWeather informationWeather)
    {
        startPage();
        List<InformationWeather> list = informationWeatherService.selectInformationWeatherList(informationWeather);
        return getDataTable(list);
    }

    /**
     * 导出天气列表
     */
    @RequiresPermissions("information:weather:export")
    @Log(title = "天气", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InformationWeather informationWeather)
    {
        List<InformationWeather> list = informationWeatherService.selectInformationWeatherList(informationWeather);
        ExcelUtil<InformationWeather> util = new ExcelUtil<InformationWeather>(InformationWeather.class);
        util.exportExcel(response, list, "天气数据");
    }

    /**
     * 获取天气详细信息
     */
    @RequiresPermissions("information:weather:query")
    @GetMapping(value = "/{weatherId}")
    public AjaxResult getInfo(@PathVariable("weatherId") Long weatherId)
    {
        return success(informationWeatherService.selectInformationWeatherByWeatherId(weatherId));
    }

    /**
     * 新增天气
     */
    @RequiresPermissions("information:weather:add")
    @Log(title = "天气", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InformationWeather informationWeather)
    {
        return toAjax(informationWeatherService.insertInformationWeather(informationWeather));
    }

    /**
     * 修改天气
     */
    @RequiresPermissions("information:weather:edit")
    @Log(title = "天气", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InformationWeather informationWeather)
    {
        return toAjax(informationWeatherService.updateInformationWeather(informationWeather));
    }

    /**
     * 删除天气
     */
    @RequiresPermissions("information:weather:remove")
    @Log(title = "天气", businessType = BusinessType.DELETE)
	@DeleteMapping("/{weatherIds}")
    public AjaxResult remove(@PathVariable Long[] weatherIds)
    {
        return toAjax(informationWeatherService.deleteInformationWeatherByWeatherIds(weatherIds));
    }
}

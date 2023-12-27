package com.xmap.information.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.xmap.common.core.utils.DateUtils;
import com.xmap.information.domain.InformationWeather;
import com.xmap.information.domain.ParkWeather;
import com.xmap.information.service.IInformationWeatherService;
import com.xmap.information.service.impl.ParkWeatherServiceImpl;
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
import com.xmap.information.domain.JgsParkGuide;
import com.xmap.information.service.IJgsParkGuideService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 停车场引导牌管理Controller
 *
 * @author xmap
 * @date 2023-11-03
 */
@RestController
@RequestMapping("/parking")
public class JgsParkGuideController extends BaseController
{
    @Autowired
    private IJgsParkGuideService jgsParkGuideService;
    @Autowired
    private ParkWeatherServiceImpl weatherService;
    @Autowired
    private IInformationWeatherService informationWeatherService;

    /**
     * 调用第三方接口获取天气信息
     */
//    @RequiresPermissions("information:parking:list")
    @GetMapping("/weather")
    public List<ParkWeather> weather(JgsParkGuide jgsParkGuide) throws IOException {
        startPage();
        long x =1;
        InformationWeather informationWeather = new InformationWeather();
        //设置要修改的那条数据的ID
        informationWeather.setWeatherId(x);
        List<ParkWeather> weathers = weatherService.getWeather("101240608");
        //将查询得到的数据存入数据库
//        天气
        informationWeather.setWeatherInfo(weathers.get(0).getType());
//        低温
        informationWeather.setWeatherLow(weathers.get(0).getLow());
//        高温
        informationWeather.setWeatherHigh(weathers.get(0).getHigh());
//        查询时间
        informationWeather.setWeatherTime(DateUtils.getNowDate());
        informationWeatherService.updateInformationWeather(informationWeather);
        return weathers;
    }

    /**
     * 查询停车场引导牌管理列表
     */
    @RequiresPermissions("information:parking:list")
    @GetMapping("/list")
    public TableDataInfo list(JgsParkGuide jgsParkGuide)
    {
        startPage();
        List<JgsParkGuide> list = jgsParkGuideService.selectJgsParkGuideList(jgsParkGuide);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        String strDate1 = sdf1.format(DateUtils.getNowDate());
        list.forEach(JgsParkGuide -> JgsParkGuide.setWeekDay(dateToWeek(strDate1)));
        return getDataTable(list);
    }
    /**
     * 根据日期获取 星期 （2019-05-06 ——> 星期一）
     * @param datetime
     * @return
     */
    public static String dateToWeek(String datetime) {

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        Date date;
        try {
            date = f.parse(datetime);
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //一周的第几天
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        int year = cal.get(Calendar.YEAR);    //获取年
        int month = cal.get(Calendar.MONTH) + 1;   //获取月份，0表示1月份
        String month_str = "" ;
        if (month <10 ) {
            month_str = "0" +month;
        }else {
            month_str = "" +month;
        }
        int day = cal.get(Calendar.DAY_OF_MONTH);    //获取当前天数
        String day_str = "" ;
        if (day <10 ) {
            day_str = "0" +day;
        }else {
            day_str = "" +day;
        }
        int time = cal.get(Calendar.HOUR_OF_DAY);  //获取当前小时
        String time_str = "" ;
        if (time <10 ) {
            time_str = "0" +time;
        }else {
            time_str = "" +time;
        }
        int min = cal.get(Calendar.MINUTE);//获取当前分钟
        String min_str = "" ;
        if (min <10 ) {
            min_str = "0" +min;
        }else {
            min_str = "" +min;
        }
        int xx = cal.get(Calendar.SECOND);          //获取当前秒
//        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String curDate = s.format(cal.getTime());  //当前日期
        //        System.out.println("当前时间："+year + "-" + month + "-"+ day + " "+time + ":" + min +":" + xx);
        String str =    year + " 年 " + month_str + " 月 "+ day_str  + " 日  "+ weekDays[w] + " " +time_str + ":" + min_str ;
        return str;
//        return weekDays[w];
    }
    /**
     * 导出停车场引导牌管理列表
     */
    @RequiresPermissions("information:parking:export")
    @Log(title = "停车场引导牌管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JgsParkGuide jgsParkGuide)
    {
        List<JgsParkGuide> list = jgsParkGuideService.selectJgsParkGuideList(jgsParkGuide);
        ExcelUtil<JgsParkGuide> util = new ExcelUtil<JgsParkGuide>(JgsParkGuide.class);
        util.exportExcel(response, list, "停车场引导牌管理数据");
    }

    /**
     * 获取停车场引导牌管理详细信息
     */
    @RequiresPermissions("information:parking:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jgsParkGuideService.selectJgsParkGuideById(id));
    }

    /**
     * 新增停车场引导牌管理
     */
    @RequiresPermissions("information:parking:add")
    @Log(title = "停车场引导牌管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JgsParkGuide jgsParkGuide)
    {
        return toAjax(jgsParkGuideService.insertJgsParkGuide(jgsParkGuide));
    }

    /**
     * 修改停车场引导牌管理
     */
    @RequiresPermissions("information:parking:edit")
    @Log(title = "停车场引导牌管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JgsParkGuide jgsParkGuide)
    {
        return toAjax(jgsParkGuideService.updateJgsParkGuide(jgsParkGuide));
    }

    /**
     * 删除停车场引导牌管理
     */
    @RequiresPermissions("information:parking:remove")
    @Log(title = "停车场引导牌管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jgsParkGuideService.deleteJgsParkGuideByIds(ids));
    }

}

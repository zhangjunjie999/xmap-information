package com.xmap.screen.controller;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson2.JSON;

import com.alibaba.fastjson.JSONObject;
//import com.alibaba.nacos.shaded.com.google.gson.Gson;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.alibaba.fastjson2.filter.SimplePropertyPreFilter;
import com.xmap.common.core.utils.DateUtils;

import com.xmap.screen.domain.RefreshMaterialContent;

import com.xmap.screen.service.impl.ScreenServiceImpl;
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
import com.xmap.screen.domain.JgsParkGuide;
import com.xmap.screen.service.IJgsParkGuideService;
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
    private ScreenServiceImpl screenService;


    /**
     * 查询停车场引导牌管理列表
     */
//    @RequiresPermissions("information:parking:list")
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
    @PostMapping()
    public AjaxResult add(@RequestBody JgsParkGuide jgsParkGuide)
    {
        return toAjax(jgsParkGuideService.insertJgsParkGuide(jgsParkGuide));
    }
    /**
     * 新增引导牌节目资源
     */
//    @RequiresPermissions("information:parking:add")
    @Log(title = "停车场引导牌节目资源", businessType = BusinessType.INSERT)
    @PostMapping("/screen")
    public String postScreen(@RequestBody RefreshMaterialContent refreshMaterialContent) throws IOException {
//        URL url ='/xpublish-web/service/rs/v1/operationService/refreshMaterialContent';
         final String apiUrl = "http://localhost:5000/xpublish-web/service/rs/v1/operationService/refreshMaterialContent";

        String jsonStr = JSON.toJSONString(refreshMaterialContent);

        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add("params"); // 排除字段名为params的字段

        System.out.println("JSON.toJSONString(refreshMaterialContent, filter): "+JSON.toJSONString(refreshMaterialContent, filter));

        return (screenService.syncPost(apiUrl,JSON.toJSONString(refreshMaterialContent, filter)));
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

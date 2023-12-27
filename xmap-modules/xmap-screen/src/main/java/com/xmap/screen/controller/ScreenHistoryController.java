package com.xmap.screen.controller;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.filter.SimplePropertyPreFilter;
import com.xmap.screen.domain.Material;
import com.xmap.screen.domain.RefreshMaterialContent;
import com.xmap.screen.domain.Resource;
import com.xmap.screen.service.impl.ScreenServiceHttpImpl;
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
import com.xmap.screen.domain.ScreenHistory;
import com.xmap.screen.service.IScreenHistoryService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 设备对接历史Controller
 * 
 * @author xmap
 * @date 2023-12-05
 */
@RestController
@RequestMapping("/history")
public class ScreenHistoryController extends BaseController
{
    @Autowired
    private IScreenHistoryService screenHistoryService;
    @Autowired
    private ScreenServiceImpl screenService;

    /**
     * 查询设备对接历史列表
     */
    @RequiresPermissions("screen:history:list")
    @GetMapping("/list")
    public TableDataInfo list(ScreenHistory screenHistory)
    {
        startPage();
        List<ScreenHistory> list = screenHistoryService.selectScreenHistoryList(screenHistory);
        return getDataTable(list);
    }

    /**
     * 导出设备对接历史列表
     */
    @RequiresPermissions("screen:history:export")
    @Log(title = "设备对接历史", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ScreenHistory screenHistory)
    {
        List<ScreenHistory> list = screenHistoryService.selectScreenHistoryList(screenHistory);
        ExcelUtil<ScreenHistory> util = new ExcelUtil<ScreenHistory>(ScreenHistory.class);
        util.exportExcel(response, list, "设备对接历史数据");
    }

    /**
     * 获取设备对接历史详细信息
     */
    @RequiresPermissions("screen:history:query")
    @GetMapping(value = "/{historyId}")
    public AjaxResult getInfo(@PathVariable("historyId") Long historyId)
    {
        return success(screenHistoryService.selectScreenHistoryByHistoryId(historyId));
    }

    /**
     * part1 新增设备对接历史
     * @param ScreenHistory  请求参数
     * @return AjaxResult 所代表资源的响应结果

     */
    @RequiresPermissions("screen:history:add")
    @Log(title = "设备对接历史", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ScreenHistory screenHistory) throws IOException {
        // <1> url 字符串拼接
        String m = ":";
//        System.out.print(" a1 screenHistory.getHistoryIp(): "+screenHistory.getHistoryIp());
//        System.out.print(" a2 m: "+m);
//        System.out.print(" a3 screenHistory.getHistoryPort(): "+screenHistory.getHistoryPort());
//        System.out.print(" a4 screenHistory.getHistoryApi(): "+screenHistory.getHistoryApi());

        final String serviceUrl= new StringBuilder().append(screenHistory.getHistoryIp()).append(m).append(screenHistory.getHistoryPort()).append(screenHistory.getHistoryApi()).toString();
//        final String serviceUrl = screenHistory.getHistoryIp()+":"+screenHistory.getHistoryPort()+
//                screenHistory.getHistoryApi();         System.out.print(serviceUrl);
//        final String apiUrl = "http://localhost:5000/xpublish-web/service/rs/v1/operationService/refreshMaterialContent";
        //<2> 创建RefreshMaterialContent 实例
        RefreshMaterialContent materialContent = new RefreshMaterialContent();
        //创建 ArrayList 实例
        List<String> TerminalUniqueFieldValues = new ArrayList<>();
            //TerminalUniqueFieldValues 是 非必填的值1，
        String stringTerminalUniqueFieldValues = "";
        if(screenHistory.getFieldValues()!= null){
            if(screenHistory.getFieldValues()!="") {
                stringTerminalUniqueFieldValues=screenHistory.getFieldValues();
            }

        }
        //添加元素
        TerminalUniqueFieldValues.add(screenHistory.getFieldValues());
        //赋值 TerminalUniqueFieldType 是 非必填的值2，
        String stringTerminalUniqueFieldType = "";
        if(screenHistory.getFieldType()!= null){
            if(screenHistory.getFieldType()!="") {
                stringTerminalUniqueFieldType=screenHistory.getFieldType();
            }

        }
        materialContent.setTerminalUniqueFieldType(stringTerminalUniqueFieldType);
        //赋值 TerminalUniqueFieldValues
        materialContent.setTerminalUniqueFieldValues(TerminalUniqueFieldValues);
        //赋值 OperatorType  是 非必填的值3
        String stringOperatorType = "";
        if(screenHistory.getOperatorType()!= null) {
            if(screenHistory.getOperatorType()!="") {
                stringOperatorType = screenHistory.getOperatorType();
            }

        }
            materialContent.setOperatorType(stringOperatorType);
        //获取 businessData 字符串
        String str_businessData = screenHistory.getBusinessData();

        //<3> String 转换为 Json
        JSONObject jsonObject  = JSONObject.parseObject(str_businessData);
        //给json 赋值
//        jsonObject.put("")
        //输出内容到控制台
//        System.out.print(" b1 jsonObject: ");
//        System.out.print(jsonObject);
//        System.out.print(" b2 jsonObject.get(\"materialUniqueFieldType\"): ");
//        System.out.print(jsonObject.get("materialUniqueFieldType"));

        //<4> 给RefreshMaterialContent的businessData赋值的准备
        //创建Resource 实例
        Resource resource = new Resource();
        //获取Object中的值
        //获取  materialNo playTime  resourceValue materialUniqueFieldType materialUniqueFieldValue resourceType等属性
        String str_materialNo = "";
        if(jsonObject.get("materialNo")!=null){
            if(jsonObject.get("materialNo").equals("")) {

            }else{
                str_materialNo = jsonObject.get("materialNo").toString();
                resource.setMaterialNo(Long.parseLong(str_materialNo));
            }

        }

//        //测试
//        System.out.print(" c1 str_materialNo: ");
//        System.out.print(str_materialNo);
        //playTime 是非必填值4
        String str_playTime = "";
        if(jsonObject.get("playTime")!=null){
            if(jsonObject.get("playTime").equals("")) {

            }else{
                str_playTime = jsonObject.get("playTime").toString();
                resource.setPlayTime(Long.parseLong(str_playTime));
            }
        }

//
        String str_resourceValue = jsonObject.get("resourceValue").toString();
        String str_materialUniqueFieldType = jsonObject.get("materialUniqueFieldType").toString();
        String str_materialUniqueFieldValue = jsonObject.get("materialUniqueFieldValue").toString();
        String str_resourceType = jsonObject.get("resourceType").toString();
        //将 获取的 string (转换为 long 再)传入 resource
        //


        resource.setResourceValue(str_resourceValue);
        //输出 resource
        System.out.print(" d1 resource： ");
        System.out.print(resource);
        //创建 Material 实例
        Material material = new Material();
        //赋值
        material.setMaterialUniqueFieldType(str_materialUniqueFieldType);
        material.setMaterialUniqueFieldValue(str_materialUniqueFieldValue);
        material.setResourceType(str_resourceType);
        //创建 List<resource>  实例
        List<Resource> resourceListEntity = new ArrayList<>();
        resourceListEntity.add(resource);
        //入参 类型 List<resource>
        material.setResourceList(resourceListEntity);
        //输出 material
        System.out.print(" e1 material: ");
        System.out.print(material);
        //最后的拼接（嵌套）
        //创建 List<material>  实例
        List<Material> materialListEntity = new ArrayList<>();
        materialListEntity.add(material);
        materialContent.setBusinessData(materialListEntity);
        //输出 materialContent
        System.out.print(" f1 materialContent: ");
        System.out.print(materialContent);
        //调用 screenService.syncPost（）
        String jsonStr = JSON.toJSONString(materialContent);

        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add("params"); // 排除字段名为params的字段
        String jsonString = JSON.toJSONString(materialContent, filter);
        //使用 httpclient 调用显示屏设备接口
        JSONObject jsonObjectFinal =  com.alibaba.fastjson.JSON.parseObject(jsonString);
        String postResult = ScreenServiceHttpImpl.postData(serviceUrl, jsonObjectFinal);
        System.out.println(" g1 postResult: "+postResult);
//        screenService.syncPost(serviceUrl,JSON.toJSONString(materialContent, filter));
        //输出调用接口的结果到控制台
        //将调用 screenService.syncPost（）的response 存入数据库表格 的 response字段里
        screenHistory.setHistoryResponse(postResult);
        //根据返回的response 判断状态， 存入 数据库表格 的 status字段里(待实现）
        // string 转 json
        JSONObject jsonObjectPostResult  = JSONObject.parseObject(postResult);
        String postRustMsg= jsonObjectPostResult.get("msg").toString();
        if(postRustMsg.equals("操作成功") ){
            screenHistory.setHistoryStatus("Success");
        }else{
            screenHistory.setHistoryStatus("Failed");
        }
        //将businessData存入数据库
        screenHistory.setBusinessData(str_businessData);
        return toAjax(screenHistoryService.insertScreenHistory(screenHistory));
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
     * 修改设备对接历史
     */
    @RequiresPermissions("screen:history:edit")
    @Log(title = "设备对接历史", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ScreenHistory screenHistory)
    {
        return toAjax(screenHistoryService.updateScreenHistory(screenHistory));
    }

    /**
     * 删除设备对接历史
     */
    @RequiresPermissions("screen:history:remove")
    @Log(title = "设备对接历史", businessType = BusinessType.DELETE)
	@DeleteMapping("/{historyIds}")
    public AjaxResult remove(@PathVariable Long[] historyIds)
    {
        return toAjax(screenHistoryService.deleteScreenHistoryByHistoryIds(historyIds));
    }
}

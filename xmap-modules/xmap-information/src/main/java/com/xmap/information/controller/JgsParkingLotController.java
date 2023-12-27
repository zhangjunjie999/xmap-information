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
import com.xmap.information.domain.JgsParkingLot;
import com.xmap.information.service.IJgsParkingLotService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 停车场信息表Controller
 * 
 * @author xmap
 * @date 2023-11-03
 */
@RestController
@RequestMapping("/lot")
public class JgsParkingLotController extends BaseController
{
    @Autowired
    private IJgsParkingLotService jgsParkingLotService;

    /**
     * 查询停车场信息表列表
     */
    @RequiresPermissions("information:lot:list")
    @GetMapping("/list")
    public TableDataInfo list(JgsParkingLot jgsParkingLot)
    {
        startPage();
        List<JgsParkingLot> list = jgsParkingLotService.selectJgsParkingLotList(jgsParkingLot);
        return getDataTable(list);
    }

    /**
     * 导出停车场信息表列表
     */
    @RequiresPermissions("information:lot:export")
    @Log(title = "停车场信息表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JgsParkingLot jgsParkingLot)
    {
        List<JgsParkingLot> list = jgsParkingLotService.selectJgsParkingLotList(jgsParkingLot);
        ExcelUtil<JgsParkingLot> util = new ExcelUtil<JgsParkingLot>(JgsParkingLot.class);
        util.exportExcel(response, list, "停车场信息表数据");
    }

    /**
     * 获取停车场信息表详细信息
     */
    @RequiresPermissions("information:lot:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jgsParkingLotService.selectJgsParkingLotById(id));
    }

    /**
     * 新增停车场信息表
     */
    @RequiresPermissions("information:lot:add")
    @Log(title = "停车场信息表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JgsParkingLot jgsParkingLot)
    {
        return toAjax(jgsParkingLotService.insertJgsParkingLot(jgsParkingLot));
    }

    /**
     * 修改停车场信息表
     */
    @RequiresPermissions("information:lot:edit")
    @Log(title = "停车场信息表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JgsParkingLot jgsParkingLot)
    {
        return toAjax(jgsParkingLotService.updateJgsParkingLot(jgsParkingLot));
    }

    /**
     * 删除停车场信息表
     */
    @RequiresPermissions("information:lot:remove")
    @Log(title = "停车场信息表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jgsParkingLotService.deleteJgsParkingLotByIds(ids));
    }
}

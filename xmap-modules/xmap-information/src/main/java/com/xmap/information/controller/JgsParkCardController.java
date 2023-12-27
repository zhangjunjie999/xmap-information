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
import com.xmap.information.domain.JgsParkCard;
import com.xmap.information.service.IJgsParkCardService;
import com.xmap.common.core.web.controller.BaseController;
import com.xmap.common.core.web.domain.AjaxResult;
import com.xmap.common.core.utils.poi.ExcelUtil;
import com.xmap.common.core.web.page.TableDataInfo;

/**
 * 内容发布引导牌管理Controller
 *
 * @author xmap
 * @date 2023-11-07
 */
@RestController
@RequestMapping("/card")
public class JgsParkCardController extends BaseController
{
    @Autowired
    private IJgsParkCardService jgsParkCardService;

    /**
     * 查询内容发布引导牌管理列表
     */
    @RequiresPermissions("information:card:list")
    @GetMapping("/list")
    public TableDataInfo list(JgsParkCard jgsParkCard)
    {
        startPage();
        List<JgsParkCard> list = jgsParkCardService.selectJgsParkCardList(jgsParkCard);
        return getDataTable(list);
    }

    /**
     * 导出内容发布引导牌管理列表
     */
    @RequiresPermissions("information:card:export")
    @Log(title = "内容发布引导牌管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JgsParkCard jgsParkCard)
    {
        List<JgsParkCard> list = jgsParkCardService.selectJgsParkCardList(jgsParkCard);
        ExcelUtil<JgsParkCard> util = new ExcelUtil<JgsParkCard>(JgsParkCard.class);
        util.exportExcel(response, list, "内容发布引导牌管理数据");
    }

    /**
     * 获取内容发布引导牌管理详细信息
     */
    @RequiresPermissions("information:card:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jgsParkCardService.selectJgsParkCardById(id));
    }

    /**
     * 新增内容发布引导牌管理
     */
    @RequiresPermissions("information:card:add")
    @Log(title = "内容发布引导牌管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JgsParkCard jgsParkCard)
    {
        return toAjax(jgsParkCardService.insertJgsParkCard(jgsParkCard));
    }

    /**
     * 修改内容发布引导牌管理
     */
    @RequiresPermissions("information:card:edit")
    @Log(title = "内容发布引导牌管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JgsParkCard jgsParkCard)
    {
        return toAjax(jgsParkCardService.updateJgsParkCard(jgsParkCard));
    }

    /**
     * 删除内容发布引导牌管理
     */
    @RequiresPermissions("information:card:remove")
    @Log(title = "内容发布引导牌管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jgsParkCardService.deleteJgsParkCardByIds(ids));
    }

//    // 审核通过内容
//    @RequiresPermissions("information:card:approve")
//    @PutMapping("/{id}")
//    public JgsParkCard approveContent(@PathVariable Long id) {
//        JgsParkCard jgsParkCard = jgsParkCardService.selectJgsParkCardById(id);
//        if (jgsParkCard != null) {
//            jgsParkCard.setVerifyStatus("审核通过");
//            jgsParkCardService.updateJgsParkCard(jgsParkCard);
//        }
//        return jgsParkCard;
//    }
//
//    // 审核不通过内容
//    @RequiresPermissions("information:card:approve")
//    @PutMapping("/{id}")
//    public JgsParkCard rejectContent(@PathVariable Long id) {
//        JgsParkCard jgsParkCard = jgsParkCardService.selectJgsParkCardById(id);
//        if (jgsParkCard != null) {
//            jgsParkCard.setVerifyStatus("审核未通过");
//            jgsParkCardService.updateJgsParkCard(jgsParkCard);
//        }
//        return jgsParkCard;
//    }
}

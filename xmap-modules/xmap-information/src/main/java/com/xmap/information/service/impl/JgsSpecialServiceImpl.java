package com.xmap.information.service.impl;

import java.util.List;
import com.xmap.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.JgsSpecialMapper;
import com.xmap.information.domain.JgsSpecial;
import com.xmap.information.service.IJgsSpecialService;

/**
 * 特色产品管理Service业务层处理
 * 
 * @author xmap
 * @date 2023-07-28
 */
@Service
public class JgsSpecialServiceImpl implements IJgsSpecialService 
{
    @Autowired
    private JgsSpecialMapper jgsSpecialMapper;

    /**
     * 查询特色产品管理
     * 
     * @param id 特色产品管理主键
     * @return 特色产品管理
     */
    @Override
    public JgsSpecial selectJgsSpecialById(Long id)
    {
        return jgsSpecialMapper.selectJgsSpecialById(id);
    }

    /**
     * 查询特色产品管理列表
     * 
     * @param jgsSpecial 特色产品管理
     * @return 特色产品管理
     */
    @Override
    public List<JgsSpecial> selectJgsSpecialList(JgsSpecial jgsSpecial)
    {
        return jgsSpecialMapper.selectJgsSpecialList(jgsSpecial);
    }

    /**
     * 新增特色产品管理
     * 
     * @param jgsSpecial 特色产品管理
     * @return 结果
     */
    @Override
    public int insertJgsSpecial(JgsSpecial jgsSpecial)
    {
        jgsSpecial.setCreateTime(DateUtils.getNowDate());
        return jgsSpecialMapper.insertJgsSpecial(jgsSpecial);
    }

    /**
     * 修改特色产品管理
     * 
     * @param jgsSpecial 特色产品管理
     * @return 结果
     */
    @Override
    public int updateJgsSpecial(JgsSpecial jgsSpecial)
    {
        return jgsSpecialMapper.updateJgsSpecial(jgsSpecial);
    }

    /**
     * 批量删除特色产品管理
     * 
     * @param ids 需要删除的特色产品管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsSpecialByIds(Long[] ids)
    {
        return jgsSpecialMapper.deleteJgsSpecialByIds(ids);
    }

    /**
     * 删除特色产品管理信息
     * 
     * @param id 特色产品管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsSpecialById(Long id)
    {
        return jgsSpecialMapper.deleteJgsSpecialById(id);
    }
}

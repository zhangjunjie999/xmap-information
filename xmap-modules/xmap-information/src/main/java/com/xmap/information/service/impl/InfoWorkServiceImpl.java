package com.xmap.information.service.impl;

import java.util.List;
import com.xmap.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.InfoWorkMapper;
import com.xmap.information.domain.InfoWork;
import com.xmap.information.service.IInfoWorkService;

/**
 * 工作动态列Service业务层处理
 * 
 * @author xmap
 * @date 2023-07-03
 */
@Service
public class InfoWorkServiceImpl implements IInfoWorkService 
{
    @Autowired
    private InfoWorkMapper infoWorkMapper;

    /**
     * 查询工作动态列
     * 
     * @param dynamicId 工作动态列主键
     * @return 工作动态列
     */
    @Override
    public InfoWork selectInfoWorkByDynamicId(Long dynamicId)
    {
        return infoWorkMapper.selectInfoWorkByDynamicId(dynamicId);
    }

    /**
     * 查询工作动态列列表
     * 
     * @param infoWork 工作动态列
     * @return 工作动态列
     */
    @Override
    public List<InfoWork> selectInfoWorkList(InfoWork infoWork)
    {
        return infoWorkMapper.selectInfoWorkList(infoWork);
    }

    /**
     * 新增工作动态列
     * 
     * @param infoWork 工作动态列
     * @return 结果
     */
    @Override
    public int insertInfoWork(InfoWork infoWork)
    {
        infoWork.setCreateTime(DateUtils.getNowDate());
        return infoWorkMapper.insertInfoWork(infoWork);
    }

    /**
     * 修改工作动态列
     * 
     * @param infoWork 工作动态列
     * @return 结果
     */
    @Override
    public int updateInfoWork(InfoWork infoWork)
    {
        return infoWorkMapper.updateInfoWork(infoWork);
    }

    /**
     * 批量删除工作动态列
     * 
     * @param dynamicIds 需要删除的工作动态列主键
     * @return 结果
     */
    @Override
    public int deleteInfoWorkByDynamicIds(Long[] dynamicIds)
    {
        return infoWorkMapper.deleteInfoWorkByDynamicIds(dynamicIds);
    }

    /**
     * 删除工作动态列信息
     * 
     * @param dynamicId 工作动态列主键
     * @return 结果
     */
    @Override
    public int deleteInfoWorkByDynamicId(Long dynamicId)
    {
        return infoWorkMapper.deleteInfoWorkByDynamicId(dynamicId);
    }
}

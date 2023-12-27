package com.xmap.information.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.JgsUrgencyMapper;
import com.xmap.information.domain.JgsUrgency;
import com.xmap.information.service.IJgsUrgencyService;

/**
 * 紧急插播管理Service业务层处理
 * 
 * @author xmap
 * @date 2023-07-31
 */
@Service
public class JgsUrgencyServiceImpl implements IJgsUrgencyService 
{
    @Autowired
    private JgsUrgencyMapper jgsUrgencyMapper;

    /**
     * 查询紧急插播管理
     * 
     * @param id 紧急插播管理主键
     * @return 紧急插播管理
     */
    @Override
    public JgsUrgency selectJgsUrgencyById(Long id)
    {
        return jgsUrgencyMapper.selectJgsUrgencyById(id);
    }

    /**
     * 查询紧急插播管理列表
     * 
     * @param jgsUrgency 紧急插播管理
     * @return 紧急插播管理
     */
    @Override
    public List<JgsUrgency> selectJgsUrgencyList(JgsUrgency jgsUrgency)
    {
        return jgsUrgencyMapper.selectJgsUrgencyList(jgsUrgency);
    }

    /**
     * 新增紧急插播管理
     * 
     * @param jgsUrgency 紧急插播管理
     * @return 结果
     */
    @Override
    public int insertJgsUrgency(JgsUrgency jgsUrgency)
    {
        return jgsUrgencyMapper.insertJgsUrgency(jgsUrgency);
    }

    /**
     * 修改紧急插播管理
     * 
     * @param jgsUrgency 紧急插播管理
     * @return 结果
     */
    @Override
    public int updateJgsUrgency(JgsUrgency jgsUrgency)
    {
        return jgsUrgencyMapper.updateJgsUrgency(jgsUrgency);
    }

    /**
     * 批量删除紧急插播管理
     * 
     * @param ids 需要删除的紧急插播管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsUrgencyByIds(Long[] ids)
    {
        return jgsUrgencyMapper.deleteJgsUrgencyByIds(ids);
    }

    /**
     * 删除紧急插播管理信息
     * 
     * @param id 紧急插播管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsUrgencyById(Long id)
    {
        return jgsUrgencyMapper.deleteJgsUrgencyById(id);
    }
}

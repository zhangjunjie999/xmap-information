package com.xmap.information.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.JgsPlanMapper;
import com.xmap.information.domain.JgsPlan;
import com.xmap.information.service.IJgsPlanService;

/**
 * 旅游攻略表Service业务层处理
 * 
 * @author xmap
 * @date 2023-07-28
 */
@Service
public class JgsPlanServiceImpl implements IJgsPlanService 
{
    @Autowired
    private JgsPlanMapper jgsPlanMapper;

    /**
     * 查询旅游攻略表
     * 
     * @param id 旅游攻略表主键
     * @return 旅游攻略表
     */
    @Override
    public JgsPlan selectJgsPlanById(Long id)
    {
        return jgsPlanMapper.selectJgsPlanById(id);
    }

    /**
     * 查询旅游攻略表列表
     * 
     * @param jgsPlan 旅游攻略表
     * @return 旅游攻略表
     */
    @Override
    public List<JgsPlan> selectJgsPlanList(JgsPlan jgsPlan)
    {
        return jgsPlanMapper.selectJgsPlanList(jgsPlan);
    }

    /**
     * 新增旅游攻略表
     * 
     * @param jgsPlan 旅游攻略表
     * @return 结果
     */
    @Override
    public int insertJgsPlan(JgsPlan jgsPlan)
    {
        return jgsPlanMapper.insertJgsPlan(jgsPlan);
    }

    /**
     * 修改旅游攻略表
     * 
     * @param jgsPlan 旅游攻略表
     * @return 结果
     */
    @Override
    public int updateJgsPlan(JgsPlan jgsPlan)
    {
        return jgsPlanMapper.updateJgsPlan(jgsPlan);
    }

    /**
     * 批量删除旅游攻略表
     * 
     * @param ids 需要删除的旅游攻略表主键
     * @return 结果
     */
    @Override
    public int deleteJgsPlanByIds(Long[] ids)
    {
        return jgsPlanMapper.deleteJgsPlanByIds(ids);
    }

    /**
     * 删除旅游攻略表信息
     * 
     * @param id 旅游攻略表主键
     * @return 结果
     */
    @Override
    public int deleteJgsPlanById(Long id)
    {
        return jgsPlanMapper.deleteJgsPlanById(id);
    }
}

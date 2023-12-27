package com.xmap.information.mapper;

import java.util.List;
import com.xmap.information.domain.JgsPlan;

/**
 * 旅游攻略表Mapper接口
 * 
 * @author xmap
 * @date 2023-07-28
 */
public interface JgsPlanMapper 
{
    /**
     * 查询旅游攻略表
     * 
     * @param id 旅游攻略表主键
     * @return 旅游攻略表
     */
    public JgsPlan selectJgsPlanById(Long id);

    /**
     * 查询旅游攻略表列表
     * 
     * @param jgsPlan 旅游攻略表
     * @return 旅游攻略表集合
     */
    public List<JgsPlan> selectJgsPlanList(JgsPlan jgsPlan);

    /**
     * 新增旅游攻略表
     * 
     * @param jgsPlan 旅游攻略表
     * @return 结果
     */
    public int insertJgsPlan(JgsPlan jgsPlan);

    /**
     * 修改旅游攻略表
     * 
     * @param jgsPlan 旅游攻略表
     * @return 结果
     */
    public int updateJgsPlan(JgsPlan jgsPlan);

    /**
     * 删除旅游攻略表
     * 
     * @param id 旅游攻略表主键
     * @return 结果
     */
    public int deleteJgsPlanById(Long id);

    /**
     * 批量删除旅游攻略表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJgsPlanByIds(Long[] ids);
}

package com.xmap.information.mapper;

import java.util.List;
import com.xmap.information.domain.JgsPlay;

/**
 * 玩转井冈山Mapper接口
 * 
 * @author xmap
 * @date 2023-07-28
 */
public interface JgsPlayMapper 
{
    /**
     * 查询玩转井冈山
     * 
     * @param id 玩转井冈山主键
     * @return 玩转井冈山
     */
    public JgsPlay selectJgsPlayById(Long id);

    /**
     * 查询玩转井冈山列表
     * 
     * @param jgsPlay 玩转井冈山
     * @return 玩转井冈山集合
     */
    public List<JgsPlay> selectJgsPlayList(JgsPlay jgsPlay);

    /**
     * 新增玩转井冈山
     * 
     * @param jgsPlay 玩转井冈山
     * @return 结果
     */
    public int insertJgsPlay(JgsPlay jgsPlay);

    /**
     * 修改玩转井冈山
     * 
     * @param jgsPlay 玩转井冈山
     * @return 结果
     */
    public int updateJgsPlay(JgsPlay jgsPlay);

    /**
     * 删除玩转井冈山
     * 
     * @param id 玩转井冈山主键
     * @return 结果
     */
    public int deleteJgsPlayById(Long id);

    /**
     * 批量删除玩转井冈山
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJgsPlayByIds(Long[] ids);
}

package com.xmap.information.service;

import java.util.List;
import com.xmap.information.domain.JgsAdvertise;

/**
 * 广告管理Service接口
 * 
 * @author xmap
 * @date 2023-11-15
 */
public interface IJgsAdvertiseService 
{
    /**
     * 查询广告管理
     * 
     * @param id 广告管理主键
     * @return 广告管理
     */
    public JgsAdvertise selectJgsAdvertiseById(Long id);

    /**
     * 查询广告管理列表
     * 
     * @param jgsAdvertise 广告管理
     * @return 广告管理集合
     */
    public List<JgsAdvertise> selectJgsAdvertiseList(JgsAdvertise jgsAdvertise);

    /**
     * 新增广告管理
     * 
     * @param jgsAdvertise 广告管理
     * @return 结果
     */
    public int insertJgsAdvertise(JgsAdvertise jgsAdvertise);

    /**
     * 修改广告管理
     * 
     * @param jgsAdvertise 广告管理
     * @return 结果
     */
    public int updateJgsAdvertise(JgsAdvertise jgsAdvertise);

    /**
     * 批量删除广告管理
     * 
     * @param ids 需要删除的广告管理主键集合
     * @return 结果
     */
    public int deleteJgsAdvertiseByIds(Long[] ids);

    /**
     * 删除广告管理信息
     * 
     * @param id 广告管理主键
     * @return 结果
     */
    public int deleteJgsAdvertiseById(Long id);
}

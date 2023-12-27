package com.xmap.information.service;

import java.util.List;
import com.xmap.information.domain.JgsScreen;

/**
 * 大屏管理Service接口
 * 
 * @author xmap
 * @date 2023-10-09
 */
public interface IJgsScreenService 
{
    /**
     * 查询大屏管理
     * 
     * @param id 大屏管理主键
     * @return 大屏管理
     */
    public JgsScreen selectJgsScreenById(Long id);

    /**
     * 查询大屏管理列表
     * 
     * @param jgsScreen 大屏管理
     * @return 大屏管理集合
     */
    public List<JgsScreen> selectJgsScreenList(JgsScreen jgsScreen);

    /**
     * 新增大屏管理
     * 
     * @param jgsScreen 大屏管理
     * @return 结果
     */
    public int insertJgsScreen(JgsScreen jgsScreen);

    /**
     * 修改大屏管理
     * 
     * @param jgsScreen 大屏管理
     * @return 结果
     */
    public int updateJgsScreen(JgsScreen jgsScreen);

    /**
     * 批量删除大屏管理
     * 
     * @param ids 需要删除的大屏管理主键集合
     * @return 结果
     */
    public int deleteJgsScreenByIds(Long[] ids);

    /**
     * 删除大屏管理信息
     * 
     * @param id 大屏管理主键
     * @return 结果
     */
    public int deleteJgsScreenById(Long id);
}

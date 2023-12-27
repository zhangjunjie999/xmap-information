package com.xmap.information.mapper;

import java.util.List;
import com.xmap.information.domain.JgsScreen;

/**
 * 大屏管理Mapper接口
 * 
 * @author xmap
 * @date 2023-10-09
 */
public interface JgsScreenMapper 
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
     * 删除大屏管理
     * 
     * @param id 大屏管理主键
     * @return 结果
     */
    public int deleteJgsScreenById(Long id);

    /**
     * 批量删除大屏管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJgsScreenByIds(Long[] ids);
}

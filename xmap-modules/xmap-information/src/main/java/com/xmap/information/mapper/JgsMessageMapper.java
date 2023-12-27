package com.xmap.information.mapper;

import java.util.List;
import com.xmap.information.domain.JgsMessage;

/**
 * 井冈山基本信息管理Mapper接口
 * 
 * @author xmap
 * @date 2023-07-28
 */
public interface JgsMessageMapper 
{
    /**
     * 查询井冈山基本信息管理
     * 
     * @param id 井冈山基本信息管理主键
     * @return 井冈山基本信息管理
     */
    public JgsMessage selectJgsMessageById(Long id);

    /**
     * 查询井冈山基本信息管理列表
     * 
     * @param jgsMessage 井冈山基本信息管理
     * @return 井冈山基本信息管理集合
     */
    public List<JgsMessage> selectJgsMessageList(JgsMessage jgsMessage);

    /**
     * 新增井冈山基本信息管理
     * 
     * @param jgsMessage 井冈山基本信息管理
     * @return 结果
     */
    public int insertJgsMessage(JgsMessage jgsMessage);

    /**
     * 修改井冈山基本信息管理
     * 
     * @param jgsMessage 井冈山基本信息管理
     * @return 结果
     */
    public int updateJgsMessage(JgsMessage jgsMessage);

    /**
     * 删除井冈山基本信息管理
     * 
     * @param id 井冈山基本信息管理主键
     * @return 结果
     */
    public int deleteJgsMessageById(Long id);

    /**
     * 批量删除井冈山基本信息管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJgsMessageByIds(Long[] ids);
}

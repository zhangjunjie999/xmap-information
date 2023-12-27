package com.xmap.information.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.JgsMessageMapper;
import com.xmap.information.domain.JgsMessage;
import com.xmap.information.service.IJgsMessageService;

/**
 * 井冈山基本信息管理Service业务层处理
 * 
 * @author xmap
 * @date 2023-07-28
 */
@Service
public class JgsMessageServiceImpl implements IJgsMessageService 
{
    @Autowired
    private JgsMessageMapper jgsMessageMapper;

    /**
     * 查询井冈山基本信息管理
     * 
     * @param id 井冈山基本信息管理主键
     * @return 井冈山基本信息管理
     */
    @Override
    public JgsMessage selectJgsMessageById(Long id)
    {
        return jgsMessageMapper.selectJgsMessageById(id);
    }

    /**
     * 查询井冈山基本信息管理列表
     * 
     * @param jgsMessage 井冈山基本信息管理
     * @return 井冈山基本信息管理
     */
    @Override
    public List<JgsMessage> selectJgsMessageList(JgsMessage jgsMessage)
    {
        return jgsMessageMapper.selectJgsMessageList(jgsMessage);
    }

    /**
     * 新增井冈山基本信息管理
     * 
     * @param jgsMessage 井冈山基本信息管理
     * @return 结果
     */
    @Override
    public int insertJgsMessage(JgsMessage jgsMessage)
    {
        return jgsMessageMapper.insertJgsMessage(jgsMessage);
    }

    /**
     * 修改井冈山基本信息管理
     * 
     * @param jgsMessage 井冈山基本信息管理
     * @return 结果
     */
    @Override
    public int updateJgsMessage(JgsMessage jgsMessage)
    {
        return jgsMessageMapper.updateJgsMessage(jgsMessage);
    }

    /**
     * 批量删除井冈山基本信息管理
     * 
     * @param ids 需要删除的井冈山基本信息管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsMessageByIds(Long[] ids)
    {
        return jgsMessageMapper.deleteJgsMessageByIds(ids);
    }

    /**
     * 删除井冈山基本信息管理信息
     * 
     * @param id 井冈山基本信息管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsMessageById(Long id)
    {
        return jgsMessageMapper.deleteJgsMessageById(id);
    }
}

package com.xmap.information.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.JgsScreenMapper;
import com.xmap.information.domain.JgsScreen;
import com.xmap.information.service.IJgsScreenService;

/**
 * 大屏管理Service业务层处理
 * 
 * @author xmap
 * @date 2023-10-09
 */
@Service
public class JgsScreenServiceImpl implements IJgsScreenService 
{
    @Autowired
    private JgsScreenMapper jgsScreenMapper;

    /**
     * 查询大屏管理
     * 
     * @param id 大屏管理主键
     * @return 大屏管理
     */
    @Override
    public JgsScreen selectJgsScreenById(Long id)
    {
        return jgsScreenMapper.selectJgsScreenById(id);
    }

    /**
     * 查询大屏管理列表
     * 
     * @param jgsScreen 大屏管理
     * @return 大屏管理
     */
    @Override
    public List<JgsScreen> selectJgsScreenList(JgsScreen jgsScreen)
    {
        return jgsScreenMapper.selectJgsScreenList(jgsScreen);
    }

    /**
     * 新增大屏管理
     * 
     * @param jgsScreen 大屏管理
     * @return 结果
     */
    @Override
    public int insertJgsScreen(JgsScreen jgsScreen)
    {
        return jgsScreenMapper.insertJgsScreen(jgsScreen);
    }

    /**
     * 修改大屏管理
     * 
     * @param jgsScreen 大屏管理
     * @return 结果
     */
    @Override
    public int updateJgsScreen(JgsScreen jgsScreen)
    {
        return jgsScreenMapper.updateJgsScreen(jgsScreen);
    }

    /**
     * 批量删除大屏管理
     * 
     * @param ids 需要删除的大屏管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsScreenByIds(Long[] ids)
    {
        return jgsScreenMapper.deleteJgsScreenByIds(ids);
    }

    /**
     * 删除大屏管理信息
     * 
     * @param id 大屏管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsScreenById(Long id)
    {
        return jgsScreenMapper.deleteJgsScreenById(id);
    }
}

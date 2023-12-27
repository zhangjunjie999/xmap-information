package com.xmap.information.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.JgsInformineMapper;
import com.xmap.information.domain.JgsInformine;
import com.xmap.information.service.IJgsInformineService;

/**
 * 资讯管理Service业务层处理
 * 
 * @author xmap
 * @date 2023-10-08
 */
@Service
public class JgsInformineServiceImpl implements IJgsInformineService 
{
    @Autowired
    private JgsInformineMapper jgsInformineMapper;

    /**
     * 查询资讯管理
     * 
     * @param id 资讯管理主键
     * @return 资讯管理
     */
    @Override
    public JgsInformine selectJgsInformineById(Long id)
    {
        return jgsInformineMapper.selectJgsInformineById(id);
    }

    /**
     * 查询资讯管理列表
     * 
     * @param jgsInformine 资讯管理
     * @return 资讯管理
     */
    @Override
    public List<JgsInformine> selectJgsInformineList(JgsInformine jgsInformine)
    {
        return jgsInformineMapper.selectJgsInformineList(jgsInformine);
    }

    /**
     * 新增资讯管理
     * 
     * @param jgsInformine 资讯管理
     * @return 结果
     */
    @Override
    public int insertJgsInformine(JgsInformine jgsInformine)
    {
        return jgsInformineMapper.insertJgsInformine(jgsInformine);
    }

    /**
     * 修改资讯管理
     * 
     * @param jgsInformine 资讯管理
     * @return 结果
     */
    @Override
    public int updateJgsInformine(JgsInformine jgsInformine)
    {
        return jgsInformineMapper.updateJgsInformine(jgsInformine);
    }

    /**
     * 批量删除资讯管理
     * 
     * @param ids 需要删除的资讯管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsInformineByIds(Long[] ids)
    {
        return jgsInformineMapper.deleteJgsInformineByIds(ids);
    }

    /**
     * 删除资讯管理信息
     * 
     * @param id 资讯管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsInformineById(Long id)
    {
        return jgsInformineMapper.deleteJgsInformineById(id);
    }
}

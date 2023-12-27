package com.xmap.information.service.impl;

import java.util.List;
import com.xmap.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.JgsDictionaryMapper;
import com.xmap.information.domain.JgsDictionary;
import com.xmap.information.service.IJgsDictionaryService;

/**
 * 字典管理Service业务层处理
 * 
 * @author xmap
 * @date 2023-08-15
 */
@Service
public class JgsDictionaryServiceImpl implements IJgsDictionaryService 
{
    @Autowired
    private JgsDictionaryMapper jgsDictionaryMapper;

    /**
     * 查询字典管理
     * 
     * @param id 字典管理主键
     * @return 字典管理
     */
    @Override
    public JgsDictionary selectJgsDictionaryById(Long id)
    {
        return jgsDictionaryMapper.selectJgsDictionaryById(id);
    }

    /**
     * 查询字典管理列表
     * 
     * @param jgsDictionary 字典管理
     * @return 字典管理
     */
    @Override
    public List<JgsDictionary> selectJgsDictionaryList(JgsDictionary jgsDictionary)
    {
        return jgsDictionaryMapper.selectJgsDictionaryList(jgsDictionary);
    }

    /**
     * 新增字典管理
     * 
     * @param jgsDictionary 字典管理
     * @return 结果
     */
    @Override
    public int insertJgsDictionary(JgsDictionary jgsDictionary)
    {
        jgsDictionary.setCreateTime(DateUtils.getNowDate());
        return jgsDictionaryMapper.insertJgsDictionary(jgsDictionary);
    }

    /**
     * 修改字典管理
     * 
     * @param jgsDictionary 字典管理
     * @return 结果
     */
    @Override
    public int updateJgsDictionary(JgsDictionary jgsDictionary)
    {
        jgsDictionary.setUpdateTime(DateUtils.getNowDate());
        return jgsDictionaryMapper.updateJgsDictionary(jgsDictionary);
    }

    /**
     * 批量删除字典管理
     * 
     * @param ids 需要删除的字典管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsDictionaryByIds(Long[] ids)
    {
        return jgsDictionaryMapper.deleteJgsDictionaryByIds(ids);
    }

    /**
     * 删除字典管理信息
     * 
     * @param id 字典管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsDictionaryById(Long id)
    {
        return jgsDictionaryMapper.deleteJgsDictionaryById(id);
    }
}

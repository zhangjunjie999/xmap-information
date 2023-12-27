package com.xmap.information.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.JgsImageMapper;
import com.xmap.information.domain.JgsImage;
import com.xmap.information.service.IJgsImageService;

/**
 * 图片管理Service业务层处理
 * 
 * @author xmap
 * @date 2023-07-28
 */
@Service
public class JgsImageServiceImpl implements IJgsImageService 
{
    @Autowired
    private JgsImageMapper jgsImageMapper;

    /**
     * 查询图片管理
     * 
     * @param id 图片管理主键
     * @return 图片管理
     */
    @Override
    public JgsImage selectJgsImageById(Long id)
    {
        return jgsImageMapper.selectJgsImageById(id);
    }

    /**
     * 查询图片管理列表
     * 
     * @param jgsImage 图片管理
     * @return 图片管理
     */
    @Override
    public List<JgsImage> selectJgsImageList(JgsImage jgsImage)
    {
        return jgsImageMapper.selectJgsImageList(jgsImage);
    }

    /**
     * 新增图片管理
     * 
     * @param jgsImage 图片管理
     * @return 结果
     */
    @Override
    public int insertJgsImage(JgsImage jgsImage)
    {
        return jgsImageMapper.insertJgsImage(jgsImage);
    }

    /**
     * 修改图片管理
     * 
     * @param jgsImage 图片管理
     * @return 结果
     */
    @Override
    public int updateJgsImage(JgsImage jgsImage)
    {
        return jgsImageMapper.updateJgsImage(jgsImage);
    }

    /**
     * 批量删除图片管理
     * 
     * @param ids 需要删除的图片管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsImageByIds(Long[] ids)
    {
        return jgsImageMapper.deleteJgsImageByIds(ids);
    }

    /**
     * 删除图片管理信息
     * 
     * @param id 图片管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsImageById(Long id)
    {
        return jgsImageMapper.deleteJgsImageById(id);
    }
}

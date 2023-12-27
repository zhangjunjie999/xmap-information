package com.xmap.information.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.JgsMediaMapper;
import com.xmap.information.domain.JgsMedia;
import com.xmap.information.service.IJgsMediaService;

/**
 * 多媒体管理Service业务层处理
 * 
 * @author xmap
 * @date 2023-07-31
 */
@Service
public class JgsMediaServiceImpl implements IJgsMediaService 
{
    @Autowired
    private JgsMediaMapper jgsMediaMapper;

    /**
     * 查询多媒体管理
     * 
     * @param id 多媒体管理主键
     * @return 多媒体管理
     */
    @Override
    public JgsMedia selectJgsMediaById(Long id)
    {
        return jgsMediaMapper.selectJgsMediaById(id);
    }

    /**
     * 查询多媒体管理列表
     * 
     * @param jgsMedia 多媒体管理
     * @return 多媒体管理
     */
    @Override
    public List<JgsMedia> selectJgsMediaList(JgsMedia jgsMedia)
    {
        return jgsMediaMapper.selectJgsMediaList(jgsMedia);
    }

    /**
     * 新增多媒体管理
     * 
     * @param jgsMedia 多媒体管理
     * @return 结果
     */
    @Override
    public int insertJgsMedia(JgsMedia jgsMedia)
    {
        return jgsMediaMapper.insertJgsMedia(jgsMedia);
    }

    /**
     * 修改多媒体管理
     * 
     * @param jgsMedia 多媒体管理
     * @return 结果
     */
    @Override
    public int updateJgsMedia(JgsMedia jgsMedia)
    {
        return jgsMediaMapper.updateJgsMedia(jgsMedia);
    }

    /**
     * 批量删除多媒体管理
     * 
     * @param ids 需要删除的多媒体管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsMediaByIds(Long[] ids)
    {
        return jgsMediaMapper.deleteJgsMediaByIds(ids);
    }

    /**
     * 删除多媒体管理信息
     * 
     * @param id 多媒体管理主键
     * @return 结果
     */
    @Override
    public int deleteJgsMediaById(Long id)
    {
        return jgsMediaMapper.deleteJgsMediaById(id);
    }
}

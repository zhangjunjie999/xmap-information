package com.xmap.information.mapper;

import java.util.List;
import com.xmap.information.domain.JgsMedia;

/**
 * 多媒体管理Mapper接口
 * 
 * @author xmap
 * @date 2023-07-31
 */
public interface JgsMediaMapper 
{
    /**
     * 查询多媒体管理
     * 
     * @param id 多媒体管理主键
     * @return 多媒体管理
     */
    public JgsMedia selectJgsMediaById(Long id);

    /**
     * 查询多媒体管理列表
     * 
     * @param jgsMedia 多媒体管理
     * @return 多媒体管理集合
     */
    public List<JgsMedia> selectJgsMediaList(JgsMedia jgsMedia);

    /**
     * 新增多媒体管理
     * 
     * @param jgsMedia 多媒体管理
     * @return 结果
     */
    public int insertJgsMedia(JgsMedia jgsMedia);

    /**
     * 修改多媒体管理
     * 
     * @param jgsMedia 多媒体管理
     * @return 结果
     */
    public int updateJgsMedia(JgsMedia jgsMedia);

    /**
     * 删除多媒体管理
     * 
     * @param id 多媒体管理主键
     * @return 结果
     */
    public int deleteJgsMediaById(Long id);

    /**
     * 批量删除多媒体管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJgsMediaByIds(Long[] ids);
}

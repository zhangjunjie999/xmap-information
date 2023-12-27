package com.xmap.information.mapper;

import java.util.List;
import com.xmap.information.domain.JgsImage;

/**
 * 图片管理Mapper接口
 * 
 * @author xmap
 * @date 2023-07-28
 */
public interface JgsImageMapper 
{
    /**
     * 查询图片管理
     * 
     * @param id 图片管理主键
     * @return 图片管理
     */
    public JgsImage selectJgsImageById(Long id);

    /**
     * 查询图片管理列表
     * 
     * @param jgsImage 图片管理
     * @return 图片管理集合
     */
    public List<JgsImage> selectJgsImageList(JgsImage jgsImage);

    /**
     * 新增图片管理
     * 
     * @param jgsImage 图片管理
     * @return 结果
     */
    public int insertJgsImage(JgsImage jgsImage);

    /**
     * 修改图片管理
     * 
     * @param jgsImage 图片管理
     * @return 结果
     */
    public int updateJgsImage(JgsImage jgsImage);

    /**
     * 删除图片管理
     * 
     * @param id 图片管理主键
     * @return 结果
     */
    public int deleteJgsImageById(Long id);

    /**
     * 批量删除图片管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJgsImageByIds(Long[] ids);
}

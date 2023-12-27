package com.xmap.information.mapper;

import java.util.List;
import com.xmap.information.domain.JgsSpecial;

/**
 * 特色产品管理Mapper接口
 * 
 * @author xmap
 * @date 2023-07-28
 */
public interface JgsSpecialMapper 
{
    /**
     * 查询特色产品管理
     * 
     * @param id 特色产品管理主键
     * @return 特色产品管理
     */
    public JgsSpecial selectJgsSpecialById(Long id);

    /**
     * 查询特色产品管理列表
     * 
     * @param jgsSpecial 特色产品管理
     * @return 特色产品管理集合
     */
    public List<JgsSpecial> selectJgsSpecialList(JgsSpecial jgsSpecial);

    /**
     * 新增特色产品管理
     * 
     * @param jgsSpecial 特色产品管理
     * @return 结果
     */
    public int insertJgsSpecial(JgsSpecial jgsSpecial);

    /**
     * 修改特色产品管理
     * 
     * @param jgsSpecial 特色产品管理
     * @return 结果
     */
    public int updateJgsSpecial(JgsSpecial jgsSpecial);

    /**
     * 删除特色产品管理
     * 
     * @param id 特色产品管理主键
     * @return 结果
     */
    public int deleteJgsSpecialById(Long id);

    /**
     * 批量删除特色产品管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJgsSpecialByIds(Long[] ids);
}

package com.xmap.information.mapper;

import java.util.List;
import com.xmap.information.domain.JgsInformine;

/**
 * 资讯管理Mapper接口
 * 
 * @author xmap
 * @date 2023-10-08
 */
public interface JgsInformineMapper 
{
    /**
     * 查询资讯管理
     * 
     * @param id 资讯管理主键
     * @return 资讯管理
     */
    public JgsInformine selectJgsInformineById(Long id);

    /**
     * 查询资讯管理列表
     * 
     * @param jgsInformine 资讯管理
     * @return 资讯管理集合
     */
    public List<JgsInformine> selectJgsInformineList(JgsInformine jgsInformine);

    /**
     * 新增资讯管理
     * 
     * @param jgsInformine 资讯管理
     * @return 结果
     */
    public int insertJgsInformine(JgsInformine jgsInformine);

    /**
     * 修改资讯管理
     * 
     * @param jgsInformine 资讯管理
     * @return 结果
     */
    public int updateJgsInformine(JgsInformine jgsInformine);

    /**
     * 删除资讯管理
     * 
     * @param id 资讯管理主键
     * @return 结果
     */
    public int deleteJgsInformineById(Long id);

    /**
     * 批量删除资讯管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJgsInformineByIds(Long[] ids);
}

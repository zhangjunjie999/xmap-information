package com.xmap.information.service;

import java.util.List;
import com.xmap.information.domain.JgsParkingLot;

/**
 * 停车场信息表Service接口
 * 
 * @author xmap
 * @date 2023-11-03
 */
public interface IJgsParkingLotService 
{
    /**
     * 查询停车场信息表
     * 
     * @param id 停车场信息表主键
     * @return 停车场信息表
     */
    public JgsParkingLot selectJgsParkingLotById(Long id);

    /**
     * 查询停车场信息表列表
     * 
     * @param jgsParkingLot 停车场信息表
     * @return 停车场信息表集合
     */
    public List<JgsParkingLot> selectJgsParkingLotList(JgsParkingLot jgsParkingLot);

    /**
     * 新增停车场信息表
     * 
     * @param jgsParkingLot 停车场信息表
     * @return 结果
     */
    public int insertJgsParkingLot(JgsParkingLot jgsParkingLot);

    /**
     * 修改停车场信息表
     * 
     * @param jgsParkingLot 停车场信息表
     * @return 结果
     */
    public int updateJgsParkingLot(JgsParkingLot jgsParkingLot);

    /**
     * 批量删除停车场信息表
     * 
     * @param ids 需要删除的停车场信息表主键集合
     * @return 结果
     */
    public int deleteJgsParkingLotByIds(Long[] ids);

    /**
     * 删除停车场信息表信息
     * 
     * @param id 停车场信息表主键
     * @return 结果
     */
    public int deleteJgsParkingLotById(Long id);
}

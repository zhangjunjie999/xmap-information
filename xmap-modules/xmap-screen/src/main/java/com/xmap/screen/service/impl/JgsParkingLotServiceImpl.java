package com.xmap.screen.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.screen.mapper.JgsParkingLotMapper;
import com.xmap.screen.domain.JgsParkingLot;
import com.xmap.screen.service.IJgsParkingLotService;

/**
 * 停车场信息表Service业务层处理
 * 
 * @author xmap
 * @date 2023-11-03
 */
@Service
public class JgsParkingLotServiceImpl implements IJgsParkingLotService 
{
    @Autowired
    private JgsParkingLotMapper jgsParkingLotMapper;

    /**
     * 查询停车场信息表
     * 
     * @param id 停车场信息表主键
     * @return 停车场信息表
     */
    @Override
    public JgsParkingLot selectJgsParkingLotById(Long id)
    {
        return jgsParkingLotMapper.selectJgsParkingLotById(id);
    }

    /**
     * 查询停车场信息表列表
     * 
     * @param jgsParkingLot 停车场信息表
     * @return 停车场信息表
     */
    @Override
    public List<JgsParkingLot> selectJgsParkingLotList(JgsParkingLot jgsParkingLot)
    {
        return jgsParkingLotMapper.selectJgsParkingLotList(jgsParkingLot);
    }

    /**
     * 新增停车场信息表
     * 
     * @param jgsParkingLot 停车场信息表
     * @return 结果
     */
    @Override
    public int insertJgsParkingLot(JgsParkingLot jgsParkingLot)
    {
        return jgsParkingLotMapper.insertJgsParkingLot(jgsParkingLot);
    }

    /**
     * 修改停车场信息表
     * 
     * @param jgsParkingLot 停车场信息表
     * @return 结果
     */
    @Override
    public int updateJgsParkingLot(JgsParkingLot jgsParkingLot)
    {
        return jgsParkingLotMapper.updateJgsParkingLot(jgsParkingLot);
    }

    /**
     * 批量删除停车场信息表
     * 
     * @param ids 需要删除的停车场信息表主键
     * @return 结果
     */
    @Override
    public int deleteJgsParkingLotByIds(Long[] ids)
    {
        return jgsParkingLotMapper.deleteJgsParkingLotByIds(ids);
    }

    /**
     * 删除停车场信息表信息
     * 
     * @param id 停车场信息表主键
     * @return 结果
     */
    @Override
    public int deleteJgsParkingLotById(Long id)
    {
        return jgsParkingLotMapper.deleteJgsParkingLotById(id);
    }
}

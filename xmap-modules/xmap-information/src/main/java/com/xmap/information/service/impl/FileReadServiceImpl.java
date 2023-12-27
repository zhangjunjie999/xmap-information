package com.xmap.information.service.impl;

import java.util.List;
import com.xmap.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmap.information.mapper.FileReadMapper;
import com.xmap.information.domain.FileRead;
import com.xmap.information.service.IFileReadService;

/**
 * 文件解读列Service业务层处理
 * 
 * @author xmap
 * @date 2023-07-04
 */
@Service
public class FileReadServiceImpl implements IFileReadService 
{
    @Autowired
    private FileReadMapper fileReadMapper;

    /**
     * 查询文件解读列
     * 
     * @param docId 文件解读列主键
     * @return 文件解读列
     */
    @Override
    public FileRead selectFileReadByDocId(Long docId)
    {
        return fileReadMapper.selectFileReadByDocId(docId);
    }

    /**
     * 查询文件解读列列表
     * 
     * @param fileRead 文件解读列
     * @return 文件解读列
     */
    @Override
    public List<FileRead> selectFileReadList(FileRead fileRead)
    {
        return fileReadMapper.selectFileReadList(fileRead);
    }

    /**
     * 新增文件解读列
     * 
     * @param fileRead 文件解读列
     * @return 结果
     */
    @Override
    public int insertFileRead(FileRead fileRead)
    {
        fileRead.setCreateTime(DateUtils.getNowDate());
        return fileReadMapper.insertFileRead(fileRead);
    }

    /**
     * 修改文件解读列
     * 
     * @param fileRead 文件解读列
     * @return 结果
     */
    @Override
    public int updateFileRead(FileRead fileRead)
    {
        return fileReadMapper.updateFileRead(fileRead);
    }

    /**
     * 批量删除文件解读列
     * 
     * @param docIds 需要删除的文件解读列主键
     * @return 结果
     */
    @Override
    public int deleteFileReadByDocIds(Long[] docIds)
    {
        return fileReadMapper.deleteFileReadByDocIds(docIds);
    }

    /**
     * 删除文件解读列信息
     * 
     * @param docId 文件解读列主键
     * @return 结果
     */
    @Override
    public int deleteFileReadByDocId(Long docId)
    {
        return fileReadMapper.deleteFileReadByDocId(docId);
    }
}

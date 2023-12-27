package com.xmap.information.service;

import java.util.List;
import com.xmap.information.domain.FileRead;

/**
 * 文件解读列Service接口
 * 
 * @author xmap
 * @date 2023-07-04
 */
public interface IFileReadService 
{
    /**
     * 查询文件解读列
     * 
     * @param docId 文件解读列主键
     * @return 文件解读列
     */
    public FileRead selectFileReadByDocId(Long docId);

    /**
     * 查询文件解读列列表
     * 
     * @param fileRead 文件解读列
     * @return 文件解读列集合
     */
    public List<FileRead> selectFileReadList(FileRead fileRead);

    /**
     * 新增文件解读列
     * 
     * @param fileRead 文件解读列
     * @return 结果
     */
    public int insertFileRead(FileRead fileRead);

    /**
     * 修改文件解读列
     * 
     * @param fileRead 文件解读列
     * @return 结果
     */
    public int updateFileRead(FileRead fileRead);

    /**
     * 批量删除文件解读列
     * 
     * @param docIds 需要删除的文件解读列主键集合
     * @return 结果
     */
    public int deleteFileReadByDocIds(Long[] docIds);

    /**
     * 删除文件解读列信息
     * 
     * @param docId 文件解读列主键
     * @return 结果
     */
    public int deleteFileReadByDocId(Long docId);
}

import request from '@/utils/request'

// 查询文件解读列列表
export function listFile(query) {
  return request({
    url: '/information/file/list',
    method: 'get',
    params: query
  })
}

// 查询文件解读列详细
export function getFile(docId) {
  return request({
    url: '/information/file/' + docId,
    method: 'get'
  })
}

// 新增文件解读列
export function addFile(data) {
  return request({
    url: '/information/file',
    method: 'post',
    data: data
  })
}

// 修改文件解读列
export function updateFile(data) {
  return request({
    url: '/information/file',
    method: 'put',
    data: data
  })
}

// 删除文件解读列
export function delFile(docId) {
  return request({
    url: '/information/file/' + docId,
    method: 'delete'
  })
}

import request from '@/utils/request'

// 查询回复表列表
export function listReply(query) {
  return request({
    url: '/information/reply/list',
    method: 'get',
    params: query
  })
}

// 查询回复表详细
export function getReply(id) {
  return request({
    url: '/information/reply/' + id,
    method: 'get'
  })
}

// 新增回复表
export function addReply(data) {
  return request({
    url: '/information/reply',
    method: 'post',
    data: data
  })
}

// 修改回复表
export function updateReply(data) {
  return request({
    url: '/information/reply',
    method: 'put',
    data: data
  })
}

// 删除回复表
export function delReply(id) {
  return request({
    url: '/information/reply/' + id,
    method: 'delete'
  })
}

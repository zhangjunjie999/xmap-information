import request from '@/utils/request'

// 查询井冈山基本信息管理列表
export function listMessage(query) {
  return request({
    url: '/information/message/list',
    method: 'get',
    params: query
  })
}

// 查询井冈山基本信息管理详细
export function getMessage(id) {
  return request({
    url: '/information/message/' + id,
    method: 'get'
  })
}

// 新增井冈山基本信息管理
export function addMessage(data) {
  return request({
    url: '/information/message',
    method: 'post',
    data: data
  })
}

// 修改井冈山基本信息管理
export function updateMessage(data) {
  return request({
    url: '/information/message',
    method: 'put',
    data: data
  })
}

// 删除井冈山基本信息管理
export function delMessage(id) {
  return request({
    url: '/information/message/' + id,
    method: 'delete'
  })
}

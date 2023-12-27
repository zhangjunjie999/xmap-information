import request from '@/utils/request'

// 查询紧急插播管理列表
export function listUrgency(query) {
  return request({
    url: '/information/urgency/list',
    method: 'get',
    params: query
  })
}

// 查询紧急插播管理详细
export function getUrgency(id) {
  return request({
    url: '/information/urgency/' + id,
    method: 'get'
  })
}

// 新增紧急插播管理
export function addUrgency(data) {
  return request({
    url: '/information/urgency',
    method: 'post',
    data: data
  })
}

// 修改紧急插播管理
export function updateUrgency(data) {
  return request({
    url: '/information/urgency',
    method: 'put',
    data: data
  })
}

// 删除紧急插播管理
export function delUrgency(id) {
  return request({
    url: '/information/urgency/' + id,
    method: 'delete'
  })
}

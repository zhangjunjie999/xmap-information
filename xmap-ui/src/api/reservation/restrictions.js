import request from '@/utils/request'

// 查询预约限制管理列表
export function listRestrictions(query) {
  return request({
    url: '/reservation/restrictions/list',
    method: 'get',
    params: query
  })
}

// 查询预约限制管理详细
export function getRestrictions(restrictionId) {
  return request({
    url: '/reservation/restrictions/' + restrictionId,
    method: 'get'
  })
}

// 新增预约限制管理
export function addRestrictions(data) {
  return request({
    url: '/reservation/restrictions',
    method: 'post',
    data: data
  })
}

// 修改预约限制管理
export function updateRestrictions(data) {
  return request({
    url: '/reservation/restrictions',
    method: 'put',
    data: data
  })
}

// 删除预约限制管理
export function delRestrictions(restrictionId) {
  return request({
    url: '/reservation/restrictions/' + restrictionId,
    method: 'delete'
  })
}

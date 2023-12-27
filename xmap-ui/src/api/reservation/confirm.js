import request from '@/utils/request'

// 查询预约确认列表
export function listConfirm(query) {
  return request({
    url: '/reservation/confirm/list',
    method: 'get',
    params: query
  })
}

// 查询预约确认详细
export function getConfirm(confirmationId) {
  return request({
    url: '/reservation/confirm/' + confirmationId,
    method: 'get'
  })
}

// 新增预约确认
export function addConfirm(data) {
  return request({
    url: '/reservation/confirm',
    method: 'post',
    data: data
  })
}

// 修改预约确认
export function updateConfirm(data) {
  return request({
    url: '/reservation/confirm',
    method: 'put',
    data: data
  })
}

// 删除预约确认
export function delConfirm(confirmationId) {
  return request({
    url: '/reservation/confirm/' + confirmationId,
    method: 'delete'
  })
}

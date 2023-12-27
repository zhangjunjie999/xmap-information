import request from '@/utils/request'

// 查询预约信息列表
export function listRecord(query) {
  return request({
    url: '/reservation/record/list',
    method: 'get',
    params: query
  })
}

// 查询预约信息详细
export function getRecord(reservationId) {
  return request({
    url: '/reservation/record/' + reservationId,
    method: 'get'
  })
}

// 新增预约信息
export function addRecord(data) {
  return request({
    url: '/reservation/record',
    method: 'post',
    data: data
  })
}

// 修改预约信息
export function updateRecord(data) {
  return request({
    url: '/reservation/record',
    method: 'put',
    data: data
  })
}

// 删除预约信息
export function delRecord(reservationId) {
  return request({
    url: '/reservation/record/' + reservationId,
    method: 'delete'
  })
}

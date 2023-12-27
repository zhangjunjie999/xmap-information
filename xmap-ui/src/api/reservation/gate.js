import request from '@/utils/request'

// 查询门票预约列表
export function listGate(query) {
  return request({
    url: '/reservation/gate/list',
    method: 'get',
    params: query
  })
}

// 查询门票预约详细
export function getGate(reservationId) {
  return request({
    url: '/reservation/gate/' + reservationId,
    method: 'get'
  })
}

// 新增门票预约
export function addGate(data) {
  return request({
    url: '/reservation/gate',
    method: 'post',
    data: data
  })
}

// 修改门票预约
export function updateGate(data) {
  return request({
    url: '/reservation/gate',
    method: 'put',
    data: data
  })
}

// 删除门票预约
export function delGate(reservationId) {
  return request({
    url: '/reservation/gate/' + reservationId,
    method: 'delete'
  })
}

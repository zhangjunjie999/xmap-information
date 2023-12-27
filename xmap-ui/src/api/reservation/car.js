import request from '@/utils/request'

// 查询车辆预订列表
export function listCar(query) {
  return request({
    url: '/reservation/car/list',
    method: 'get',
    params: query
  })
}

// 查询车辆预订详细
export function getCar(reservationId) {
  return request({
    url: '/reservation/car/' + reservationId,
    method: 'get'
  })
}

// 新增车辆预订
export function addCar(data) {
  return request({
    url: '/reservation/car',
    method: 'post',
    data: data
  })
}

// 修改车辆预订
export function updateCar(data) {
  return request({
    url: '/reservation/car',
    method: 'put',
    data: data
  })
}

// 删除车辆预订
export function delCar(reservationId) {
  return request({
    url: '/reservation/car/' + reservationId,
    method: 'delete'
  })
}

import request from '@/utils/request'

// 查询餐饮预约列表
export function listRestaurant(query) {
  return request({
    url: '/reservation/restaurant/list',
    method: 'get',
    params: query
  })
}

// 查询餐饮预约详细
export function getRestaurant(reservationId) {
  return request({
    url: '/reservation/restaurant/' + reservationId,
    method: 'get'
  })
}

// 新增餐饮预约
export function addRestaurant(data) {
  return request({
    url: '/reservation/restaurant',
    method: 'post',
    data: data
  })
}

// 修改餐饮预约
export function updateRestaurant(data) {
  return request({
    url: '/reservation/restaurant',
    method: 'put',
    data: data
  })
}

// 删除餐饮预约
export function delRestaurant(reservationId) {
  return request({
    url: '/reservation/restaurant/' + reservationId,
    method: 'delete'
  })
}

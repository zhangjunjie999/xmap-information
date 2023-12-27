import request from '@/utils/request'

// 查询餐饮推荐列表
export function listRestaurant(query) {
  return request({
    url: '/commonservice/restaurant/list',
    method: 'get',
    params: query
  })
}

// 查询餐饮推荐详细
export function getRestaurant(restaurantId) {
  return request({
    url: '/commonservice/restaurant/' + restaurantId,
    method: 'get'
  })
}

// 新增餐饮推荐
export function addRestaurant(data) {
  return request({
    url: '/commonservice/restaurant',
    method: 'post',
    data: data
  })
}

// 修改餐饮推荐
export function updateRestaurant(data) {
  return request({
    url: '/commonservice/restaurant',
    method: 'put',
    data: data
  })
}

// 删除餐饮推荐
export function delRestaurant(restaurantId) {
  return request({
    url: '/commonservice/restaurant/' + restaurantId,
    method: 'delete'
  })
}

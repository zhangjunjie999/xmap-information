import request from '@/utils/request'

// 查询我的订单列表
export function listOrder(query) {
  return request({
    url: '/commonservice/order/list',
    method: 'get',
    params: query
  })
}

// 查询我的订单详细
export function getOrder(orderId) {
  return request({
    url: '/commonservice/order/' + orderId,
    method: 'get'
  })
}

// 新增我的订单
export function addOrder(data) {
  return request({
    url: '/commonservice/order',
    method: 'post',
    data: data
  })
}

// 修改我的订单
export function updateOrder(data) {
  return request({
    url: '/commonservice/order',
    method: 'put',
    data: data
  })
}

// 删除我的订单
export function delOrder(orderId) {
  return request({
    url: '/commonservice/order/' + orderId,
    method: 'delete'
  })
}

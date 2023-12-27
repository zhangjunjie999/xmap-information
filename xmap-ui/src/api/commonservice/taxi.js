import request from '@/utils/request'

// 查询出租车信息管理列表
export function listTaxi(query) {
  return request({
    url: '/commonservice/taxi/list',
    method: 'get',
    params: query
  })
}

// 查询出租车信息管理详细
export function getTaxi(taxiId) {
  return request({
    url: '/commonservice/taxi/' + taxiId,
    method: 'get'
  })
}

// 新增出租车信息管理
export function addTaxi(data) {
  return request({
    url: '/commonservice/taxi',
    method: 'post',
    data: data
  })
}

// 修改出租车信息管理
export function updateTaxi(data) {
  return request({
    url: '/commonservice/taxi',
    method: 'put',
    data: data
  })
}

// 删除出租车信息管理
export function delTaxi(taxiId) {
  return request({
    url: '/commonservice/taxi/' + taxiId,
    method: 'delete'
  })
}

import request from '@/utils/request'

// 查询运营位服务列表
export function listOperating(query) {
  return request({
    url: '/commonservice/operating/list',
    method: 'get',
    params: query
  })
}

// 查询运营位服务详细
export function getOperating(operatingId) {
  return request({
    url: '/commonservice/operating/' + operatingId,
    method: 'get'
  })
}

// 新增运营位服务
export function addOperating(data) {
  return request({
    url: '/commonservice/operating',
    method: 'post',
    data: data
  })
}

// 修改运营位服务
export function updateOperating(data) {
  return request({
    url: '/commonservice/operating',
    method: 'put',
    data: data
  })
}

// 删除运营位服务
export function delOperating(operatingId) {
  return request({
    url: '/commonservice/operating/' + operatingId,
    method: 'delete'
  })
}

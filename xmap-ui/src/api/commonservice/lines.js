import request from '@/utils/request'

// 查询线路规划列表
export function listLines(query) {
  return request({
    url: '/commonservice/lines/list',
    method: 'get',
    params: query
  })
}

// 查询线路规划详细
export function getLines(routeId) {
  return request({
    url: '/commonservice/lines/' + routeId,
    method: 'get'
  })
}

// 新增线路规划
export function addLines(data) {
  return request({
    url: '/commonservice/lines',
    method: 'post',
    data: data
  })
}

// 修改线路规划
export function updateLines(data) {
  return request({
    url: '/commonservice/lines',
    method: 'put',
    data: data
  })
}

// 删除线路规划
export function delLines(routeId) {
  return request({
    url: '/commonservice/lines/' + routeId,
    method: 'delete'
  })
}

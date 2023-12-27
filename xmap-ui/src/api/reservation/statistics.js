import request from '@/utils/request'

// 查询预约数据统计列表
export function listStatistics(query) {
  return request({
    url: '/reservation/statistics/list',
    method: 'get',
    params: query
  })
}

// 查询预约数据统计详细
export function getStatistics(statisticId) {
  return request({
    url: '/reservation/statistics/' + statisticId,
    method: 'get'
  })
}

// 新增预约数据统计
export function addStatistics(data) {
  return request({
    url: '/reservation/statistics',
    method: 'post',
    data: data
  })
}

// 修改预约数据统计
export function updateStatistics(data) {
  return request({
    url: '/reservation/statistics',
    method: 'put',
    data: data
  })
}

// 删除预约数据统计
export function delStatistics(statisticId) {
  return request({
    url: '/reservation/statistics/' + statisticId,
    method: 'delete'
  })
}

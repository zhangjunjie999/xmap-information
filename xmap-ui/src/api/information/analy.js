import request from '@/utils/request'

// 查询统计分析列表
export function listAnaly(query) {
  return request({
    url: '/information/analy/list',
    method: 'get',
    params: query
  })
}

// 查询统计分析详细
export function getAnaly(statisticsId) {
  return request({
    url: '/information/analy/' + statisticsId,
    method: 'get'
  })
}

// 新增统计分析
export function addAnaly(data) {
  return request({
    url: '/information/analy',
    method: 'post',
    data: data
  })
}

// 修改统计分析
export function updateAnaly(data) {
  return request({
    url: '/information/analy',
    method: 'put',
    data: data
  })
}

// 删除统计分析
export function delAnaly(statisticsId) {
  return request({
    url: '/information/analy/' + statisticsId,
    method: 'delete'
  })
}

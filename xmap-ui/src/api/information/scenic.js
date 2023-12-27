import request from '@/utils/request'

// 查询景点引导牌管理列表
export function listScenic(query) {
  return request({
    url: '/information/scenic/list',
    method: 'get',
    params: query
  })
}

// 查询景点引导牌管理详细
export function getScenic(id) {
  return request({
    url: '/information/scenic/' + id,
    method: 'get'
  })
}

// 新增景点引导牌管理
export function addScenic(data) {
  return request({
    url: '/information/scenic',
    method: 'post',
    data: data
  })
}

// 修改景点引导牌管理
export function updateScenic(data) {
  return request({
    url: '/information/scenic',
    method: 'put',
    data: data
  })
}

// 删除景点引导牌管理
export function delScenic(id) {
  return request({
    url: '/information/scenic/' + id,
    method: 'delete'
  })
}

export function weatherParking() {
  return request({
    url: '/information/parking/weather',
    method: 'get',
    // params: query
  })
}
// 查询数据库中存储的天气数据
export function getWeather(query) {
  return request({
    url: '/information/weather/list',
    method: 'get',
    params: query
  })
}

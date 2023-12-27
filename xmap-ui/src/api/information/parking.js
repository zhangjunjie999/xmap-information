import request from '@/utils/request'

// 查询停车场引导牌管理列表
export function listParking(query) {
  return request({
    url: '/information/parking/list',
    method: 'get',
    params: query
  })
}

// 查询停车场引导牌管理详细
export function getParking(id) {
  return request({
    url: '/information/parking/' + id,
    method: 'get'
  })
}

// 新增停车场引导牌管理
export function addParking(data) {
  return request({
    url: '/information/parking',
    method: 'post',
    data: data
  })
}

// 修改停车场引导牌管理
export function updateParking(data) {
  return request({
    url: '/information/parking',
    method: 'put',
    data: data
  })
}

// 删除停车场引导牌管理
export function delParking(id) {
  return request({
    url: '/information/parking/' + id,
    method: 'delete'
  })
}

export function previewTable(id) {
  return request({
    url: '/code/gen/preview/' + id,
    method: 'get'
  })

  
}

// 查询停车场引导牌天气
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


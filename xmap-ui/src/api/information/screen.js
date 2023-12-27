import request from '@/utils/request'

// 查询大屏管理列表
export function listScreen(query) {
  return request({
    url: '/information/screen/list',
    method: 'get',
    params: query
  })
}

// 查询大屏管理详细
export function getScreen(id) {
  return request({
    url: '/information/screen/' + id,
    method: 'get'
  })
}

// 新增大屏管理
export function addScreen(data) {
  return request({
    url: '/information/screen',
    method: 'post',
    data: data
  })
}

// 修改大屏管理
export function updateScreen(data) {
  return request({
    url: '/information/screen',
    method: 'put',
    data: data
  })
}

// 删除大屏管理
export function delScreen(id) {
  return request({
    url: '/information/screen/' + id,
    method: 'delete'
  })
}

import request from '@/utils/request'

// 查询地点标志管理列表
export function listSite(query) {
  return request({
    url: '/information/site/list',
    method: 'get',
    params: query
  })
}

// 查询地点标志管理详细
export function getSite(id) {
  return request({
    url: '/information/site/' + id,
    method: 'get'
  })
}

// 新增地点标志管理
export function addSite(data) {
  return request({
    url: '/information/site',
    method: 'post',
    data: data
  })
}

// 修改地点标志管理
export function updateSite(data) {
  return request({
    url: '/information/site',
    method: 'put',
    data: data
  })
}

// 删除地点标志管理
export function delSite(id) {
  return request({
    url: '/information/site/' + id,
    method: 'delete'
  })
}

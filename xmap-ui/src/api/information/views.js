import request from '@/utils/request'

// 查询景点类型管理列表
export function listViews(query) {
  return request({
    url: '/information/views/list',
    method: 'get',
    params: query
  })
}

// 查询景点类型管理详细
export function getViews(id) {
  return request({
    url: '/information/views/' + id,
    method: 'get'
  })
}

// 新增景点类型管理
export function addViews(data) {
  return request({
    url: '/information/views',
    method: 'post',
    data: data
  })
}

// 修改景点类型管理
export function updateViews(data) {
  return request({
    url: '/information/views',
    method: 'put',
    data: data
  })
}

// 删除景点类型管理
export function delViews(id) {
  return request({
    url: '/information/views/' + id,
    method: 'delete'
  })
}

export function listSite(query) {
  return request({
    url: '/information/site/list',
    method: 'get',
    params: query
  })
}

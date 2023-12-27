import request from '@/utils/request'

// 查询内容发布景点引导牌管理列表
export function listView(query) {
  return request({
    url: '/information/view/list',
    method: 'get',
    params: query
  })
}

// 查询内容发布景点引导牌管理详细
export function getView(id) {
  return request({
    url: '/information/view/' + id,
    method: 'get'
  })
}

// 新增内容发布景点引导牌管理
export function addView(data) {
  return request({
    url: '/information/view',
    method: 'post',
    data: data
  })
}

// 修改内容发布景点引导牌管理
export function updateView(data) {
  return request({
    url: '/information/view',
    method: 'put',
    data: data
  })
}

// 删除内容发布景点引导牌管理
export function delView(id) {
  return request({
    url: '/information/view/' + id,
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

export function getUserProfile() {
  return request({
    url: '/system/user/profile',
    method: 'get'
  })
}

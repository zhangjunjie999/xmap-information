import request from '@/utils/request'

// 查询景点引导牌列表
export function listScenic1(query) {
  return request({
    url: '/information/scenic1/list',
    method: 'get',
    params: query
  })
}

// 查询景点引导牌详细
export function getScenic1(id) {
  return request({
    url: '/information/scenic1/' + id,
    method: 'get'
  })
}

// 新增景点引导牌
export function addScenic1(data) {
  return request({
    url: '/information/scenic1',
    method: 'post',
    data: data
  })
}

// 修改景点引导牌
export function updateScenic1(data) {
  return request({
    url: '/information/scenic1',
    method: 'put',
    data: data
  })
}

// 删除景点引导牌
export function delScenic1(id) {
  return request({
    url: '/information/scenic1/' + id,
    method: 'delete'
  })
}
export function listUser(query) {
  return request({
    url: '/system/user/list',
    method: 'get',
    params: query
  })
}


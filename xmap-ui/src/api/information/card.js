import request from '@/utils/request'

// 查询内容发布引导牌管理列表
export function listCard(query) {
  return request({
    url: '/information/card/list',
    method: 'get',
    params: query
  })
}

// 查询内容发布引导牌管理详细
export function getCard(id) {
  return request({
    url: '/information/card/' + id,
    method: 'get'
  })
}

// 新增内容发布引导牌管理
export function addCard(data) {
  return request({
    url: '/information/card',
    method: 'post',
    data: data
  })
}

// 修改内容发布引导牌管理
export function updateCard(data) {
  return request({
    url: '/information/card',
    method: 'put',
    data: data
  })
}

// 删除内容发布引导牌管理
export function delCard(id) {
  return request({
    url: '/information/card/' + id,
    method: 'delete'
  })
}
// 获取停车场引导牌类型名称
export function listType(query) {
  return request({
    url: '/information/type/list',
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


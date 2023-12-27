import request from '@/utils/request'

// 查询微信管理列表
export function listWechat(query) {
  return request({
    url: '/information/wechat/list',
    method: 'get',
    params: query
  })
}

// 查询微信管理详细
export function getWechat(id) {
  return request({
    url: '/information/wechat/' + id,
    method: 'get'
  })
}

// 新增微信管理
export function addWechat(data) {
  return request({
    url: '/information/wechat',
    method: 'post',
    data: data
  })
}

// 修改微信管理
export function updateWechat(data) {
  return request({
    url: '/information/wechat',
    method: 'put',
    data: data
  })
}

// 删除微信管理
export function delWechat(id) {
  return request({
    url: '/information/wechat/' + id,
    method: 'delete'
  })
}

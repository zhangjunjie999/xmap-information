import request from '@/utils/request'

// 查询游客建议列表
export function listSuggest(query) {
  return request({
    url: '/information/suggest/list',
    method: 'get',
    params: query
  })
}

// 查询游客建议详细
export function getSuggest(id) {
  return request({
    url: '/information/suggest/' + id,
    method: 'get'
  })
}

// 新增游客建议
export function addSuggest(data) {
  return request({
    url: '/information/suggest',
    method: 'post',
    data: data
  })
}

// 修改游客建议
export function updateSuggest(data) {
  return request({
    url: '/information/suggest',
    method: 'put',
    data: data
  })
}

// 删除游客建议
export function delSuggest(id) {
  return request({
    url: '/information/suggest/' + id,
    method: 'delete'
  })
}

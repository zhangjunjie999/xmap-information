import request from '@/utils/request'

// 查询招商信息列列表
export function listInvitation(query) {
  return request({
    url: '/information/invitation/list',
    method: 'get',
    params: query
  })
}

// 查询招商信息列详细
export function getInvitation(investmentId) {
  return request({
    url: '/information/invitation/' + investmentId,
    method: 'get'
  })
}

// 新增招商信息列
export function addInvitation(data) {
  return request({
    url: '/information/invitation',
    method: 'post',
    data: data
  })
}

// 修改招商信息列
export function updateInvitation(data) {
  return request({
    url: '/information/invitation',
    method: 'put',
    data: data
  })
}

// 删除招商信息列
export function delInvitation(investmentId) {
  return request({
    url: '/information/invitation/' + investmentId,
    method: 'delete'
  })
}

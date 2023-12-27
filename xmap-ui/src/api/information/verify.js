import request from '@/utils/request'

// 查询审核管理列列表
export function listVerify(query) {
  return request({
    url: '/information/verify/list',
    method: 'get',
    params: query
  })
}

// 查询审核管理列详细
export function getVerify(id) {
  return request({
    url: '/information/verify/' + id,
    method: 'get'
  })
}

// 新增审核管理列
export function addVerify(data) {
  return request({
    url: '/information/verify',
    method: 'post',
    data: data
  })
}

// 修改审核管理列
export function updateVerify(data) {
  return request({
    url: '/information/verify',
    method: 'put',
    data: data
  })
}

// 删除审核管理列
export function delVerify(id) {
  return request({
    url: '/information/verify/' + id,
    method: 'delete'
  })
}

export function listCard(query) {
  return request({
    url: '/information/card/list',
    method: 'get',
    params: query
  })
}


import request from '@/utils/request'

// 查询权限管理列表
export function listPermission(query) {
  return request({
    url: '/information/permission/list',
    method: 'get',
    params: query
  })
}

// 查询权限管理详细
export function getPermission(id) {
  return request({
    url: '/information/permission/' + id,
    method: 'get'
  })
}

// 新增权限管理
export function addPermission(data) {
  return request({
    url: '/information/permission',
    method: 'post',
    data: data
  })
}

// 修改权限管理
export function updatePermission(data) {
  return request({
    url: '/information/permission',
    method: 'put',
    data: data
  })
}

// 删除权限管理
export function delPermission(id) {
  return request({
    url: '/information/permission/' + id,
    method: 'delete'
  })
}

import request from '@/utils/request'

// 查询工作动态列列表
export function listWork(query) {
  return request({
    url: '/information/work/list',
    method: 'get',
    params: query
  })
}

// 查询工作动态列详细
export function getWork(dynamicId) {
  return request({
    url: '/information/work/' + dynamicId,
    method: 'get'
  })
}

// 新增工作动态列
export function addWork(data) {
  return request({
    url: '/information/work',
    method: 'post',
    data: data
  })
}

// 修改工作动态列
export function updateWork(data) {
  return request({
    url: '/information/work',
    method: 'put',
    data: data
  })
}

// 删除工作动态列
export function delWork(dynamicId) {
  return request({
    url: '/information/work/' + dynamicId,
    method: 'delete'
  })
}

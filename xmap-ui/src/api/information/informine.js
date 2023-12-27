import request from '@/utils/request'

// 查询资讯管理列表
export function listInformine(query) {
  return request({
    url: '/information/informine/list',
    method: 'get',
    params: query
  })
}

// 查询资讯管理详细
export function getInformine(id) {
  return request({
    url: '/information/informine/' + id,
    method: 'get'
  })
}

// 新增资讯管理
export function addInformine(data) {
  return request({
    url: '/information/informine',
    method: 'post',
    data: data
  })
}

// 修改资讯管理
export function updateInformine(data) {
  return request({
    url: '/information/informine',
    method: 'put',
    data: data
  })
}

// 删除资讯管理
export function delInformine(id) {
  return request({
    url: '/information/informine/' + id,
    method: 'delete'
  })
}

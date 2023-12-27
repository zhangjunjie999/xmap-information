import request from '@/utils/request'

// 查询观光车信息管理列表
export function listTourbus(query) {
  return request({
    url: '/commonservice/tourbus/list',
    method: 'get',
    params: query
  })
}

// 查询观光车信息管理详细
export function getTourbus(tourbusId) {
  return request({
    url: '/commonservice/tourbus/' + tourbusId,
    method: 'get'
  })
}

// 新增观光车信息管理
export function addTourbus(data) {
  return request({
    url: '/commonservice/tourbus',
    method: 'post',
    data: data
  })
}

// 修改观光车信息管理
export function updateTourbus(data) {
  return request({
    url: '/commonservice/tourbus',
    method: 'put',
    data: data
  })
}

// 删除观光车信息管理
export function delTourbus(tourbusId) {
  return request({
    url: '/commonservice/tourbus/' + tourbusId,
    method: 'delete'
  })
}

import request from '@/utils/request'

// 查询办事指南列表
export function listGuidance(query) {
  return request({
    url: '/commonservice/guidance/list',
    method: 'get',
    params: query
  })
}

// 查询办事指南详细
export function getGuidance(guidanceId) {
  return request({
    url: '/commonservice/guidance/' + guidanceId,
    method: 'get'
  })
}

// 新增办事指南
export function addGuidance(data) {
  return request({
    url: '/commonservice/guidance',
    method: 'post',
    data: data
  })
}

// 修改办事指南
export function updateGuidance(data) {
  return request({
    url: '/commonservice/guidance',
    method: 'put',
    data: data
  })
}

// 删除办事指南
export function delGuidance(guidanceId) {
  return request({
    url: '/commonservice/guidance/' + guidanceId,
    method: 'delete'
  })
}

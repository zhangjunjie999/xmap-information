import request from '@/utils/request'

// 查询入驻审批 列表
export function listApproval(query) {
  return request({
    url: '/commercial/approval/list',
    method: 'get',
    params: query
  })
}

// 查询入驻审批 详细
export function getApproval(merchantId) {
  return request({
    url: '/commercial/approval/' + merchantId,
    method: 'get'
  })
}

// 新增入驻审批 
export function addApproval(data) {
  return request({
    url: '/commercial/approval',
    method: 'post',
    data: data
  })
}

// 修改入驻审批 
export function updateApproval(data) {
  return request({
    url: '/commercial/approval',
    method: 'put',
    data: data
  })
}

// 删除入驻审批 
export function delApproval(merchantId) {
  return request({
    url: '/commercial/approval/' + merchantId,
    method: 'delete'
  })
}

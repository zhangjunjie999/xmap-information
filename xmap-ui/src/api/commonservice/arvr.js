import request from '@/utils/request'

// 查询增强现实虚拟现实列表
export function listArvr(query) {
  return request({
    url: '/commonservice/arvr/list',
    method: 'get',
    params: query
  })
}

// 查询增强现实虚拟现实详细
export function getArvr(serviceId) {
  return request({
    url: '/commonservice/arvr/' + serviceId,
    method: 'get'
  })
}

// 新增增强现实虚拟现实
export function addArvr(data) {
  return request({
    url: '/commonservice/arvr',
    method: 'post',
    data: data
  })
}

// 修改增强现实虚拟现实
export function updateArvr(data) {
  return request({
    url: '/commonservice/arvr',
    method: 'put',
    data: data
  })
}

// 删除增强现实虚拟现实
export function delArvr(serviceId) {
  return request({
    url: '/commonservice/arvr/' + serviceId,
    method: 'delete'
  })
}

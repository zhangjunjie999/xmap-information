import request from '@/utils/request'

// 查询医疗服务机构管理列表
export function listHealthcare(query) {
  return request({
    url: '/commonservice/healthcare/list',
    method: 'get',
    params: query
  })
}

// 查询医疗服务机构管理详细
export function getHealthcare(medicalId) {
  return request({
    url: '/commonservice/healthcare/' + medicalId,
    method: 'get'
  })
}

// 新增医疗服务机构管理
export function addHealthcare(data) {
  return request({
    url: '/commonservice/healthcare',
    method: 'post',
    data: data
  })
}

// 修改医疗服务机构管理
export function updateHealthcare(data) {
  return request({
    url: '/commonservice/healthcare',
    method: 'put',
    data: data
  })
}

// 删除医疗服务机构管理
export function delHealthcare(medicalId) {
  return request({
    url: '/commonservice/healthcare/' + medicalId,
    method: 'delete'
  })
}

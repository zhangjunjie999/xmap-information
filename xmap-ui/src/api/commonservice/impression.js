import request from '@/utils/request'

// 查询景区印象列表
export function listImpression(query) {
  return request({
    url: '/commonservice/impression/list',
    method: 'get',
    params: query
  })
}

// 查询景区印象详细
export function getImpression(areaId) {
  return request({
    url: '/commonservice/impression/' + areaId,
    method: 'get'
  })
}

// 新增景区印象
export function addImpression(data) {
  return request({
    url: '/commonservice/impression',
    method: 'post',
    data: data
  })
}

// 修改景区印象
export function updateImpression(data) {
  return request({
    url: '/commonservice/impression',
    method: 'put',
    data: data
  })
}

// 删除景区印象
export function delImpression(areaId) {
  return request({
    url: '/commonservice/impression/' + areaId,
    method: 'delete'
  })
}

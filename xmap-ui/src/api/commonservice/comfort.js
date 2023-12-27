import request from '@/utils/request'

// 查询旅游舒适度列表
export function listComfort(query) {
  return request({
    url: '/commonservice/comfort/list',
    method: 'get',
    params: query
  })
}

// 查询旅游舒适度详细
export function getComfort(comfortId) {
  return request({
    url: '/commonservice/comfort/' + comfortId,
    method: 'get'
  })
}

// 新增旅游舒适度
export function addComfort(data) {
  return request({
    url: '/commonservice/comfort',
    method: 'post',
    data: data
  })
}

// 修改旅游舒适度
export function updateComfort(data) {
  return request({
    url: '/commonservice/comfort',
    method: 'put',
    data: data
  })
}

// 删除旅游舒适度
export function delComfort(comfortId) {
  return request({
    url: '/commonservice/comfort/' + comfortId,
    method: 'delete'
  })
}

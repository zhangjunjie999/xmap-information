import request from '@/utils/request'

// 查询景点介绍列表
export function listAttractions(query) {
  return request({
    url: '/commonservice/attractions/list',
    method: 'get',
    params: query
  })
}

// 查询景点介绍详细
export function getAttractions(activityId) {
  return request({
    url: '/commonservice/attractions/' + activityId,
    method: 'get'
  })
}

// 新增景点介绍
export function addAttractions(data) {
  return request({
    url: '/commonservice/attractions',
    method: 'post',
    data: data
  })
}

// 修改景点介绍
export function updateAttractions(data) {
  return request({
    url: '/commonservice/attractions',
    method: 'put',
    data: data
  })
}

// 删除景点介绍
export function delAttractions(activityId) {
  return request({
    url: '/commonservice/attractions/' + activityId,
    method: 'delete'
  })
}

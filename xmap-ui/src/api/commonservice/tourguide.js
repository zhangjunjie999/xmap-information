import request from '@/utils/request'

// 查询导游导览列表
export function listTourguide(query) {
  return request({
    url: '/commonservice/tourguide/list',
    method: 'get',
    params: query
  })
}

// 查询导游导览详细
export function getTourguide(scenicAreaId) {
  return request({
    url: '/commonservice/tourguide/' + scenicAreaId,
    method: 'get'
  })
}

// 新增导游导览
export function addTourguide(data) {
  return request({
    url: '/commonservice/tourguide',
    method: 'post',
    data: data
  })
}

// 修改导游导览
export function updateTourguide(data) {
  return request({
    url: '/commonservice/tourguide',
    method: 'put',
    data: data
  })
}

// 删除导游导览
export function delTourguide(scenicAreaId) {
  return request({
    url: '/commonservice/tourguide/' + scenicAreaId,
    method: 'delete'
  })
}

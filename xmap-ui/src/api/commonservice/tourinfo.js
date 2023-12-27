import request from '@/utils/request'

// 查询景点信息管理列表
export function listTourinfo(query) {
  return request({
    url: '/commonservice/tourinfo/list',
    method: 'get',
    params: query
  })
}

// 查询景点信息管理详细
export function getTourinfo(scenicAreaId) {
  return request({
    url: '/commonservice/tourinfo/' + scenicAreaId,
    method: 'get'
  })
}

// 新增景点信息管理
export function addTourinfo(data) {
  return request({
    url: '/commonservice/tourinfo',
    method: 'post',
    data: data
  })
}

// 修改景点信息管理
export function updateTourinfo(data) {
  return request({
    url: '/commonservice/tourinfo',
    method: 'put',
    data: data
  })
}

// 删除景点信息管理
export function delTourinfo(scenicAreaId) {
  return request({
    url: '/commonservice/tourinfo/' + scenicAreaId,
    method: 'delete'
  })
}

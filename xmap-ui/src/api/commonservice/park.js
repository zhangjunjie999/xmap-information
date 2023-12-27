import request from '@/utils/request'

// 查询停车场信息列表
export function listPark(query) {
  return request({
    url: '/commonservice/park/list',
    method: 'get',
    params: query
  })
}

// 查询停车场信息详细
export function getPark(parkingLotId) {
  return request({
    url: '/commonservice/park/' + parkingLotId,
    method: 'get'
  })
}

// 新增停车场信息
export function addPark(data) {
  return request({
    url: '/commonservice/park',
    method: 'post',
    data: data
  })
}

// 修改停车场信息
export function updatePark(data) {
  return request({
    url: '/commonservice/park',
    method: 'put',
    data: data
  })
}

// 删除停车场信息
export function delPark(parkingLotId) {
  return request({
    url: '/commonservice/park/' + parkingLotId,
    method: 'delete'
  })
}

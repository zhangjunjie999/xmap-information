import request from '@/utils/request'

// 查询停车场预约列表
export function listParkinglot(query) {
  return request({
    url: '/reservation/parkinglot/list',
    method: 'get',
    params: query
  })
}

// 查询停车场预约详细
export function getParkinglot(attractionId) {
  return request({
    url: '/reservation/parkinglot/' + attractionId,
    method: 'get'
  })
}

// 新增停车场预约
export function addParkinglot(data) {
  return request({
    url: '/reservation/parkinglot',
    method: 'post',
    data: data
  })
}

// 修改停车场预约
export function updateParkinglot(data) {
  return request({
    url: '/reservation/parkinglot',
    method: 'put',
    data: data
  })
}

// 删除停车场预约
export function delParkinglot(attractionId) {
  return request({
    url: '/reservation/parkinglot/' + attractionId,
    method: 'delete'
  })
}

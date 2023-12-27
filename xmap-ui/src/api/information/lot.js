import request from '@/utils/request'

// 查询停车场信息表列表
export function listLot(query) {
  return request({
    url: '/information/lot/list',
    method: 'get',
    params: query
  })
}

// 查询停车场信息表详细
export function getLot(id) {
  return request({
    url: '/information/lot/' + id,
    method: 'get'
  })
}

// 新增停车场信息表
export function addLot(data) {
  return request({
    url: '/information/lot',
    method: 'post',
    data: data
  })
}

// 修改停车场信息表
export function updateLot(data) {
  return request({
    url: '/information/lot',
    method: 'put',
    data: data
  })
}

// 删除停车场信息表
export function delLot(id) {
  return request({
    url: '/information/lot/' + id,
    method: 'delete'
  })
}

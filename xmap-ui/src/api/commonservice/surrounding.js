import request from '@/utils/request'

// 查询景区周边列表
export function listSurrounding(query) {
  return request({
    url: '/commonservice/surrounding/list',
    method: 'get',
    params: query
  })
}

// 查询景区周边详细
export function getSurrounding(surroundId) {
  return request({
    url: '/commonservice/surrounding/' + surroundId,
    method: 'get'
  })
}

// 新增景区周边
export function addSurrounding(data) {
  return request({
    url: '/commonservice/surrounding',
    method: 'post',
    data: data
  })
}

// 修改景区周边
export function updateSurrounding(data) {
  return request({
    url: '/commonservice/surrounding',
    method: 'put',
    data: data
  })
}

// 删除景区周边
export function delSurrounding(surroundId) {
  return request({
    url: '/commonservice/surrounding/' + surroundId,
    method: 'delete'
  })
}

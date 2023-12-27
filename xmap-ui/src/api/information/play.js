import request from '@/utils/request'

// 查询玩转井冈山列表
export function listPlay(query) {
  return request({
    url: '/information/play/list',
    method: 'get',
    params: query
  })
}

// 查询玩转井冈山详细
export function getPlay(id) {
  return request({
    url: '/information/play/' + id,
    method: 'get'
  })
}

// 新增玩转井冈山
export function addPlay(data) {
  return request({
    url: '/information/play',
    method: 'post',
    data: data
  })
}

// 修改玩转井冈山
export function updatePlay(data) {
  return request({
    url: '/information/play',
    method: 'put',
    data: data
  })
}

// 删除玩转井冈山
export function delPlay(id) {
  return request({
    url: '/information/play/' + id,
    method: 'delete'
  })
}

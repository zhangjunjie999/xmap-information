import request from '@/utils/request'

// 查询玩转井冈山列表
export function listPlay(query) {
  return request({
    url: '/commonservice/play/list',
    method: 'get',
    params: query
  })
}

// 查询玩转井冈山详细
export function getPlay(playId) {
  return request({
    url: '/commonservice/play/' + playId,
    method: 'get'
  })
}

// 新增玩转井冈山
export function addPlay(data) {
  return request({
    url: '/commonservice/play',
    method: 'post',
    data: data
  })
}

// 修改玩转井冈山
export function updatePlay(data) {
  return request({
    url: '/commonservice/play',
    method: 'put',
    data: data
  })
}

// 删除玩转井冈山
export function delPlay(playId) {
  return request({
    url: '/commonservice/play/' + playId,
    method: 'delete'
  })
}

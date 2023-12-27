import request from '@/utils/request'

// 查询导游预约管理列表
export function listGuider(query) {
  return request({
    url: '/reservation/guider/list',
    method: 'get',
    params: query
  })
}

// 查询导游预约管理详细
export function getGuider(appointmentId) {
  return request({
    url: '/reservation/guider/' + appointmentId,
    method: 'get'
  })
}

// 新增导游预约管理
export function addGuider(data) {
  return request({
    url: '/reservation/guider',
    method: 'post',
    data: data
  })
}

// 修改导游预约管理
export function updateGuider(data) {
  return request({
    url: '/reservation/guider',
    method: 'put',
    data: data
  })
}

// 删除导游预约管理
export function delGuider(appointmentId) {
  return request({
    url: '/reservation/guider/' + appointmentId,
    method: 'delete'
  })
}

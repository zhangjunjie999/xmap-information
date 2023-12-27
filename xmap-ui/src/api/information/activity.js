import request from '@/utils/request'

// 查询活动盛事管理列表
export function listActivity(query) {
  return request({
    url: '/information/activity/list',
    method: 'get',
    params: query
  })
}

// 查询活动盛事管理详细
export function getActivity(id) {
  return request({
    url: '/information/activity/' + id,
    method: 'get'
  })
}

// 新增活动盛事管理
export function addActivity(data) {
  return request({
    url: '/information/activity',
    method: 'post',
    data: data
  })
}

// 修改活动盛事管理
export function updateActivity(data) {
  return request({
    url: '/information/activity',
    method: 'put',
    data: data
  })
}

// 删除活动盛事管理
export function delActivity(id) {
  return request({
    url: '/information/activity/' + id,
    method: 'delete'
  })
}

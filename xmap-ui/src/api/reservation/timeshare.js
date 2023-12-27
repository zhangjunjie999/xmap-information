import request from '@/utils/request'

// 查询分时预约列表
export function listTimeshare(query) {
  return request({
    url: '/reservation/timeshare/list',
    method: 'get',
    params: query
  })
}

// 查询分时预约详细
export function getTimeshare(timeSlotId) {
  return request({
    url: '/reservation/timeshare/' + timeSlotId,
    method: 'get'
  })
}

// 新增分时预约
export function addTimeshare(data) {
  return request({
    url: '/reservation/timeshare',
    method: 'post',
    data: data
  })
}

// 修改分时预约
export function updateTimeshare(data) {
  return request({
    url: '/reservation/timeshare',
    method: 'put',
    data: data
  })
}

// 删除分时预约
export function delTimeshare(timeSlotId) {
  return request({
    url: '/reservation/timeshare/' + timeSlotId,
    method: 'delete'
  })
}

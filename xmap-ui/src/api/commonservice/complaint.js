import request from '@/utils/request'

// 查询我的投诉列表
export function listComplaint(query) {
  return request({
    url: '/commonservice/complaint/list',
    method: 'get',
    params: query
  })
}

// 查询我的投诉详细
export function getComplaint(complaintId) {
  return request({
    url: '/commonservice/complaint/' + complaintId,
    method: 'get'
  })
}

// 新增我的投诉
export function addComplaint(data) {
  return request({
    url: '/commonservice/complaint',
    method: 'post',
    data: data
  })
}

// 修改我的投诉
export function updateComplaint(data) {
  return request({
    url: '/commonservice/complaint',
    method: 'put',
    data: data
  })
}

// 删除我的投诉
export function delComplaint(complaintId) {
  return request({
    url: '/commonservice/complaint/' + complaintId,
    method: 'delete'
  })
}

import request from '@/utils/request'

// 查询在线客服反馈列表
export function listFeedback(query) {
  return request({
    url: '/commonservice/feedback/list',
    method: 'get',
    params: query
  })
}

// 查询在线客服反馈详细
export function getFeedback(feedbackId) {
  return request({
    url: '/commonservice/feedback/' + feedbackId,
    method: 'get'
  })
}

// 新增在线客服反馈
export function addFeedback(data) {
  return request({
    url: '/commonservice/feedback',
    method: 'post',
    data: data
  })
}

// 修改在线客服反馈
export function updateFeedback(data) {
  return request({
    url: '/commonservice/feedback',
    method: 'put',
    data: data
  })
}

// 删除在线客服反馈
export function delFeedback(feedbackId) {
  return request({
    url: '/commonservice/feedback/' + feedbackId,
    method: 'delete'
  })
}

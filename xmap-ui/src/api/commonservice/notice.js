import request from '@/utils/request'

// 查询通知公告列表
export function listNotice(query) {
  return request({
    url: '/commonservice/notice/list',
    method: 'get',
    params: query
  })
}

// 查询通知公告详细
export function getNotice(noticeId) {
  return request({
    url: '/commonservice/notice/' + noticeId,
    method: 'get'
  })
}

// 新增通知公告
export function addNotice(data) {
  return request({
    url: '/commonservice/notice',
    method: 'post',
    data: data
  })
}

// 修改通知公告
export function updateNotice(data) {
  return request({
    url: '/commonservice/notice',
    method: 'put',
    data: data
  })
}

// 删除通知公告
export function delNotice(noticeId) {
  return request({
    url: '/commonservice/notice/' + noticeId,
    method: 'delete'
  })
}

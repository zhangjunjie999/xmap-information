import request from '@/utils/request'

// 查询讲解员预约列表
export function listCommentator(query) {
  return request({
    url: '/reservation/commentator/list',
    method: 'get',
    params: query
  })
}

// 查询讲解员预约详细
export function getCommentator(tourGuideId) {
  return request({
    url: '/reservation/commentator/' + tourGuideId,
    method: 'get'
  })
}

// 新增讲解员预约
export function addCommentator(data) {
  return request({
    url: '/reservation/commentator',
    method: 'post',
    data: data
  })
}

// 修改讲解员预约
export function updateCommentator(data) {
  return request({
    url: '/reservation/commentator',
    method: 'put',
    data: data
  })
}

// 删除讲解员预约
export function delCommentator(tourGuideId) {
  return request({
    url: '/reservation/commentator/' + tourGuideId,
    method: 'delete'
  })
}

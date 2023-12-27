import request from '@/utils/request'

// 查询讲解员信息管理列表
export function listComentator(query) {
  return request({
    url: '/commonservice/comentator/list',
    method: 'get',
    params: query
  })
}

// 查询讲解员信息管理详细
export function getComentator(comentatorId) {
  return request({
    url: '/commonservice/comentator/' + comentatorId,
    method: 'get'
  })
}

// 新增讲解员信息管理
export function addComentator(data) {
  return request({
    url: '/commonservice/comentator',
    method: 'post',
    data: data
  })
}

// 修改讲解员信息管理
export function updateComentator(data) {
  return request({
    url: '/commonservice/comentator',
    method: 'put',
    data: data
  })
}

// 删除讲解员信息管理
export function delComentator(comentatorId) {
  return request({
    url: '/commonservice/comentator/' + comentatorId,
    method: 'delete'
  })
}

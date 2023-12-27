import request from '@/utils/request'

// 查询个人信息列表
export function listPersonalinfo(query) {
  return request({
    url: '/commonservice/personalinfo/list',
    method: 'get',
    params: query
  })
}

// 查询个人信息详细
export function getPersonalinfo(personalinfoId) {
  return request({
    url: '/commonservice/personalinfo/' + personalinfoId,
    method: 'get'
  })
}

// 新增个人信息
export function addPersonalinfo(data) {
  return request({
    url: '/commonservice/personalinfo',
    method: 'post',
    data: data
  })
}

// 修改个人信息
export function updatePersonalinfo(data) {
  return request({
    url: '/commonservice/personalinfo',
    method: 'put',
    data: data
  })
}

// 删除个人信息
export function delPersonalinfo(personalinfoId) {
  return request({
    url: '/commonservice/personalinfo/' + personalinfoId,
    method: 'delete'
  })
}

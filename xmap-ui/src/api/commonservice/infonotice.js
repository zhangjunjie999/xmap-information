import request from '@/utils/request'

// 查询信息公告列表
export function listInfonotice(query) {
  return request({
    url: '/commonservice/infonotice/list',
    method: 'get',
    params: query
  })
}

// 查询信息公告详细
export function getInfonotice(infoId) {
  return request({
    url: '/commonservice/infonotice/' + infoId,
    method: 'get'
  })
}

// 新增信息公告
export function addInfonotice(data) {
  return request({
    url: '/commonservice/infonotice',
    method: 'post',
    data: data
  })
}

// 修改信息公告
export function updateInfonotice(data) {
  return request({
    url: '/commonservice/infonotice',
    method: 'put',
    data: data
  })
}

// 删除信息公告
export function delInfonotice(infoId) {
  return request({
    url: '/commonservice/infonotice/' + infoId,
    method: 'delete'
  })
}

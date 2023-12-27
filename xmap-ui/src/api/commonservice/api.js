import request from '@/utils/request'

// 查询对外接口列表
export function listApi(query) {
  return request({
    url: '/commonservice/api/list',
    method: 'get',
    params: query
  })
}

// 查询对外接口详细
export function getApi(appId) {
  return request({
    url: '/commonservice/api/' + appId,
    method: 'get'
  })
}

// 新增对外接口
export function addApi(data) {
  return request({
    url: '/commonservice/api',
    method: 'post',
    data: data
  })
}

// 修改对外接口
export function updateApi(data) {
  return request({
    url: '/commonservice/api',
    method: 'put',
    data: data
  })
}

// 删除对外接口
export function delApi(appId) {
  return request({
    url: '/commonservice/api/' + appId,
    method: 'delete'
  })
}

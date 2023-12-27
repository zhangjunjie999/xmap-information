import request from '@/utils/request'

// 查询政策法规列表列表
export function listPolicy(query) {
  return request({
    url: '/information/policy/list',
    method: 'get',
    params: query
  })
}

// 查询政策法规列表详细
export function getPolicy(policesId) {
  return request({
    url: '/information/policy/' + policesId,
    method: 'get'
  })
}

// 新增政策法规列表
export function addPolicy(data) {
  return request({
    url: '/information/policy',
    method: 'post',
    data: data
  })
}

// 修改政策法规列表
export function updatePolicy(data) {
  return request({
    url: '/information/policy',
    method: 'put',
    data: data
  })
}

// 删除政策法规列表
export function delPolicy(policesId) {
  return request({
    url: '/information/policy/' + policesId,
    method: 'delete'
  })
}

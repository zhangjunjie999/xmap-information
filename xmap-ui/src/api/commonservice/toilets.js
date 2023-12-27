import request from '@/utils/request'

// 查询公厕信息列表
export function listToilets(query) {
  return request({
    url: '/commonservice/toilets/list',
    method: 'get',
    params: query
  })
}

// 查询公厕信息详细
export function getToilets(restroomId) {
  return request({
    url: '/commonservice/toilets/' + restroomId,
    method: 'get'
  })
}

// 新增公厕信息
export function addToilets(data) {
  return request({
    url: '/commonservice/toilets',
    method: 'post',
    data: data
  })
}

// 修改公厕信息
export function updateToilets(data) {
  return request({
    url: '/commonservice/toilets',
    method: 'put',
    data: data
  })
}

// 删除公厕信息
export function delToilets(restroomId) {
  return request({
    url: '/commonservice/toilets/' + restroomId,
    method: 'delete'
  })
}

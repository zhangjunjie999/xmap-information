import request from '@/utils/request'

// 查询攻略推荐列表
export function listStrategy(query) {
  return request({
    url: '/commonservice/strategy/list',
    method: 'get',
    params: query
  })
}

// 查询攻略推荐详细
export function getStrategy(guideId) {
  return request({
    url: '/commonservice/strategy/' + guideId,
    method: 'get'
  })
}

// 新增攻略推荐
export function addStrategy(data) {
  return request({
    url: '/commonservice/strategy',
    method: 'post',
    data: data
  })
}

// 修改攻略推荐
export function updateStrategy(data) {
  return request({
    url: '/commonservice/strategy',
    method: 'put',
    data: data
  })
}

// 删除攻略推荐
export function delStrategy(guideId) {
  return request({
    url: '/commonservice/strategy/' + guideId,
    method: 'delete'
  })
}

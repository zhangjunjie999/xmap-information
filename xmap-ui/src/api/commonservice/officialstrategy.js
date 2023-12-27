import request from '@/utils/request'

// 查询官方攻略列表
export function listOfficialstrategy(query) {
  return request({
    url: '/commonservice/officialstrategy/list',
    method: 'get',
    params: query
  })
}

// 查询官方攻略详细
export function getOfficialstrategy(strategyId) {
  return request({
    url: '/commonservice/officialstrategy/' + strategyId,
    method: 'get'
  })
}

// 新增官方攻略
export function addOfficialstrategy(data) {
  return request({
    url: '/commonservice/officialstrategy',
    method: 'post',
    data: data
  })
}

// 修改官方攻略
export function updateOfficialstrategy(data) {
  return request({
    url: '/commonservice/officialstrategy',
    method: 'put',
    data: data
  })
}

// 删除官方攻略
export function delOfficialstrategy(strategyId) {
  return request({
    url: '/commonservice/officialstrategy/' + strategyId,
    method: 'delete'
  })
}

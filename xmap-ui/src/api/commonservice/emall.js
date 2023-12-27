import request from '@/utils/request'

// 查询电子商城列表
export function listEmall(query) {
  return request({
    url: '/commonservice/emall/list',
    method: 'get',
    params: query
  })
}

// 查询电子商城详细
export function getEmall(emallId) {
  return request({
    url: '/commonservice/emall/' + emallId,
    method: 'get'
  })
}

// 新增电子商城
export function addEmall(data) {
  return request({
    url: '/commonservice/emall',
    method: 'post',
    data: data
  })
}

// 修改电子商城
export function updateEmall(data) {
  return request({
    url: '/commonservice/emall',
    method: 'put',
    data: data
  })
}

// 删除电子商城
export function delEmall(emallId) {
  return request({
    url: '/commonservice/emall/' + emallId,
    method: 'delete'
  })
}

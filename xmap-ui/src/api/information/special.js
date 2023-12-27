import request from '@/utils/request'

// 查询特色产品管理列表
export function listSpecial(query) {
  return request({
    url: '/information/special/list',
    method: 'get',
    params: query
  })
}

// 查询特色产品管理详细
export function getSpecial(id) {
  return request({
    url: '/information/special/' + id,
    method: 'get'
  })
}

// 新增特色产品管理
export function addSpecial(data) {
  return request({
    url: '/information/special',
    method: 'post',
    data: data
  })
}

// 修改特色产品管理
export function updateSpecial(data) {
  return request({
    url: '/information/special',
    method: 'put',
    data: data
  })
}

// 删除特色产品管理
export function delSpecial(id) {
  return request({
    url: '/information/special/' + id,
    method: 'delete'
  })
}

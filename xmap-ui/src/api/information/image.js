import request from '@/utils/request'

// 查询图片管理列表
export function listImage(query) {
  return request({
    url: '/information/image/list',
    method: 'get',
    params: query
  })
}

// 查询图片管理详细
export function getImage(id) {
  return request({
    url: '/information/image/' + id,
    method: 'get'
  })
}

// 新增图片管理
export function addImage(data) {
  return request({
    url: '/information/image',
    method: 'post',
    data: data
  })
}

// 修改图片管理
export function updateImage(data) {
  return request({
    url: '/information/image',
    method: 'put',
    data: data
  })
}

// 删除图片管理
export function delImage(id) {
  return request({
    url: '/information/image/' + id,
    method: 'delete'
  })
}

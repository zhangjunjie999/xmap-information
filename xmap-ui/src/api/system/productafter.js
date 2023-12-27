import request from '@/utils/request'

// 查询售后服务列表
export function listProductafter(query) {
  return request({
    url: '/system/productafter/list',
    method: 'get',
    params: query
  })
}

// 查询售后服务详细
export function getProductafter(aftersalesId) {
  return request({
    url: '/system/productafter/' + aftersalesId,
    method: 'get'
  })
}

// 新增售后服务
export function addProductafter(data) {
  return request({
    url: '/system/productafter',
    method: 'post',
    data: data
  })
}

// 修改售后服务
export function updateProductafter(data) {
  return request({
    url: '/system/productafter',
    method: 'put',
    data: data
  })
}

// 删除售后服务
export function delProductafter(aftersalesId) {
  return request({
    url: '/system/productafter/' + aftersalesId,
    method: 'delete'
  })
}

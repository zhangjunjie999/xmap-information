import request from '@/utils/request'

// 查询商超推荐列表
export function listShop(query) {
  return request({
    url: '/commonservice/shop/list',
    method: 'get',
    params: query
  })
}

// 查询商超推荐详细
export function getShop(supermarketId) {
  return request({
    url: '/commonservice/shop/' + supermarketId,
    method: 'get'
  })
}

// 新增商超推荐
export function addShop(data) {
  return request({
    url: '/commonservice/shop',
    method: 'post',
    data: data
  })
}

// 修改商超推荐
export function updateShop(data) {
  return request({
    url: '/commonservice/shop',
    method: 'put',
    data: data
  })
}

// 删除商超推荐
export function delShop(supermarketId) {
  return request({
    url: '/commonservice/shop/' + supermarketId,
    method: 'delete'
  })
}

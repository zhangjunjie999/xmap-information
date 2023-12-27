import request from '@/utils/request'

// 查询酒店民宿预约列表
export function listHotel(query) {
  return request({
    url: '/reservation/hotel/list',
    method: 'get',
    params: query
  })
}

// 查询酒店民宿预约详细
export function getHotel(propertyId) {
  return request({
    url: '/reservation/hotel/' + propertyId,
    method: 'get'
  })
}

// 新增酒店民宿预约
export function addHotel(data) {
  return request({
    url: '/reservation/hotel',
    method: 'post',
    data: data
  })
}

// 修改酒店民宿预约
export function updateHotel(data) {
  return request({
    url: '/reservation/hotel',
    method: 'put',
    data: data
  })
}

// 删除酒店民宿预约
export function delHotel(propertyId) {
  return request({
    url: '/reservation/hotel/' + propertyId,
    method: 'delete'
  })
}

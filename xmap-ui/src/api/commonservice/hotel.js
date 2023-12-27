import request from '@/utils/request'

// 查询住宿推荐列表
export function listHotel(query) {
  return request({
    url: '/commonservice/hotel/list',
    method: 'get',
    params: query
  })
}

// 查询住宿推荐详细
export function getHotel(accommodationId) {
  return request({
    url: '/commonservice/hotel/' + accommodationId,
    method: 'get'
  })
}

// 新增住宿推荐
export function addHotel(data) {
  return request({
    url: '/commonservice/hotel',
    method: 'post',
    data: data
  })
}

// 修改住宿推荐
export function updateHotel(data) {
  return request({
    url: '/commonservice/hotel',
    method: 'put',
    data: data
  })
}

// 删除住宿推荐
export function delHotel(accommodationId) {
  return request({
    url: '/commonservice/hotel/' + accommodationId,
    method: 'delete'
  })
}

import request from '@/utils/request'

// 查询多媒体管理列表
export function listVideo(query) {
  return request({
    url: '/information/video/list',
    method: 'get',
    params: query
  })
}

// 查询多媒体管理详细
export function getVideo(id) {
  return request({
    url: '/information/video/' + id,
    method: 'get'
  })
}

// 新增多媒体管理
export function addVideo(data) {
  return request({
    url: '/information/video',
    method: 'post',
    data: data
  })
}

// 修改多媒体管理
export function updateVideo(data) {
  return request({
    url: '/information/video',
    method: 'put',
    data: data
  })
}

// 删除多媒体管理
export function delVideo(id) {
  return request({
    url: '/information/video/' + id,
    method: 'delete'
  })
}

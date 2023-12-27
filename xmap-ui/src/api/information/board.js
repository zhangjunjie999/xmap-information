import request from '@/utils/request'

// 查询公告信息管理列表
export function listBoard(query) {
  return request({
    url: '/information/board/list',
    method: 'get',
    params: query
  })
}

// 查询公告信息管理详细
export function getBoard(id) {
  return request({
    url: '/information/board/' + id,
    method: 'get'
  })
}

// 新增公告信息管理
export function addBoard(data) {
  return request({
    url: '/information/board',
    method: 'post',
    data: data
  })
}

// 修改公告信息管理
export function updateBoard(data) {
  return request({
    url: '/information/board',
    method: 'put',
    data: data
  })
}

// 删除公告信息管理
export function delBoard(id) {
  return request({
    url: '/information/board/' + id,
    method: 'delete'
  })
}

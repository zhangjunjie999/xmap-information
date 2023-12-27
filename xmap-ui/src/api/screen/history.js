import request from '@/utils/request'

// 查询设备对接历史列表
export function listHistory(query) {
  return request({
    url: '/screen/history/list',
    method: 'get',
    params: query
  })
}

// 查询设备对接历史详细
export function getHistory(historyId) {
  return request({
    url: '/screen/history/' + historyId,
    method: 'get'
  })
}

// 新增设备对接历史
export function addHistory(data) {
  return request({
    url: '/screen/history',
    method: 'post',
    data: data
  })
}

// 修改设备对接历史
export function updateHistory(data) {
  return request({
    url: '/screen/history',
    method: 'put',
    data: data
  })
}

// 删除设备对接历史
export function delHistory(historyId) {
  return request({
    url: '/screen/history/' + historyId,
    method: 'delete'
  })
}

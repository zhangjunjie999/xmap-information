import request from '@/utils/request'

// 查询旅游攻略表列表
export function listPlan(query) {
  return request({
    url: '/information/plan/list',
    method: 'get',
    params: query
  })
}

// 查询旅游攻略表详细
export function getPlan(id) {
  return request({
    url: '/information/plan/' + id,
    method: 'get'
  })
}

// 新增旅游攻略表
export function addPlan(data) {
  return request({
    url: '/information/plan',
    method: 'post',
    data: data
  })
}

// 修改旅游攻略表
export function updatePlan(data) {
  return request({
    url: '/information/plan',
    method: 'put',
    data: data
  })
}

// 删除旅游攻略表
export function delPlan(id) {
  return request({
    url: '/information/plan/' + id,
    method: 'delete'
  })
}

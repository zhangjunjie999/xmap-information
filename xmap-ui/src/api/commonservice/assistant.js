import request from '@/utils/request'

// 查询智能语音助手列表
export function listAssistant(query) {
  return request({
    url: '/commonservice/assistant/list',
    method: 'get',
    params: query
  })
}

// 查询智能语音助手详细
export function getAssistant(voiceId) {
  return request({
    url: '/commonservice/assistant/' + voiceId,
    method: 'get'
  })
}

// 新增智能语音助手
export function addAssistant(data) {
  return request({
    url: '/commonservice/assistant',
    method: 'post',
    data: data
  })
}

// 修改智能语音助手
export function updateAssistant(data) {
  return request({
    url: '/commonservice/assistant',
    method: 'put',
    data: data
  })
}

// 删除智能语音助手
export function delAssistant(voiceId) {
  return request({
    url: '/commonservice/assistant/' + voiceId,
    method: 'delete'
  })
}

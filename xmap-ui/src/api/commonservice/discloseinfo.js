import request from '@/utils/request'

// 查询信息公开列表
export function listDiscloseinfo(query) {
  return request({
    url: '/commonservice/discloseinfo/list',
    method: 'get',
    params: query
  })
}

// 查询信息公开详细
export function getDiscloseinfo(discloseinfoId) {
  return request({
    url: '/commonservice/discloseinfo/' + discloseinfoId,
    method: 'get'
  })
}

// 新增信息公开
export function addDiscloseinfo(data) {
  return request({
    url: '/commonservice/discloseinfo',
    method: 'post',
    data: data
  })
}

// 修改信息公开
export function updateDiscloseinfo(data) {
  return request({
    url: '/commonservice/discloseinfo',
    method: 'put',
    data: data
  })
}

// 删除信息公开
export function delDiscloseinfo(discloseinfoId) {
  return request({
    url: '/commonservice/discloseinfo/' + discloseinfoId,
    method: 'delete'
  })
}

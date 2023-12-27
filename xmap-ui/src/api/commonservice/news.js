import request from '@/utils/request'

// 查询景区资讯列表
export function listNews(query) {
  return request({
    url: '/commonservice/news/list',
    method: 'get',
    params: query
  })
}

// 查询景区资讯详细
export function getNews(newsId) {
  return request({
    url: '/commonservice/news/' + newsId,
    method: 'get'
  })
}

// 新增景区资讯
export function addNews(data) {
  return request({
    url: '/commonservice/news',
    method: 'post',
    data: data
  })
}

// 修改景区资讯
export function updateNews(data) {
  return request({
    url: '/commonservice/news',
    method: 'put',
    data: data
  })
}

// 删除景区资讯
export function delNews(newsId) {
  return request({
    url: '/commonservice/news/' + newsId,
    method: 'delete'
  })
}

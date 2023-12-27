import request from '@/utils/request'

// 查询旅游新闻列表列表
export function listNews(query) {
  return request({
    url: '/information/news/list',
    method: 'get',
    params: query
  })
}

// 查询旅游新闻列表详细
export function getNews(newsId) {
  return request({
    url: '/information/news/' + newsId,
    method: 'get'
  })
}

// 新增旅游新闻列表
export function addNews(data) {
  return request({
    url: '/information/news',
    method: 'post',
    data: data
  })
}

// 修改旅游新闻列表
export function updateNews(data) {
  return request({
    url: '/information/news',
    method: 'put',
    data: data
  })
}

// 删除旅游新闻列表
export function delNews(newsId) {
  return request({
    url: '/information/news/' + newsId,
    method: 'delete'
  })
}

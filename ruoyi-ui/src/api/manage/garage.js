import request from '@/utils/request'

// 查询 车库列表
export function listGarage(query) {
  return request({
    url: '/manage/garage/list',
    method: 'get',
    params: query
  })
}

// 查询 车库详细
export function getGarage(garageId) {
  return request({
    url: '/manage/garage/' + garageId,
    method: 'get'
  })
}

// 新增 车库
export function addGarage(data) {
  return request({
    url: '/manage/garage',
    method: 'post',
    data: data
  })
}

// 修改 车库
export function updateGarage(data) {
  return request({
    url: '/manage/garage',
    method: 'put',
    data: data
  })
}

// 删除 车库
export function delGarage(garageId) {
  return request({
    url: '/manage/garage/' + garageId,
    method: 'delete'
  })
}

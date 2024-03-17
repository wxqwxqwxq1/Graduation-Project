import request from '@/utils/request'

// 查询车位列表
export function listPark(query) {
  return request({
    url: '/fvehicles/park/list',
    method: 'get',
    params: query
  })
}

// 查询车位详细
export function getPark(associationId) {
  return request({
    url: '/fvehicles/park/' + associationId,
    method: 'get'
  })
}

// 新增车位
export function addPark(data) {
  return request({
    url: '/garage_fvehicle/garage_fvehicle',
    method: 'post',
    data: data
  })
}

// 修改车位
export function updatePark(data) {
  return request({
    url: '/garage_fvehicle/garage_fvehicle',
    method: 'put',
    data: data
  })
}

// 删除车位
export function delPark(associationId) {
  return request({
    url: '/garage_fvehicle/garage_fvehicle/' + associationId,
    method: 'delete'
  })
}
// 获取车牌照信息
export function querySearchLicensePlate(query) {
  return request({
    url: '/fvehicles/park/querySearchLicensePlate',
    method: 'get',
    params: query
  })
}
// 获取车位信息
export function querySearchPark(query) {
  return request({
    url: '/fvehicles/park/querySearchPark',
    method: 'get',
    params: query
  })
}
// 获取车主信息
export function querySearchOwner(query) {
  return request({
    url: '/fvehicles/owner/querySearchOwner/'+query,
    method: 'get',
    params: query
  })
}
// 获取车牌信息
export function querySearchLicensePlateInfo(query) {
  return request({
    url: '/fvehicles/fvehicle/querySearchLicensePlateInfo/'+query,
    method: 'get',
    params: query
  })
}
// 获取车库信息
export function querySearchGarageLocation(query) {
  return request({
    url: '/manage/garage/querySearchGarageLocation/'+query,
    method: 'get',
    params: query
  })
}

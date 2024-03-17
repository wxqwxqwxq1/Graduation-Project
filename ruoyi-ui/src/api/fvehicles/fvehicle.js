import request from '@/utils/request'

// 查询车辆列表
export function listFvehicle(query) {
  return request({
    url: '/fvehicles/fvehicle/list',
    method: 'get',
    params: query
  })
}

// 查询车辆详细
export function getFvehicle(vehicleId) {
  return request({
    url: '/fvehicles/fvehicle/' + vehicleId,
    method: 'get'
  })
}

// 新增车辆
export function addFvehicle(data) {
  return request({
    url: '/fvehicles/fvehicle',
    method: 'post',
    data: data
  })
}

// 修改车辆
export function updateFvehicle(data) {
  return request({
    url: '/fvehicles/fvehicle',
    method: 'put',
    data: data
  })
}

// 删除车辆
export function delFvehicle(vehicleId) {
  return request({
    url: '/fvehicles/fvehicle/' + vehicleId,
    method: 'delete'
  })
}
// 查询车主列表


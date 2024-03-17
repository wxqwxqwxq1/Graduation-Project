import request from '@/utils/request'

// 查询车主列表
export function listOwner(query) {
  return request({
    url: '/fvehicles/owner/list',
    method: 'get',
    params: query
  })
}

// 查询车主详细
export function getOwner(ownerId) {
  return request({
    url: '/fvehicles/owner/' + ownerId,
    method: 'get'
  })
}

// 新增车主
export function addOwner(data) {
  return request({
    url: '/fvehicles/owner',
    method: 'post',
    data: data
  })
}

// 修改车主
export function updateOwner(data) {
  return request({
    url: '/fvehicles/owner',
    method: 'put',
    data: data
  })
}

// 删除车主
export function delOwner(ownerId) {
  return request({
    url: '/fvehicles/owner/' + ownerId,
    method: 'delete'
  })
}



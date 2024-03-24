import request from '@/utils/request'

// 查询车库-价格视图列表
export function listPricingGarage(query) {
  return request({
    url: '/manage/pricingGarage/list',
    method: 'get',
    params: query
  })
}

// 查询车库-价格视图详细
export function getPricingGarage(pricingId) {
  return request({
    url: '/manage/pricingGarage/' + pricingId,
    method: 'get'
  })
}

// 新增车库-价格视图
export function addPricingGarage(data) {
  return request({
    url: '/manage/pricingGarage',
    method: 'post',
    data: data
  })
}

// 修改车库-价格视图
export function updatePricingGarage(data) {
  return request({
    url: '/manage/pricingGarage',
    method: 'put',
    data: data
  })
}

// 删除车库-价格视图
export function delPricingGarage(pricingId) {
  return request({
    url: '/manage/pricingGarage/' + pricingId,
    method: 'delete'
  })
}

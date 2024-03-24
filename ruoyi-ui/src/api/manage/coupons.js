import request from '@/utils/request'

// 查询优惠券表列表
export function listCoupons(query) {
  return request({
    url: '/manage/coupons/list',
    method: 'get',
    params: query
  })
}

// 查询优惠券表详细
export function getCoupons(couponId) {
  return request({
    url: '/manage/coupons/' + couponId,
    method: 'get'
  })
}

// 新增优惠券表
export function addCoupons(data) {
  return request({
    url: '/manage/coupons',
    method: 'post',
    data: data
  })
}

// 修改优惠券表
export function updateCoupons(data) {
  return request({
    url: '/manage/coupons',
    method: 'put',
    data: data
  })
}

// 删除优惠券表
export function delCoupons(couponId) {
  return request({
    url: '/manage/coupons/' + couponId,
    method: 'delete'
  })
}

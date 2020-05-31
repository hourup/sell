package com.yaya.sell.service;

import com.yaya.sell.dto.OrderDTO;

/**
 * @author yaomengya
 * @date 2020/3/24
 */
public interface BuyerService {

    // 查询一个订单
    OrderDTO findOrderOne(String openid, String orderId);

    // 取消订单
    OrderDTO cancelOrder(String openid, String orderId);
}

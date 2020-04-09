package com.yaya.sell.service.impl;

import com.yaya.sell.dto.OrderDTO;
import com.yaya.sell.enums.ResultEnum;
import com.yaya.sell.exception.SellException;
import com.yaya.sell.service.BuyerService;
import com.yaya.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author changhr2013
 * @date 2020/3/24
 */
@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO == null) {
            log.error("[取消订单]查不到该订单，orderId=[{}]", orderId);
            throw new SellException(ResultEnum.ORDER_IS_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            return null;
        }
        // 判断是否是自己的订单
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("[查询订单]订单的 openid 不一致，openid=[{}],orderDTO=[{}]", openid, orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
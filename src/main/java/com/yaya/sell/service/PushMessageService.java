package com.yaya.sell.service;

import com.yaya.sell.dto.OrderDTO;

/**
 * @author yaomengya
 * @date 2020/5/11
 */
public interface PushMessageService {

    void orderStatus(OrderDTO orderDTO);
}

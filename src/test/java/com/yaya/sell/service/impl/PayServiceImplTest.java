package com.yaya.sell.service.impl;

import com.yaya.sell.dto.OrderDTO;
import com.yaya.sell.service.OrderService;
import com.yaya.sell.service.PayService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @Test
    void create() {
        OrderDTO orderDTO = orderService.findOne("1584895390323780651");
        payService.create(orderDTO);
    }

    @Test
    void refund() {
        OrderDTO orderDTO = orderService.findOne("1586616554375706263");
        payService.refund(orderDTO);
    }
}
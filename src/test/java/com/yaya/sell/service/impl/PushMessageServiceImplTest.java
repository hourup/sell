package com.yaya.sell.service.impl;

import com.yaya.sell.dto.OrderDTO;
import com.yaya.sell.service.OrderService;
import com.yaya.sell.service.PushMessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class PushMessageServiceImplTest {

    @Autowired
    private PushMessageService pushMessageService;

    @Autowired
    private OrderService orderService;

    @Test
    void orderStatus() {

        OrderDTO orderDTO = orderService.findOne("1586594357254443524");
        pushMessageService.orderStatus(orderDTO);
    }
}
package com.yaya.sell.repository;

import com.yaya.sell.dataobject.OrderMaster;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.Assert;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository orderRepository;

    private final String OPEN_ID = "110111";

    @Test
    void saveOneTest(){
        OrderMaster orderMaster = new OrderMaster()
                .setOrderId("123458")
                .setBuyerName("浩然")
                .setBuyerPhone("15738814496")
                .setBuyerAddress("安阳工学院")
                .setBuyerOpenid(OPEN_ID)
                .setOrderAmount(new BigDecimal("19.9"));
        OrderMaster order = orderRepository.save(orderMaster);
        Assert.notNull(order, "非空");
    }

    @Test
    void findByBuyerOpenid() {
        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<OrderMaster> pageInfo = orderRepository.findByBuyerOpenid(OPEN_ID, pageRequest);
        pageInfo.getContent().forEach(System.out::println);
        Assert.notEmpty(pageInfo.getContent(), "非空");
    }
}
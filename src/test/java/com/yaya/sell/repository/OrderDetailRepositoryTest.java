package com.yaya.sell.repository;

import com.yaya.sell.dataobject.OrderDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository detailRepository;

    @Test
    void saveOneTest(){
        OrderDetail orderDetail = new OrderDetail()
                .setDetailId("123457")
                .setOrderId("123456")
                .setProductId("123457")
                .setProductName("皮皮虾")
                .setProductPrice(new BigDecimal("3.2"))
                .setProductIcon("http://xxx.jpg")
                .setProductQuantity(2);

        OrderDetail detail = detailRepository.save(orderDetail);
        Assert.notNull(detail, "非空");
    }

    @Test
    void findByOrderIdTest() {
        List<OrderDetail> orderDetailList = detailRepository.findByOrderId("123456");
        orderDetailList.forEach(System.out::println);
        Assert.notEmpty(orderDetailList, "非空");
    }
}
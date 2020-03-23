package com.yaya.sell.service.impl;

import com.yaya.sell.dataobject.OrderDetail;
import com.yaya.sell.dto.CartDTO;
import com.yaya.sell.dto.OrderDTO;
import com.yaya.sell.enums.OrderStatusEnum;
import com.yaya.sell.enums.PayStatusEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private static final String BUYER_OPEN_ID = "110111";

    private static final String ORDER_ID = "1584811010450430046";

    private static final String UNFINISHED_ORDER_ID = "1584810832742224756";

    private static final String UNPAID_ORDER_ID = "1584811010450430046";

    @Test
    void create() {
        List<OrderDetail> cartDTOList = new ArrayList<OrderDetail>() {{
            add(new OrderDetail().setProductId("123456").setProductQuantity(2));
            add(new OrderDetail().setProductId("123457").setProductQuantity(3));
        }};
        OrderDTO orderDTO = new OrderDTO()
                .setBuyerName("丫丫")
                .setBuyerAddress("安阳工学院")
                .setBuyerOpenid(BUYER_OPEN_ID)
                .setBuyerPhone("13598040501")
                .setOrderDetailList(cartDTOList);
        OrderDTO order = orderService.create(orderDTO);
        Assert.notNull(order, "非空");
    }

    @Test
    void findOne() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        System.out.println(orderDTO);
        Assert.notNull(orderDTO, "非空");
        Assert.isTrue(orderDTO.getOrderId().equals(ORDER_ID), "Order ID 相等");
    }

    @Test
    void findList() {
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPEN_ID, PageRequest.of(0, 5));
        orderDTOPage.getContent().forEach(System.out::println);
        Assert.notEmpty(orderDTOPage.getContent(), "非空");
    }

    @Test
    void cancel() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.isTrue(result.getOrderStatus().equals(OrderStatusEnum.CANCEL.getCode()), "相等");
    }

    @Test
    void finish() {
        OrderDTO orderDTO = orderService.findOne(UNFINISHED_ORDER_ID);
        OrderDTO result = orderService.finish(orderDTO);
        Assert.isTrue(result.getOrderStatus().equals(OrderStatusEnum.FINISHED.getCode()), "相等");
    }

    @Test
    void paid() {
        OrderDTO orderDTO = orderService.findOne(UNPAID_ORDER_ID);
        OrderDTO result = orderService.paid(orderDTO);
        Assert.isTrue(result.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode()), "相等");
    }
}
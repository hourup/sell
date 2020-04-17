package com.yaya.sell.controller;

import com.lly835.bestpay.model.PayResponse;
import com.yaya.sell.dto.OrderDTO;
import com.yaya.sell.enums.ResultEnum;
import com.yaya.sell.exception.SellException;
import com.yaya.sell.service.OrderService;
import com.yaya.sell.service.PayService;
import com.yaya.sell.utils.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.sql.DriverManager;
import java.util.Map;

/**
 * @author changhr2013
 * @date 2020/4/11
 */
@Slf4j
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String, Object> map) {

        // 1. 查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            throw new SellException(ResultEnum.ORDER_IS_NOT_EXIST);
        }

        PayResponse payResponse = payService.create(orderDTO);

        map.put("payResponse", payResponse);
        map.put("returnUrl", returnUrl);

        return new ModelAndView("pay/create", map);
    }

    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData) {

        // 1. 验证签名
        // 2. 支付的状态
        // 3. 支付金额
        // 4. 支付人（下单人 == 支付人）
        payService.notify(notifyData);

        return new ModelAndView("pay/success");
    }
}

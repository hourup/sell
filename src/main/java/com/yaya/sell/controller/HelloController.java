package com.yaya.sell.controller;

import com.yaya.sell.enums.ResultEnum;
import com.yaya.sell.exception.SellException;
import com.yaya.sell.threadlocal.LocalRequestContext;
import com.yaya.sell.threadlocal.LocalRequestContextHolder;
import com.yaya.sell.threadlocal.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author changhr2013
 * @date 2020/3/19
 */
@RestController
public class HelloController {

    @GetMapping("/hello/{id}")
    public String hello(@PathVariable("id") String id) {
        LocalRequestContext context = LocalRequestContextHolder.getLocalRequestContext()
                .orElseThrow(() -> new SellException(ResultEnum.CART_EMPTY));

        UserInfo userInfo = context.getUserInfo();
        System.out.println(userInfo);
        return userInfo.toString();
    }

    @GetMapping("/hi/{id}")
    public String hi(HttpServletRequest request, @PathVariable("id") String id) {
        System.out.println(request.getRequestURL());
        return request.getRequestURL().toString();
    }
}

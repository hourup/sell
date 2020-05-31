package com.yaya.sell.controller;

import com.yaya.sell.config.ProjectUrlConfig;
import com.yaya.sell.constant.CookieConstant;
import com.yaya.sell.constant.RedisConstant;
import com.yaya.sell.dataobject.SellerInfo;
import com.yaya.sell.enums.ResultEnum;
import com.yaya.sell.service.SellerService;
import com.yaya.sell.utils.CookieUtil;
import com.yaya.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author yaomengya
 * @date 2020/5/5
 */
@Slf4j
@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/login")
    public ModelAndView login(HttpServletResponse response,
                              @RequestParam("openid") String openid,
                              Map<String, Object> map) {

        // openid 去和数据库里的数据匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);

        if (sellerInfo == null) {
            map.put("msg", ResultEnum.LOGIN_FAIL.getMsg());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        // 设置 token 至 redis
        String token = KeyUtil.generateRandomId();
        Integer expire = RedisConstant.TOKEN_EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openid, expire, TimeUnit.SECONDS);

        // 设置 token 至 Cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);

        map.put("url", projectUrlConfig.getSell() + "/sell/seller/order/list");
//        return new ModelAndView("common/login", map);
        return new ModelAndView("redirect:" + projectUrlConfig.getSell() + "/sell/seller/order/list");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {

        // 获取 cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);

        // 清除 redis
        if (cookie != null) {
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        }

        // 清除 cookie
        CookieUtil.set(response, CookieConstant.TOKEN, null, 0);

        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMsg());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success", map);
    }
}

package com.yaya.sell.aspect;

import com.yaya.sell.constant.CookieConstant;
import com.yaya.sell.constant.RedisConstant;
import com.yaya.sell.exception.SellerAuthorizeException;
import com.yaya.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author yaomengya
 * @date 2020/5/10
 */
@Slf4j
@Aspect
@Component
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.yaya.sell.controller.Seller*.*(..))" +
    "&& !execution(public * com.yaya.sell.controller.SellerUserController.*(..))")
    public void pointcut(){}

    @Before("pointcut()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 查询 cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("[登录校验] Cookie 中查不到 token");
            throw new SellerAuthorizeException();
        }

        String tokenRedisKey = String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue());
        String tokenValue = redisTemplate.opsForValue().get(tokenRedisKey);

        if (StringUtils.isEmpty(tokenValue)) {
            log.warn("[登录校验] Redis 中查不到 token");
            throw new SellerAuthorizeException();
        }
    }
}

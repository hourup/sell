package com.yaya.sell.constant;

/**
 * Redis 常量
 * @author yaomengya
 * @date 2020/5/5
 */
public interface RedisConstant {

    /** TOKEN 格式 */
    String TOKEN_PREFIX = "token_%s";

    /** TOKEN 过期时间，2 小时 */
    Integer TOKEN_EXPIRE = 7200;
}

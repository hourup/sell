package com.yaya.sell.utils;

import java.security.SecureRandom;
import java.time.Clock;

/**
 * @author changhr2013
 * @date 2020/3/21
 */
public class KeyUtil {

    private KeyUtil() {
    }

    /**
     * 生成唯一主键
     * 格式：时间 + 随机数
     *
     * @return 随机数
     */
    public static synchronized String getUniqueKey() {
        SecureRandom random = new SecureRandom();
        long now = Clock.systemUTC().millis();

        int number = random.nextInt(900000) + 100000;
        return now + String.valueOf(number);
    }
}

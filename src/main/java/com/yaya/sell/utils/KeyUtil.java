package com.yaya.sell.utils;

import org.springframework.util.AlternativeJdkIdGenerator;

import java.security.SecureRandom;
import java.time.Clock;

/**
 * @author yaomengya
 * @date 2020/3/21
 */
public class KeyUtil {

    private KeyUtil() {
    }

    private static final AlternativeJdkIdGenerator GENERATOR = new AlternativeJdkIdGenerator();

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

    /**
     * 生成不带 “-” 的UUID
     *
     * @return String
     */
    public static String generateRandomId() {
        return GENERATOR.generateId().toString().replace("-", "");
    }
}

package com.yaya.sell.utils;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * @author changhr2013
 * @date 2020/4/11
 */
public class MathUtil {

    private MathUtil() {
    }

    private static final Double MONEY_RANGE = 0.01;

    /**
     * 比较两个金额是否相等
     *
     * @param d1 金额 1
     * @param d2 金额 2
     * @return 相等：true，不等：false
     */
    public static Boolean equals(Double d1, Double d2) {
        double result = Math.abs(d1 - d2);
        return result < MONEY_RANGE;
    }

    public static Boolean notEquals(Double d1, Double d2) {
        return !equals(d1, d2);
    }
}

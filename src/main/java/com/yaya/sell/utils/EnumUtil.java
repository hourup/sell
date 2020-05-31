package com.yaya.sell.utils;

import com.yaya.sell.enums.CodeEnum;

/**
 * @author yaomengya
 * @date 2020/5/2
 */
public class EnumUtil {

    public static <T, E extends CodeEnum<T>> E getByCode(T code, Class<E> enumClass) {
        for (E itemEnum : enumClass.getEnumConstants()) {
            if (code.equals(itemEnum.getCode())) {
                return itemEnum;
            }
        }
        return null;
    }
}

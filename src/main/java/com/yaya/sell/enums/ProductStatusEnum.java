package com.yaya.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yaomengya
 * @date 2020/3/20
 */
@Getter
@AllArgsConstructor
public enum ProductStatusEnum implements CodeEnum<Integer> {

    /** 商品状态 */
    UP(0, "在架"),
    DOWN(1, "下架");

    private Integer code;

    private String message;
}

package com.yaya.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yaomengya
 * @date 2020/3/21
 */
@Getter
@AllArgsConstructor
public enum PayStatusEnum implements CodeEnum<Integer> {

    /** 支付状态 */
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功");

    private Integer code;

    private String message;
}

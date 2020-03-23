package com.yaya.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author changhr2013
 * @date 2020/3/21
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

    /** 订单状态类型 */
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消");

    private Integer code;

    private String massage;
}

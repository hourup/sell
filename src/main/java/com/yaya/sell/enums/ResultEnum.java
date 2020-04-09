package com.yaya.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author changhr2013
 * @date 2020/3/21
 */
@Getter
@AllArgsConstructor
public enum ResultEnum implements IResultEnum {

    /** 结果枚举 */
    SUCCESS(0, "success")
    , PRODUCT_IS_NOT_EXIST(100001, "商品不存在")
    , PRODUCT_STOCK_NOT_ENOUGH(100002, "库存不足")
    , ORDER_IS_NOT_EXIST(100003, "订单不存在")
    , ORDER_DETAIL_IS_NOT_EXIST(100004, "订单详情不存在")
    , ORDER_STATUS_ERROR(100005, "订单状态不正确")
    , ORDER_DETAIL_EMPTY(100006, "订单中无订单详情")
    , ORDER_PAY_STATUS_ERROR(100007, "订单支付状态不正确")
    , PARAM_ERROR(100008, "参数不正确")
    , CART_EMPTY(100009, "购物车不能为空")
    , ORDER_OWNER_ERROR(100010, "订单不属于当前用户")
    , WECHAT_MP_ERROR(100011, "微信公众账号错误")
    ;

    private Integer code;

    private String msg;
}

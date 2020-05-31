package com.yaya.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yaya.sell.dataobject.OrderDetail;
import com.yaya.sell.enums.OrderStatusEnum;
import com.yaya.sell.enums.PayStatusEnum;
import com.yaya.sell.serializer.Date2LongSerializer;
import com.yaya.sell.utils.EnumUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.EnumUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yaomengya
 * @date 2020/3/21
 */
@Data
@Accessors(chain = true)
public class OrderDTO {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    /** 买家微信 openid */
    private String buyerOpenid;

    private BigDecimal orderAmount;

    /** 订单状态 */
    private Integer orderStatus;

    /** 支付状态 */
    private Integer payStatus;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtil.getByCode(this.orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum() {
        return EnumUtil.getByCode(this.payStatus, PayStatusEnum.class);
    }
}

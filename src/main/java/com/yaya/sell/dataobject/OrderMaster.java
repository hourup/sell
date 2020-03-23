package com.yaya.sell.dataobject;

import com.yaya.sell.enums.OrderStatusEnum;
import com.yaya.sell.enums.PayStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author changhr2013
 * @date 2020/3/21
 */
@Data
@Accessors(chain = true)
@Entity
@DynamicUpdate
public class OrderMaster {

    @Id
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    /** 买家微信 openid */
    private String buyerOpenid;

    private BigDecimal orderAmount;

    /** 订单状态 */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /** 支付状态 */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
}

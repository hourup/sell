package com.yaya.sell.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.BestPayService;
import com.yaya.sell.dto.OrderDTO;
import com.yaya.sell.enums.ResultEnum;
import com.yaya.sell.exception.SellException;
import com.yaya.sell.service.OrderService;
import com.yaya.sell.service.PayService;
import com.yaya.sell.utils.JsonUtil;
import com.yaya.sell.utils.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yaomengya
 * @date 2020/4/11
 */
@Slf4j
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private BestPayService bestPayService;

    @Autowired
    private OrderService orderService;

    private static final String ORDER_NAME = "微信点餐订单";

    /**
     * 发起微信支付
     *
     * @param orderDTO 订单 DTO
     * @return PayResponse
     */
    @Override
    public PayResponse create(OrderDTO orderDTO) {

        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_MP);

        log.info("[微信支付] 发起支付，request=[{}]", JsonUtil.toJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("[微信支付] 发起支付，payResponse=[{}]", JsonUtil.toJson(payResponse));
        return payResponse;
    }

    /**
     * 处理支付成功异步通知
     *
     * @param notifyData 异步通知数据
     * @return PayResponse
     */
    @Override
    public PayResponse notify(String notifyData) {
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("[微信支付] 异步通知，payResponse=[{}]", payResponse);

        // 修改订单支付状态
        OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());

        // 判断订单是否存在
        if (orderDTO == null) {
            log.error("[微信支付] 异步通知，订单不存在，orderId=[{}]", payResponse.getOrderId());
            throw new SellException(ResultEnum.ORDER_IS_NOT_EXIST);
        }

        // 判断订单金额是否一致
        if (MathUtil.notEquals(payResponse.getOrderAmount(), orderDTO.getOrderAmount().doubleValue())) {
            log.error("[微信支付] 异步通知，订单金额不一致，orderId=[{}]，微信通知金额=[{}]，系统金额=[{}]",
                    payResponse.getOrderId(), payResponse.getOrderAmount(), orderDTO.getOrderAmount());
            throw new SellException(ResultEnum.WECHAT_PAY_NOTIFY_MONEY_VERIFY_ERROR);
        }

        // 修改支付状态
        orderService.paid(orderDTO);
        return payResponse;
    }

    /**
     * 退款
     *
     * @param orderDTO 订单 DTO
     */
    @Override
    public RefundResponse refund(OrderDTO orderDTO) {

        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_MP);

        log.info("[微信退款] request=[{}]", JsonUtil.toJson(refundRequest));
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("[微信退款] response=[{}]", JsonUtil.toJson(refundResponse));
        return refundResponse;
    }
}

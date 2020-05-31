package com.yaya.sell.service.impl;

import com.yaya.sell.config.WeChatAccountConfig;
import com.yaya.sell.dto.OrderDTO;
import com.yaya.sell.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author yaomengya
 * @date 2020/5/11
 */
@Slf4j
@Service
public class PushMessageServiceImpl implements PushMessageService {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WeChatAccountConfig weChatAccountConfig;

    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId(weChatAccountConfig.getTemplateId().get("orderStatus"));
        templateMessage.setToUser(orderDTO.getBuyerOpenid());

        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first", "亲，记得收货"),
                new WxMpTemplateData("storeName", "泛轻舟小店"),
                new WxMpTemplateData("orderType", "外卖"),
                new WxMpTemplateData("orderId", orderDTO.getOrderId()),
                new WxMpTemplateData("remark", "消费金额：" + orderDTO.getOrderAmount() + " 元"
                        + "\n商家电话：13598040501"
                        + "\n订单状态：" + orderDTO.getOrderStatusEnum().getMassage()
                        + "\n欢迎再次光临！")
        );
        templateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            log.error("[微信模板消息] 发送失败，{}", e.getMessage(), e);
        }
    }
}

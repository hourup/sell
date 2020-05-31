package com.yaya.sell.config;

import com.lly835.bestpay.config.WxPayConfig;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.lly835.bestpay.service.impl.WxPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yaomengya
 * @date 2020/4/11
 */
@Configuration
public class WeChatPayConfig {

    @Autowired
    private WeChatAccountConfig weChatAccountConfig;

    @Bean
    public WxPayConfig wxPayConfig() {
        WxPayConfig wxPayConfig = new WxPayConfig();
        wxPayConfig.setAppId(weChatAccountConfig.getAppId());
        wxPayConfig.setAppSecret(weChatAccountConfig.getAppSecret());
        wxPayConfig.setMchId(weChatAccountConfig.getMchId());
        wxPayConfig.setMchKey(weChatAccountConfig.getMchKey());
        wxPayConfig.setKeyPath(weChatAccountConfig.getKeyPath());
        wxPayConfig.setNotifyUrl(weChatAccountConfig.getNotifyUrl());
        return wxPayConfig;
    }

    @Bean
    public BestPayService wxPayService() {
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayConfig(wxPayConfig());
        return bestPayService;
    }
}

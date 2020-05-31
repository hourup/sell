package com.yaya.sell.config;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yaomengya
 * @date 2020/4/9
 */
@Configuration
public class WeChatMpConfig {

    @Autowired
    private WeChatAccountConfig weChatAccountConfig;

    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpServiceOkHttp = new WxMpServiceImpl();
        wxMpServiceOkHttp.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpServiceOkHttp;
    }

    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpDefaultConfigImpl wxMpDefaultConfig = new WxMpDefaultConfigImpl();
        wxMpDefaultConfig.setAppId(weChatAccountConfig.getAppId());
        wxMpDefaultConfig.setSecret(weChatAccountConfig.getAppSecret());
        return wxMpDefaultConfig;
    }
}

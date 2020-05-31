package com.yaya.sell.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author yaomengya
 * @date 2020/5/5
 */
@Configuration
public class AliPayConfig {

    @Autowired
    private AliPayAccountConfig config;

    @Bean
    public AlipayClient alipayClient() {

        return new DefaultAlipayClient(
                config.getUrl(),
                config.getAppId(),
                config.getAppPrivateKey(),
                config.getFormat(),
                config.getCharset(),
                config.getAlipayPublicKey(),
                config.getSignType());
    }
}

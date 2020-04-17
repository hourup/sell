package com.yaya.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author changhr2013
 * @date 2020/4/9
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WeChatAccountConfig {

    private String appId;

    private String appSecret;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */
    private String keyPath;

    private String notifyUrl;
}

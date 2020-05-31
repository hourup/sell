package com.yaya.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yaomengya
 * @date 2020/5/5
 */
@Data
@Component
@ConfigurationProperties(prefix = "alipay")
public class AliPayAccountConfig {

    private String url;

    private String appId;

    private String appPrivateKey;

    private String format;

    private String charset;

    private String alipayPublicKey;

    private String signType;
}

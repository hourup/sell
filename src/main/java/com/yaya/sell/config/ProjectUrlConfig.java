package com.yaya.sell.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yaomengya
 * @date 2020/5/5
 */
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = "project.url")
@Component
public class ProjectUrlConfig {

    /** 微信支付 */
    public String wechatMpAuthorize;

    /** 支付宝开放平台 */
    public String alipayOpenAuthorize;

    /** 点餐系统 URL */
    public String sell;
}

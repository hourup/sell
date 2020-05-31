package com.yaya.sell.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.yaya.sell.config.AliPayAccountConfig;
import com.yaya.sell.config.ProjectUrlConfig;
import com.yaya.sell.enums.ResultEnum;
import com.yaya.sell.exception.SellException;
import com.yaya.sell.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * 集成支付宝开放平台扫码登录
 *
 * @author yaomengya
 * @date 2020/5/5
 */
@Slf4j
@Controller
@RequestMapping("/alipay")
public class AliPayController {

    @Autowired
    private AliPayAccountConfig config;

    @Autowired
    private AlipayClient alipayClient;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/qrAuthorize")
    public String qrAuthorize(@RequestParam("returnUrl") String returnUrl) {

        String url = projectUrlConfig.getAlipayOpenAuthorize() + "/sell/alipay/qrUserInfo";
        log.info(url);

        final String alipayOpenAuthUrl = "openauth.alipay.com";

        URI uri;
        try {
            uri = new URIBuilder()
                    .setScheme("https")
                    .setHost(alipayOpenAuthUrl)
                    .setPath("/oauth2/publicAppAuthorize.htm")
                    .setParameter("app_id", config.getAppId())
                    .setParameter("scope", "auth_user")
                    .setParameter("redirect_uri", url)
                    .setParameter("state", returnUrl)
                    .build();
        } catch (URISyntaxException e) {
            log.error("[支付宝网页授权] {}", e.getMessage(), e);
            throw new SellException(ResultEnum.ALIPAY_ERROR, e.getMessage());
        }

        log.info(uri.toString());
        return "redirect:" + uri;
    }

    @GetMapping("/qrUserInfo")
    public String qrUserInfo(@RequestParam("auth_code") String authCode,
                             @RequestParam("state") String returnUrl) {

        log.info("auth_code=[{}], state=[{}]", authCode, returnUrl);

        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setCode(authCode);
        request.setGrantType("authorization_code");

        try {
            AlipaySystemOauthTokenResponse oauthTokenResponse = alipayClient.execute(request);
            log.info("oauthTokenResponse: {}", JsonUtil.toJson(oauthTokenResponse));

            String accessToken = oauthTokenResponse.getAccessToken();

            AlipayUserInfoShareRequest userRequest = new AlipayUserInfoShareRequest();
            AlipayUserInfoShareResponse userInfoShareResponse = alipayClient.execute(userRequest, accessToken);

            log.info("userInfoShareResponse: {}", JsonUtil.toJson(userInfoShareResponse));

            String openId = userInfoShareResponse.getUserId();
            return "redirect:" + returnUrl + "?openid=" + openId;

        } catch (AlipayApiException e) {
            log.error("[支付宝网页授权] {}", e.getMessage(), e);
            throw new SellException(ResultEnum.ALIPAY_ERROR, e.getMessage());
        }
    }
}

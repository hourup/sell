package com.yaya.sell.controller;

import com.yaya.sell.enums.ResultEnum;
import com.yaya.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

/**
 * @author changhr2013
 * @date 2020/4/9
 */
@Slf4j
@Controller
@RequestMapping("/wechat")
public class WeChatController {

    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
        String url = "http://changhr.nat100.top/sell/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));
        log.info("[微信网页授权]获取 code，result=[{}]", redirectUrl);
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code, @RequestParam("state") String returnUrl) {
        log.info("进入 userInfo");
        WxMpOAuth2AccessToken accessToken;
        try {
            accessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("[微信网页授权]", e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR, e.getMessage());
        }
        String openId = accessToken.getOpenId();
        log.info("openId=[{}]", openId);

        return "redirect:" + returnUrl + "?openid=" + openId;
    }
}

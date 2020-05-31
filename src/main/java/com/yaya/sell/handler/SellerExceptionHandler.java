package com.yaya.sell.handler;

import com.yaya.sell.config.ProjectUrlConfig;
import com.yaya.sell.exception.SellerAuthorizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author yaomengya
 * @date 2020/5/10
 */
@ControllerAdvice
public class SellerExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @ExceptionHandler(SellerAuthorizeException.class)
    public ModelAndView handlerSellerAuthorizeException() {
        return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getSell())
                .concat("/sell/alipay/qrAuthorize")
                .concat("?returnUrl=")
                .concat(projectUrlConfig.getAlipayOpenAuthorize())
                .concat("/sell/seller/login"));
    }
}

package com.yaya.sell.filter;

import com.yaya.sell.threadlocal.LocalRequestContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
//@Component
public class AuthorityInterceptor extends HandlerInterceptorAdapter {

    private ServletContext servletContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        log.info("授权拦截器-[preHandle]-[{}]", request.getRequestURL());
        servletContext = request.getServletContext();
        LocalRequestContextHolder.setLocalRequestContext(servletContext, request, response);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model)
            throws Exception {
        log.info("授权拦截器-[postHandle]-[{}]", request.getRequestURL());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3)
            throws Exception {
        log.info("授权拦截器-[afterCompletion]-[{}]", request.getRequestURL());
        LocalRequestContextHolder.destroyLocalRequestContext();
    }
}

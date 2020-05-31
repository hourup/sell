package com.yaya.sell.threadlocal;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author yaomengya
 * @date 2020/3/19
 */
@Data
@Accessors(chain = true)
public class LocalRequestContext {

    private ServletContext servletContext;

    private HttpSession session;

    private HttpServletRequest request;

    private HttpServletResponse response;

    private Map<String, Cookie> cookies;

    private UserInfo userInfo;
}

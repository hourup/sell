package com.yaya.sell.threadlocal;

import org.springframework.util.CollectionUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author yaomengya
 * @date 2020/3/19
 */
public class LocalRequestContextHolder {

    private static final ThreadLocal<LocalRequestContext> CONTEXT_THREAD_LOCAL = new ThreadLocal<>();

    private LocalRequestContextHolder() {
    }

    public static void setLocalRequestContext(ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        LocalRequestContext localRequestContext = new LocalRequestContext()
                .setServletContext(context)
                .setRequest(request)
                .setResponse(response)
                .setSession(request.getSession());

        if (!CollectionUtils.isEmpty(CollectionUtils.arrayToList(request.getCookies()))) {
            Map<String, Cookie> cookieMap = Arrays.stream(request.getCookies())
                    .collect(Collectors.toMap(Cookie::getName, cookie -> cookie));
            localRequestContext.setCookies(cookieMap);
        }

        String[] split = request.getRequestURI().split("/");
        String id = split[split.length - 1];
        UserInfo userInfo = new UserInfo()
                .setId(id)
                .setUserName("hello")
                .setPassword("world")
                .setThreadName(Thread.currentThread().getName());
        localRequestContext.setUserInfo(userInfo);

        CONTEXT_THREAD_LOCAL.set(localRequestContext);
    }

    /**
     * 获取当前请求的上下文
     *
     * @return LocalRequestContext
     */
    public static Optional<LocalRequestContext> getLocalRequestContext() {
        return Optional.ofNullable(CONTEXT_THREAD_LOCAL.get());
    }

    /**
     * 清除当前线程请求上下文对象的引用（即让GC回收当前请求上下文对象）
     */
    public static void destroyLocalRequestContext() {
        CONTEXT_THREAD_LOCAL.remove();
    }
}

package com.yaya.sell.config;

import com.yaya.sell.filter.AuthorityInterceptor;
import com.yaya.sell.filter.ReadInterceptorConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Map;

/**
 * @author changhr
 * @create 2020-03-23 16:40
 */
//@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthorityInterceptor authorityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        ReadInterceptorConfig config = new ReadInterceptorConfig();
        Map<String, List<String>> configMap = config.readIniFile();

        List<String> urlList = configMap.get("authority-exclude-mapping");

        registry.addInterceptor(authorityInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(urlList);
    }
}

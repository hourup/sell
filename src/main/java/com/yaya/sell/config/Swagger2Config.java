package com.yaya.sell.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class Swagger2Config {
 
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.basePackage("com.yaya.sell.controller"))
                .paths(PathSelectors.any())
                .build();
    }
 
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("api文档")
                .description("restful 风格接口")
                // 服务条款网址
                .termsOfServiceUrl("https://www.lovemengya.cn")
                .version("1.0")
                .contact(new Contact("浩然","http://www.lovemengya.cn", "changhr2013@outlook.com"))
                .build();
    }
}
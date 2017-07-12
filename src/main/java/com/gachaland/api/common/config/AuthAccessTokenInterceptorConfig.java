package com.gachaland.api.common.config;

import com.gachaland.api.common.interceptor.AuthAccessTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by jhpark1220 on 2017. 7. 6..
 */
@Configuration
public class AuthAccessTokenInterceptorConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private AuthAccessTokenInterceptor authAccessTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authAccessTokenInterceptor)
                .excludePathPatterns("/swagger-resources/**");
//        registry.addInterceptor(new AuthAccessTokenInterceptor())
//                .excludePathPatterns("/swagger-resources/**");
    }
}


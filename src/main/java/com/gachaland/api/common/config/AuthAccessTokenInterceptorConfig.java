package com.gachaland.api.common.config;

import com.gachaland.api.common.interceptor.AuthAccessTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by jhpark1220 on 2017. 7. 6..
 */
@Configuration
public class AuthAccessTokenInterceptorConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthAccessTokenInterceptor())
                .excludePathPatterns("/swagger-resources/**");
    }
}


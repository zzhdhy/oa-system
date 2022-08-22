package com.zzh.oa_system.common.Interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 14:52
 * description:配置拦截器
 */
@Configuration
public class Interceptorconfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new recordInterceptor()).addPathPatterns("/**").excludePathPatterns("/logins").excludePathPatterns("/captcha");
        //registry.addInterceptor(new recordInterceptor()).addPathPatterns("/**").excludePathPatterns("/logins").excludePathPatterns("/index").excludePathPatterns("/test2");

    }
   /* final String[] notLoginInterceptPaths = {
            "/logins",
            "/error",
            "/login/**",
            "/captcha"
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new recordInterceptor()).addPathPatterns("/**").excludePathPatterns(notLoginInterceptPaths);
    }*/

}

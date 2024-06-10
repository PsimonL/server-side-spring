package com.example.serversidespring.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

//    @Bean
//    public FilterRegistrationBean<CustomCsrfFilter> csrfFilter() {
//        FilterRegistrationBean<CustomCsrfFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new CustomCsrfFilter());
//        registrationBean.addUrlPatterns("/*");
//        return registrationBean;
//    }

    @Bean
    public FilterRegistrationBean<ContentSecurityPolicyFilter> cspFilter() {
        FilterRegistrationBean<ContentSecurityPolicyFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ContentSecurityPolicyFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}

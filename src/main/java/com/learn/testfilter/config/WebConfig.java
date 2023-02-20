package com.learn.testfilter.config;

import com.learn.testfilter.filter.MyFilter;
import com.learn.testfilter.service.KeyServiceImpl;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<MyFilter> filterRegistrationBean() {
        FilterRegistrationBean<MyFilter> myFilter = new FilterRegistrationBean<>();
        myFilter.setFilter(new MyFilter(new KeyServiceImpl()));
        myFilter.addUrlPatterns("/*");
        return myFilter;
    }
}

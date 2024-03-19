package com.itranswarp.aop.before;

import com.itranswarp.sunny.annotation.Bean;
import com.itranswarp.sunny.annotation.ComponentScan;
import com.itranswarp.sunny.annotation.Configuration;
import com.itranswarp.sunny.aop.AroundProxyBeanPostProcessor;

@Configuration
@ComponentScan
public class BeforeApplication {

    @Bean
    AroundProxyBeanPostProcessor createAroundProxyBeanPostProcessor() {
        return new AroundProxyBeanPostProcessor();
    }
}
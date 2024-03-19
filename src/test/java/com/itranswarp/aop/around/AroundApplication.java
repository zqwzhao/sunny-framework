package com.itranswarp.aop.around;

import com.itranswarp.sunny.annotation.Bean;
import com.itranswarp.sunny.annotation.ComponentScan;
import com.itranswarp.sunny.annotation.Configuration;
import com.itranswarp.sunny.aop.AroundProxyBeanPostProcessor;

/**
 * @author zhaoqw
 * @date 2024/3/15
 */
@Configuration
@ComponentScan
public class AroundApplication {
    @Bean
    AroundProxyBeanPostProcessor aroundProxyBeanPostProcessor() {
        return new AroundProxyBeanPostProcessor();
    }
}

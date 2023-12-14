package com.itranswarp.scan.destroy;

import com.itranswarp.sunny.annotation.Bean;
import com.itranswarp.sunny.annotation.Configuration;
import com.itranswarp.sunny.annotation.Value;

/**
 * @author zhaoqw
 * @date 2023/9/25
 */
@Configuration
public class SpecifyDestroyConfiguration {
    @Bean(destroyMethod = "destroy")
    SpecifyDestroyBean createSpecifyDestroyBean(@Value("${app.title}") String appTitle) {
        return new SpecifyDestroyBean(appTitle);
    }
}

package com.itranswarp.scan.init;

import com.itranswarp.sunny.annotation.Bean;
import com.itranswarp.sunny.annotation.Configuration;
import com.itranswarp.sunny.annotation.Value;

/**
 * @author zhaoqw
 * @date 2023/9/25
 */
@Configuration
public class SpecifyInitConfiguration {
    @Bean(initMethod = "init")
    SpecifyInitBean createSpecifyInitBean(@Value("${app.title}") String appTitle, @Value("${app.version}") String appVersion) {
        return new SpecifyInitBean(appTitle, appVersion);
    }
}

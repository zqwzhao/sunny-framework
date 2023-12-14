package com.itranswarp.imported;

import com.itranswarp.sunny.annotation.Bean;
import com.itranswarp.sunny.annotation.Configuration;

import java.time.ZonedDateTime;

@Configuration
public class ZonedDateConfiguration {

    @Bean
    ZonedDateTime startZonedDateTime() {
        return ZonedDateTime.now();
    }
}
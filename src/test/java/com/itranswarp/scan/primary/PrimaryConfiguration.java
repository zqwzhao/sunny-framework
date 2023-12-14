package com.itranswarp.scan.primary;

import com.itranswarp.sunny.annotation.Bean;
import com.itranswarp.sunny.annotation.Configuration;
import com.itranswarp.sunny.annotation.Primary;

@Configuration
public class PrimaryConfiguration {

    @Primary
    @Bean
    DogBean husky() {
        return new DogBean("Husky");
    }

    @Bean
    DogBean teddy() {
        return new DogBean("Teddy");
    }
}
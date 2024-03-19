package com.itranswarp.aop.around;

import com.itranswarp.sunny.annotation.Around;
import com.itranswarp.sunny.annotation.Component;
import com.itranswarp.sunny.annotation.Value;

@Component
@Around("aroundInvocationHandler")
public class OriginBean {

    @Value("${customer.name}")
    public String name;

    @Polite
    public String hello() {
        return "Hello, " + name + ".";
    }

    public String morning() {
        return "Morning, " + name + ".";
    }
}
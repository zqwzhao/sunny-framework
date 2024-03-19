package com.itranswarp.scan.proxy;

import com.itranswarp.aop.around.Polite;
import com.itranswarp.sunny.annotation.Around;
import com.itranswarp.sunny.annotation.Component;
import com.itranswarp.sunny.annotation.Value;

@Component
@Around("aroundInvocationHandler")
public class OriginBean {

    @Value("${app.title}")
    public String name;

    @Value("${app.version}")
    public String version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Polite
    public String hello() {
        return "Hello, " + name + ".";
    }

    public String morning() {
        return "Morning, " + name + ".";
    }
}
package com.itranswarp.scan.init;

import com.itranswarp.sunny.annotation.Component;
import com.itranswarp.sunny.annotation.Value;
import jakarta.annotation.PostConstruct;

/**
 * @author zhaoqw
 * @date 2023/9/25
 */
@Component
public class AnnotationInitBean {
    @Value("${app.title}")
    String appTitle;

    @Value("${app.version}")
    String appVersion;

    public String appName;

    @PostConstruct
    void init() {
        this.appName = this.appTitle + " / " + this.appVersion;
    }
}

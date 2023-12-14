package com.itranswarp.scan.destroy;

import com.itranswarp.sunny.annotation.Component;
import com.itranswarp.sunny.annotation.Value;
import jakarta.annotation.PreDestroy;

/**
 * @author zhaoqw
 * @date 2023/9/25
 */
@Component
public class AnnotationDestroyBean {

    @Value("${app.title}")
    public String appTitle;

    @PreDestroy
    void destroy() {
        this.appTitle = null;
    }
}
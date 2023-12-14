package com.itranswarp.scan.destroy;

/**
 * @author zhaoqw
 * @date 2023/9/25
 */
public class SpecifyDestroyBean {
    public String appTitle;

    SpecifyDestroyBean(String appTitle) {
        this.appTitle = appTitle;
    }

    void destroy() {
        this.appTitle = null;
    }
}

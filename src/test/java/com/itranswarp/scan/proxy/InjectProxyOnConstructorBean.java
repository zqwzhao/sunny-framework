package com.itranswarp.scan.proxy;

import com.itranswarp.scan.proxy.OriginBean;
import com.itranswarp.sunny.annotation.Autowired;
import com.itranswarp.sunny.annotation.Component;

/**
 * @author zhaoqw
 * @date 2024/3/7
 */
@Component
public class InjectProxyOnConstructorBean {
    public final OriginBean injected;

    public InjectProxyOnConstructorBean(@Autowired OriginBean injected) {
        this.injected = injected;
    }
}

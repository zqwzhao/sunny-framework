package com.itranswarp.scan.proxy;

import com.itranswarp.scan.proxy.OriginBean;
import com.itranswarp.sunny.annotation.Autowired;
import com.itranswarp.sunny.annotation.Component;

@Component
public class InjectProxyOnPropertyBean {

    @Autowired
    public OriginBean injected;
}
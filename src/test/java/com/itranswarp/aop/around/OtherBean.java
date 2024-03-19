package com.itranswarp.aop.around;

import com.itranswarp.sunny.annotation.Autowired;
import com.itranswarp.sunny.annotation.Component;
import com.itranswarp.sunny.annotation.Order;

@Order(0)
@Component
public class OtherBean {

    public OriginBean origin;

    public OtherBean(@Autowired OriginBean origin) {
        this.origin = origin;
    }
}
package com.itranswarp.scan.nested;

import com.itranswarp.sunny.annotation.Component;

/**
 * @author zhaoqw
 * @date 2023/9/25
 */
@Component
public class OuterBean {
    @Component
    public static class NestedBean {

    }
}

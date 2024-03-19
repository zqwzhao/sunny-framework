package com.itranswarp.aop.around;

import com.itranswarp.sunny.context.AnnotationConfigApplicationContext;
import com.itranswarp.sunny.io.PropertyResolver;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * @author zhaoqw
 * @date 2024/3/15
 */
public class AroundProxyTest {
    @Test
    public void testAroundProxy() {
        try (var ctx = new AnnotationConfigApplicationContext(AroundApplication.class, createPropertyResolver())) {
            OriginBean proxy = ctx.getBean(OriginBean.class);
            // OriginBean$ByteBuddy$8NoD1FcQ
            System.out.println(proxy.getClass().getName());

            // proxy class, not origin class:
            assertNotSame(OriginBean.class, proxy.getClass());
            // proxy.name not injected:
            assertNull(proxy.name);

            assertEquals("Hello, Bob Around", proxy.hello());
            assertEquals("Morning, Bob.", proxy.morning());

            // test injected proxy:
            OtherBean other = ctx.getBean(OtherBean.class);
            assertSame(proxy, other.origin);
            assertEquals("Hello, Bob Around", other.origin.hello());
        }
    }

    PropertyResolver createPropertyResolver() {
        var ps = new Properties();
        ps.put("customer.name", "Bob");
        var pr = new PropertyResolver(ps);
        return pr;
    }
}

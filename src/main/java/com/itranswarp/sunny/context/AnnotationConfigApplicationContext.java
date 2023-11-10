package com.itranswarp.sunny.context;

import com.itranswarp.sunny.io.PropertyResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

/**
 * @author zhaoqw
 * @date 2023/11/10
 */
public class AnnotationConfigApplicationContext {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final PropertyResolver propertyResolver;
//    protected final Map<String, BeanDefinition> beans;

    public AnnotationConfigApplicationContext(Class<?> configClass, PropertyResolver propertyResolver) {
        this.propertyResolver = propertyResolver;
/*
        // 扫描获取所有Bean的Class类型:
        final Set<String> beanClassNames = scanForClassNames(configClass);

        // 创建Bean的定义:
        this.beans = createBeanDefinitions(beanClassNames);*/
    }
}

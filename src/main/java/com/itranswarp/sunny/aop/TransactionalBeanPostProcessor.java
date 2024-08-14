package com.itranswarp.sunny.aop;

import com.itranswarp.sunny.jdbc.annotation.Transactional;

/**
 * @author zhaoqw
 * @date 2024/3/15
 */
public class TransactionalBeanPostProcessor extends AnnotationProxyBeanPostProcessor<Transactional>{
}

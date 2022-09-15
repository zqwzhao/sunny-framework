package top.zhaoqw.springframework.factory.beans.factory.support;

import top.zhaoqw.springframework.factory.beans.factory.config.BeanDefinition;

/**
 * @author zhaoqw
 * @date 2022/08/31
 */
public interface BeanDefinitionRegistry {
  void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

  boolean containsBeanDefinition(String beanName);
}

package top.zhaoqw.springframework.factory.beans.factory.config;

import top.zhaoqw.springframework.factory.beans.BeansException;

/**
 * @author zhaoqw
 * @date 2022/09/13
 */
public interface BeanFactoryPostProcessor {
  /**
   * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
   *
   * @param beanFactory
   * @throws BeansException
   */
  void postProcessBeanFactory(ConfigurableBeanFactory beanFactory) throws BeansException;
}

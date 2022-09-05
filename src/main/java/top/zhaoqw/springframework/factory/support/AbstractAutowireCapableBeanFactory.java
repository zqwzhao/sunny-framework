package top.zhaoqw.springframework.factory.support;

import top.zhaoqw.springframework.factory.BeansException;
import top.zhaoqw.springframework.factory.config.BeanDefinition;

/**
 * 实例化Bean类(AbstractAutowireCapableBeanFactory)
 *
 * @author zhaoqw
 * @date 2022/08/31
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
  @Override
  protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
    Object bean = null;
    try {
      bean = beanDefinition.getBeanClass().newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      throw new BeansException("Instantiation of bean failed", e);
    }
    addSingleton(beanName, bean);
    return bean;
  }
}

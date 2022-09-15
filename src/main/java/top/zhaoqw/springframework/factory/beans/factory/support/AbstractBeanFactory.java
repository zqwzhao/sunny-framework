package top.zhaoqw.springframework.factory.beans.factory.support;

import top.zhaoqw.springframework.factory.BeanFactory;
import top.zhaoqw.springframework.factory.beans.BeansException;
import top.zhaoqw.springframework.factory.beans.factory.config.BeanDefinition;

/**
 * 抽象类定义模板方法(AbstractBeanFactory)
 * 继承了DefaultSingletonBeanRegistry具备了他的方法
 *
 * @author zhaoqw
 * @date 2022/08/31
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
  @Override
  public Object getBean(String name) throws BeansException {
    Object bean = getSingleton(name);
    if (null != bean) {
      return bean;
    }
    BeanDefinition beanDefinition = getBeanDefinition(name);
    return createBean(name, beanDefinition,null);
  }

  @Override
  public Object getBean(String name, Object... args) throws BeansException {
    return doGetBean(name, args);
  }

  @Override
  public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
    return (T) getBean(name);
  }

  protected <T> T doGetBean(final String name, final Object[] args) throws BeansException {
    Object bean = getSingleton(name);
    if (bean != null) {
      return (T) bean;
    }

    BeanDefinition beanDefinition = getBeanDefinition(name);
    return (T) createBean(name, beanDefinition, args);
  }
  /**
   * 获取bean定义
   *
   * @param beanName bean名称
   * @return BeanDefinition
   * @throws BeansException
   */
  protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

  /**
   * 创建bean对象
   *
   * @param beanName bean名称
   * @param beanDefinition bean定义对象
   * @return Object
   * @throws BeansException
   */
  protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}

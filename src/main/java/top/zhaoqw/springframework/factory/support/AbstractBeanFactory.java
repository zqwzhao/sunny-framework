package top.zhaoqw.springframework.factory.support;

import top.zhaoqw.springframework.factory.BeanFactory;
import top.zhaoqw.springframework.factory.BeansException;
import top.zhaoqw.springframework.factory.config.BeanDefinition;

/**
 * 抽象类定义模板方法(AbstractBeanFactory)
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
    return createBean(name, beanDefinition);
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
  protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;
}

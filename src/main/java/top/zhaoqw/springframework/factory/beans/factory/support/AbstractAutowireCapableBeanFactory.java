package top.zhaoqw.springframework.factory.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import top.zhaoqw.springframework.factory.beans.BeansException;;
import top.zhaoqw.springframework.factory.beans.PropertyValue;
import top.zhaoqw.springframework.factory.beans.PropertyValues;
import top.zhaoqw.springframework.factory.beans.factory.config.AutowireCapableBeanFactory;
import top.zhaoqw.springframework.factory.beans.factory.config.BeanDefinition;
import top.zhaoqw.springframework.factory.beans.factory.config.BeanPostProcessor;
import top.zhaoqw.springframework.factory.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 * 实例化Bean类(AbstractAutowireCapableBeanFactory)
 *
 * @author zhaoqw
 * @date 2022/08/31
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {
  /**
   * 初始化策略
   */
  private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

  @Override
  protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
    Object bean = null;
    try {
      bean = createBeanInstance(beanDefinition, beanName, args);
      // 给bean填充属性值
      applyPropertyValues(beanName, bean, beanDefinition);
      // 执行Bean的初始化方法和 BeanPostProcessor 的前置和后置处理方法
      bean =
    } catch (Exception e) {
      throw new BeansException("Instantiation of bean failed", e);
    }

    addSingleton(beanName, bean);
    return bean;
  }

  /**
   * 实初始化bean方法
   *
   * @param beanDefinition bean定义
   * @param beanName       bean名称
   * @param args           构造器参数
   * @return bean
   * @throws BeansException e
   */
  protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) throws BeansException {
    Constructor constructorToUse = null;
    Class<?> beanClass = beanDefinition.getBeanClass();
    Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
    for (Constructor ctor : declaredConstructors) {
      if (null != args && ctor.getParameterTypes().length == args.length) {
        constructorToUse = ctor;
        break;
      }
    }

    return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
  }

  /**
   * bean 属性填充
   */
  protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) throws BeansException {
    try {
      PropertyValues propertyValues = beanDefinition.getPropertyValues();
      for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
        String name = propertyValue.getName();
        Object value = propertyValue.getValue();

        if (value instanceof BeanReference) {
          BeanReference beanReference = (BeanReference) value;
          value = getBean(beanReference.getBeanName());
        }

        // 属性填充
        BeanUtil.setProperty(bean, name, value);
      }
    } catch (Exception e) {
      throw new BeansException("Error setting property values：" + beanName);
    }
  }

  private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
    // 1. 执行 BeanPostProcessor Before 处理
    // TODO 待完成内容：invokeInitMethods(beanName, wrappedBean, beanDefinition);
    return null;
  }

  @Override
  public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
    // TODO 待完成内容
    return null;
  }

  @Override
  public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
    Object result = existingBean;
    // TODO 待完成内容
    return null;
  }

  protected abstract BeanPostProcessor[] getBeanPostProcessors();



  public InstantiationStrategy getInstantiationStrategy() {
    return instantiationStrategy;
  }

  public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
    this.instantiationStrategy = instantiationStrategy;
  }
}

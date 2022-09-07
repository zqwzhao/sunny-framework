package top.zhaoqw.springframework.factory.support;

import top.zhaoqw.springframework.factory.BeansException;
import top.zhaoqw.springframework.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author zhaoqw
 * @date 2022/09/05
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {
  @Override
  public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException {
    Class clazz = beanDefinition.getBeanClass();
    //判断 Constructor 是否为空，如果为空则是无构造函数实例化，否则就是需要有构造函数的实例化。
    try {
      if (null != constructor) {
        // 有构造函数的实例化
        return clazz.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
      }else {
        return clazz.getDeclaredConstructor().newInstance();
      }
    } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
      throw new BeansException("Failed to instantiate [" + clazz.getName() + "]", e);
    }
  }
}

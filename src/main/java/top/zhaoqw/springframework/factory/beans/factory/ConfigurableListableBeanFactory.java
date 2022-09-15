package top.zhaoqw.springframework.factory.beans.factory;

import top.zhaoqw.springframework.factory.beans.BeansException;
import top.zhaoqw.springframework.factory.beans.factory.config.AutowireCapableBeanFactory;
import top.zhaoqw.springframework.factory.beans.factory.config.BeanDefinition;
import top.zhaoqw.springframework.factory.beans.factory.config.BeanPostProcessor;
import top.zhaoqw.springframework.factory.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author zhaoqw
 * @date 2022/09/13
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

  BeanDefinition getBeanDefinition(String beanName) throws BeansException;

  void preInstantiateSingletons() throws BeansException;

  void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}

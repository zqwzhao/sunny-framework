package top.zhaoqw.springframework.factory.beans.factory.config;

import top.zhaoqw.springframework.factory.beans.BeansException;


/**
 * @author zhaoqw
 * @date 2022/09/13
 */
public interface BeanPostProcessor {

  /**
   * 在 Bean 对象执行初始化方法之前，执行此方法
   *
   * @param bean bean
   * @param beanName beanName
   * @return Object
   * @throws BeansException e
   */
  Object poseProcessorBeforeInitialization(Object bean, String beanName) throws BeansException;

  /**
   * 在 Bean 对象执行初始化方法之后，执行此方法
   *
   * @param bean bean
   * @param beanName beanName
   * @return Object
   * @throws BeansException e
   */
  Object postProcessorAfterInitialization(Object bean, String beanName) throws BeansException;
}

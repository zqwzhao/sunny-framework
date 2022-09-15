package top.zhaoqw.springframework.factory.beans.factory.config;

import top.zhaoqw.springframework.factory.BeanFactory;
import top.zhaoqw.springframework.factory.beans.BeansException;

/**
 * @author zhaoqw
 * @date 2022/09/13
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
  /**
   * 执行 BeanPostProcessors 接口实现类的 postProcessBeforeInitialization 方法
   *
   * @param existingBean
   * @param beanName
   * @return
   * @throws BeansException
   */
  Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

  /**
   * 执行 BeanPostProcessors 接口实现类的 postProcessorsAfterInitialization 方法
   *
   * @param existingBean
   * @param beanName
   * @return
   * @throws BeansException
   */
  Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;
}

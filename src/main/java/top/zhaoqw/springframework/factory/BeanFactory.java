package top.zhaoqw.springframework.factory;

import top.zhaoqw.springframework.factory.beans.BeansException;

/**
 * @author zhaoqw
 * @date 2022/08/31
 */
public interface BeanFactory {
  Object getBean(String name) throws BeansException;

  Object getBean(String name, Object... args) throws BeansException;

  <T> T getBean(String name, Class<T> requiredType) throws BeansException;
}

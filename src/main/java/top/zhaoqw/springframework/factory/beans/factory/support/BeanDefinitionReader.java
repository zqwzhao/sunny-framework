package top.zhaoqw.springframework.factory.beans.factory.support;

import top.zhaoqw.springframework.factory.beans.BeansException;
import top.zhaoqw.springframework.factory.core.io.Resource;
import top.zhaoqw.springframework.factory.core.io.ResourceLoader;

/**
 * @author zhaoqw
 * @date 2022/09/09
 */
public interface BeanDefinitionReader {
  BeanDefinitionRegistry getRegistry();

  ResourceLoader getResourceLoader();

  void loadBeanDefinitions(Resource resource) throws BeansException;

  void loadBeanDefinitions(Resource... resources) throws BeansException;

  void loadBeanDefinitions(String location) throws BeansException;

  void loadBeanDefinitions(String... locations) throws BeansException;
}

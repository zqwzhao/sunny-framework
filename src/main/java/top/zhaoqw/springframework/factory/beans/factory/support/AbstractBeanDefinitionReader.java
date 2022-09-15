package top.zhaoqw.springframework.factory.beans.factory.support;

import top.zhaoqw.springframework.factory.core.io.DefaultResourceLoader;
import top.zhaoqw.springframework.factory.core.io.ResourceLoader;

/**
 * @author zhaoqw
 * @date 2022/09/09
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
  private final BeanDefinitionRegistry beanDefinitionRegistry;

  private ResourceLoader resourceLoader;

  public AbstractBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
    this(beanDefinitionRegistry, new DefaultResourceLoader());
  }

  public AbstractBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry, ResourceLoader resourceLoader) {
    this.beanDefinitionRegistry = beanDefinitionRegistry;
    this.resourceLoader = resourceLoader;
  }

  @Override
  public BeanDefinitionRegistry getRegistry() {
    return beanDefinitionRegistry;
  }

  @Override
  public ResourceLoader getResourceLoader() {
    return resourceLoader;
  }
}

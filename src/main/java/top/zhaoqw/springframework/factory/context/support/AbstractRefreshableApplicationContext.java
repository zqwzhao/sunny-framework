package top.zhaoqw.springframework.factory.context.support;

import top.zhaoqw.springframework.factory.beans.BeansException;
import top.zhaoqw.springframework.factory.beans.factory.ConfigurableListableBeanFactory;
import top.zhaoqw.springframework.factory.beans.factory.support.DefaultListableBeanFactory;
import top.zhaoqw.springframework.factory.context.ConfigurableApplicationContext;

import java.util.Map;

/**
 * @author zhaoqw
 * @date 2022/09/13
 */
public abstract  class AbstractRefreshableApplicationContext extends AbstractApplicationContext{

  private DefaultListableBeanFactory beanFactory;


  private DefaultListableBeanFactory createBeanFactory() {
    return new DefaultListableBeanFactory();
  }


  @Override
  protected void refreshBeanfactory() throws BeansException {
    DefaultListableBeanFactory beanFactory = createBeanFactory();
    loadBeanDefinitions(beanFactory);
    this.beanFactory = beanFactory;
  }

  protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException;

  @Override
  protected ConfigurableListableBeanFactory getBeanFactory() {
    return beanFactory;
  }
}

package top.zhaoqw.springframework.factory.context.support;

import top.zhaoqw.springframework.factory.beans.BeansException;
import top.zhaoqw.springframework.factory.beans.factory.ConfigurableListableBeanFactory;
import top.zhaoqw.springframework.factory.beans.factory.config.BeanFactoryPostProcessor;
import top.zhaoqw.springframework.factory.beans.factory.config.BeanPostProcessor;
import top.zhaoqw.springframework.factory.context.ConfigurableApplicationContext;
import top.zhaoqw.springframework.factory.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @author zhaoqw
 * @date 2022/09/13
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
  @Override
  public void refresh() throws BeansException {
    //1. 创建BeanFactory，加载BeanDefinition
    refreshBeanfactory();
    //2. 获取BeanFactory
    ConfigurableListableBeanFactory  beanFactory = getBeanFactory();

    //3. 在Bean 实例化之前，执行BeanFactoryPostProcessor (Invoke factory processors registered as beans in the context.)
    invokeBeanFactoryPostProcessor(beanFactory);
    //4. BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
    registerBeanPostProcessors(beanFactory);

    // 5. 提前实例化单例Bean对象
    beanFactory.preInstantiateSingletons();
  }

  protected abstract void refreshBeanfactory() throws BeansException;

  protected abstract ConfigurableListableBeanFactory  getBeanFactory();

  private void invokeBeanFactoryPostProcessor(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
    for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
      beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
    }
  }

  private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
    for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
      beanFactory.addBeanPostProcessor(beanPostProcessor);
    }
  }

  //... getBean、getBeansOfType、getBeanDefinitionNames 方法

  @Override
  public String[] getBeanDefinitionNames() {
    return getBeanFactory().getBeanDefinitionNames();
  }

  @Override
  public Object getBean(String name, Object... args) throws BeansException {
    return getBeanFactory().getBean(name, args);
  }

  @Override
  public Object getBean(String name) throws BeansException {
    return getBeanFactory().getBean(name);
  }

  @Override
  public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
    return getBeanFactory().getBean(name, requiredType);
  }


  @Override
  public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
    return getBeanFactory().getBeansOfType(type);
  }
}

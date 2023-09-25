package top.zhaoqw.springframework.factory.beans.factory.support;

import top.zhaoqw.springframework.factory.beans.BeansException;
import top.zhaoqw.springframework.factory.beans.factory.ConfigurableListableBeanFactory;
import top.zhaoqw.springframework.factory.beans.factory.config.BeanDefinition;
import top.zhaoqw.springframework.factory.beans.factory.config.BeanPostProcessor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DefaultListableBeanFactory 是AbstractBeanFactory具体的间接实现类
 * DefaultListableBeanFactory 继承了 AbstractAutowireCapableBeanFactory 类，也
 * 就具备了接口 BeanFactory 和 AbstractBeanFactory 等一连串的功能实现。
 * 它实现了AbstractBeanFactory 中定义的抽象方法getBeanDefinition
 * @author zhaoqw
 * @date 2022/08/31
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory  {

  private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

  @Override
  public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
    beanDefinitionMap.put(beanName, beanDefinition);
  }

  @Override
  public  BeanDefinition getBeanDefinition(String beanName) throws BeansException {
    BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
    if (beanDefinition == null) throw new BeansException("No bean named '" + beanName + "' is defined");
    return beanDefinition;
  }

  @Override
  public void preInstantiateSingletons() {
    beanDefinitionMap.keySet().forEach(beanName -> {
      try {
        this.getBean(beanName);
      } catch (BeansException e) {
        e.printStackTrace();
      }
    });
  }

  @Override
  public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {

  }


  @Override
  public boolean containsBeanDefinition(String beanName) {
    return beanDefinitionMap.containsKey(beanName);
  }

  @Override
  public <T> Map<String, T> getBeansOfType(Class<T> type) {
    Map<String, T> result = new HashMap<>();
    beanDefinitionMap.forEach((beanName, beanDefinition) -> {
      Class beanClass = beanDefinition.getBeanClass();
      if (type.isAssignableFrom(beanClass)) {
        try {
          result.put(beanName, (T) getBean(beanName));
        } catch (BeansException e) {
          e.printStackTrace();
        }
      }
    });
    return result;
  }

  @Override
  public String[] getBeanDefinitionNames() {
    return beanDefinitionMap.keySet().toArray(new String[0]);
  }

  @Override
  protected BeanPostProcessor[] getBeanPostProcessors() {
    return new BeanPostProcessor[0];
  }
}

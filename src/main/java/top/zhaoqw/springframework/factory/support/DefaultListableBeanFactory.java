package top.zhaoqw.springframework.factory.support;

import top.zhaoqw.springframework.factory.BeansException;
import top.zhaoqw.springframework.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * DefaultListableBeanFactory 是AbstractBeanFactory具体的间接实现类
 * DefaultListableBeanFactory 继承了 AbstractAutowireCapableBeanFactory 类，也
 * 就具备了接口 BeanFactory 和 AbstractBeanFactory 等一连串的功能实现。
 * 它实现了AbstractBeanFactory 中定义的抽象方法getBeanDefinition
 * @author zhaoqw
 * @date 2022/08/31
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

  private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

  @Override
  protected BeanDefinition getBeanDefinition(String beanName) throws BeansException {
    BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
    if (beanDefinition == null) throw new BeansException("No bean named '" + beanName + "' is defined");
    return beanDefinition;
  }

  @Override
  public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
    beanDefinitionMap.put(beanName, beanDefinition);
  }
}

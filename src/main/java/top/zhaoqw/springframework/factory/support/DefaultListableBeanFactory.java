package top.zhaoqw.springframework.factory.support;

import top.zhaoqw.springframework.factory.BeansException;
import top.zhaoqw.springframework.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
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

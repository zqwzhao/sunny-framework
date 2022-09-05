package top.zhaoqw.springframework.factory.support;

import top.zhaoqw.springframework.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaoqw
 * @date 2022/08/31
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
  private Map<String, Object> singletonObjects = new HashMap<>();


  @Override
  public Object getSingleton(String beanName) {
    return singletonObjects.get(beanName);
  }

  protected void addSingleton(String beanName, Object singletonObject) {
    singletonObjects.put(beanName, singletonObject);
  }
}
package top.zhaoqw.springframework.factory.beans.factory;

import top.zhaoqw.springframework.factory.BeanFactory;
import top.zhaoqw.springframework.factory.beans.BeansException;

import java.util.Map;

/**
 * @author zhaoqw
 * @date 2022/09/13
 */
public interface ListableBeanFactory extends BeanFactory {
  /**
   * 按照类型返回 Bean 实例
   *
   * @param type type
   * @param <T> T
   * @return bean实例
   * @throws BeansException e
   */
  <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

  /**
   * 返回注册表中所有的Bean名称
   * @return 所有的Bean名称
   */
  String[] getBeanDefinitionNames();
}

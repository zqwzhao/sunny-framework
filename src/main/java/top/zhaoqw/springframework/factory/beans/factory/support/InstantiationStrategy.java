package top.zhaoqw.springframework.factory.beans.factory.support;

import top.zhaoqw.springframework.factory.beans.BeansException;
import top.zhaoqw.springframework.factory.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 实例化策略
 *
 * @author zhaoqw
 * @date 2022/09/05
 */
public interface InstantiationStrategy {
  /**
   * 初始化
   *
   * @param beanDefinition beanDefinition
   * @param beanName beanName
   * @param constructor 有这个参数的目的就是为了拿到符合入参信息相对应的构造函数
   * @param args args
   * @return Object对象
   */
  Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException;
}

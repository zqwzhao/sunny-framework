package top.zhaoqw.springframework.factory.beans.factory.config;

import top.zhaoqw.springframework.factory.beans.factory.HierarchicalBeanFactory;

/**
 * @author zhaoqw
 * @date 2022/09/13
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
  String SCOPE_SINGLETON = "singleton";

  String SCOPE_PROTOTYPE = "prototype";
}

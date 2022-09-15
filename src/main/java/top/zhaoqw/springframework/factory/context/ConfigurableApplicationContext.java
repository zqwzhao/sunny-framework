package top.zhaoqw.springframework.factory.context;

import top.zhaoqw.springframework.factory.beans.BeansException;

/**
 * @author zhaoqw
 * @date 2022/09/13
 */
public interface ConfigurableApplicationContext extends ApplicationContext {
  /**
   * 刷新容器
   *
   * @throws BeansException
   */
  void refresh() throws BeansException;
}

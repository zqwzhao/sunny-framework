package top.zhaoqw.springframework.factory.config;

/**
 * 单例注册接口定义
 * @author zhaoqw
 * @date 2022/08/31
 */
public interface SingletonBeanRegistry {
  Object getSingleton(String beanName);
}

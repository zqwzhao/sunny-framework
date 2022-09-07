package top.zhaoqw.springframework.factory;

/**
 * @author zhaoqw
 * @date 2022/08/31
 */
public interface BeanFactory {
  Object getBean(String name) throws BeansException;

  Object getBean(String name, Object... args) throws BeansException;
}

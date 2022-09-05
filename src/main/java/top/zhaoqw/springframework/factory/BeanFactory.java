package top.zhaoqw.springframework.factory;

/**
 * @author zhaoqw
 * @date 2022/08/31
 */
public interface BeanFactory {
  public Object getBean(String name) throws BeansException;
}

package top.zhaoqw.springframework.factory.config;

/**
 * @author zhaoqw
 * @date 2022/09/08
 */
public class BeanReference {
  /**
   * bean名称
   */
  private final String beanName;

  public String getBeanName() {
    return beanName;
  }

  public BeanReference(String beanName) {
    this.beanName = beanName;
  }
}

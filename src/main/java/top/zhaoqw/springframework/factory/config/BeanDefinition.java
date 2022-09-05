package top.zhaoqw.springframework.factory.config;

/**
 * @author zhaoqw
 * @date 2022/08/31
 */
public class BeanDefinition {
  private Class beanClass;

  public BeanDefinition(Class beanClass) {
    this.beanClass = beanClass;
  }

  public Class getBeanClass() {
    return beanClass;
  }

  public void setBeanClass(Class beanClass) {
    this.beanClass = beanClass;
  }
}

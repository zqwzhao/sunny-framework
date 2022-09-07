package top.zhaoqw.springframework.factory.config;

import top.zhaoqw.springframework.factory.beans.PropertyValues;

/**
 * @author zhaoqw
 * @date 2022/08/31
 */
public class BeanDefinition {
  private Class beanClass;

  private PropertyValues propertyValues;

  public BeanDefinition(Class beanClass) {
    this.beanClass = beanClass;
  }

  public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
    this.beanClass = beanClass;
    this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
  }

  public Class getBeanClass() {
    return beanClass;
  }

  public void setBeanClass(Class beanClass) {
    this.beanClass = beanClass;
  }
}

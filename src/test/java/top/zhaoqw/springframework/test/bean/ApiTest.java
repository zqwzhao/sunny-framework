package top.zhaoqw.springframework.test.bean;

import org.junit.jupiter.api.Test;
import top.zhaoqw.springframework.factory.beans.BeansException;
import top.zhaoqw.springframework.factory.beans.PropertyValue;
import top.zhaoqw.springframework.factory.beans.PropertyValues;
import top.zhaoqw.springframework.factory.beans.factory.config.BeanDefinition;
import top.zhaoqw.springframework.factory.beans.factory.config.BeanReference;
import top.zhaoqw.springframework.factory.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author zhaoqw
 * @date 2022/08/31
 */
public class ApiTest {
  @Test
  public void test_BeanFactory() throws BeansException {
    // 1.初始化 BeanFactory
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

    // UserService 属性设定
    PropertyValues propertyValues = new PropertyValues();
    propertyValues.addPropertyValue(new PropertyValue("name", "zhaoqw"));
    propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

    // 2.注册 bean
    BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
    // 就是将beanDefinition放进DefaultListableBeanFactory的beanDefinitionMap
    beanFactory.registerBeanDefinition("userService", beanDefinition);
    // 3.第一次获取 bean
    /**
     * 第一次获取 bean 调用的是AbstractBeanFactory 里面定义的getBean
     * AbstractBeanFactory继承DefaultSingletonBeanRegistry ，会先到(Map<String, Object>) singletonObjects里面找
     * 第一次获取设个Bean，没找到，就用
     */
    UserService userService = (UserService) beanFactory.getBean("userService");
    userService.queryUserInfo();
    UserDao userDao = userService.getUserDao();
    String name = userDao.queryUserName("10001");
    System.out.println(name);
  }
}

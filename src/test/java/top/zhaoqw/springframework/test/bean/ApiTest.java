package top.zhaoqw.springframework.test.bean;

import org.junit.Test;
import top.zhaoqw.springframework.factory.BeansException;
import top.zhaoqw.springframework.factory.config.BeanDefinition;
import top.zhaoqw.springframework.factory.support.DefaultListableBeanFactory;

/**
 * @author zhaoqw
 * @date 2022/08/31
 */
public class ApiTest {
  @Test
  public void test_BeanFactory() throws BeansException {
    // 1.初始化 BeanFactory
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    // 2.注册 bean
    BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
    beanFactory.registerBeanDefinition("userService", beanDefinition);
    // 3.第一次获取 bean
    UserService userService = (UserService) beanFactory.getBean("userService");
    userService.queryUserInfo();
    // 4.第二次获取 bean from Singleton
    UserService userService_singleton = (UserService) beanFactory.getBean("userService");
    userService_singleton.queryUserInfo();
  }
}

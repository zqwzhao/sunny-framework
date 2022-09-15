package top.zhaoqw.springframework.test.bean;

import cn.hutool.core.io.IoUtil;
import org.junit.Before;
import org.junit.Test;
import top.zhaoqw.springframework.factory.beans.BeansException;
import top.zhaoqw.springframework.factory.core.io.DefaultResourceLoader;
import top.zhaoqw.springframework.factory.core.io.Resource;
import top.zhaoqw.springframework.factory.beans.factory.support.DefaultListableBeanFactory;
import top.zhaoqw.springframework.factory.beans.factory.xml.XmlBeanDefinitionReader;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhaoqw
 * @date 2022/09/13
 */
public class ResourceLoaderTest {
  private DefaultResourceLoader resourceLoader;

  private final String msg;

  public ResourceLoaderTest(String msg) {
    this.msg = msg;
  }


  @Before
  public void init() {
    resourceLoader = new DefaultResourceLoader();
  }

  @Test
  public void testClassPath() {
    Resource resource = resourceLoader.getResource("classpath:important.properties");
    InputStream inputStream = null;
    try {
      inputStream = resource.getInputStream();
    } catch (IOException e) {
      e.printStackTrace();
    }
    String msg = IoUtil.readUtf8(inputStream);
    System.out.println(msg);
  }

  @Test
  public void testFileSystem() throws IOException {
    Resource resource = resourceLoader.getResource("src/main/resources/important.properties");
    InputStream inputStream = null;
    inputStream = resource.getInputStream();
    String s = IoUtil.readUtf8(inputStream);
    System.out.println(s);
  }

  @Test
  public void testXmlLoadBean() throws BeansException {
    //1. 初始化BeanFactory
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

    //2. 读取配置文件，注册Bean
    XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
    xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

    //3. 获取bean 调用方法
    UserService userService = beanFactory.getBean("userService", UserService.class);
    String result = userService.queryUserInfoByUId();
    System.out.println("测试结果：" + result);


  }
}

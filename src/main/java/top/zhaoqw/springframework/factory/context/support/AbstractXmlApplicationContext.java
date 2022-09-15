package top.zhaoqw.springframework.factory.context.support;

import top.zhaoqw.springframework.factory.beans.BeansException;
import top.zhaoqw.springframework.factory.beans.factory.support.DefaultListableBeanFactory;
import top.zhaoqw.springframework.factory.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author zhaoqw
 * @date 2022/09/13
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {
  @Override
  protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException {
    XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
    String[] configLocations = getConfigLocations();
    if (null != configLocations) {
      xmlBeanDefinitionReader.loadBeanDefinitions(configLocations);
    }
  }

  protected abstract String[] getConfigLocations();
}

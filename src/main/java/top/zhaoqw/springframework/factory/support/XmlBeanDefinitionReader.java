package top.zhaoqw.springframework.factory.support;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import top.zhaoqw.springframework.factory.BeansException;
import top.zhaoqw.springframework.factory.beans.PropertyValue;
import top.zhaoqw.springframework.factory.config.BeanDefinition;
import top.zhaoqw.springframework.factory.config.BeanReference;
import top.zhaoqw.springframework.factory.core.io.Resource;
import top.zhaoqw.springframework.factory.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

/**
 * XML Bean资源获取器
 *
 * @author zhaoqw
 * @date 2022/09/09
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
  public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
    super(beanDefinitionRegistry);
  }

  public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry, ResourceLoader resourceLoader) {
    super(beanDefinitionRegistry, resourceLoader);
  }

  @Override
  public void loadBeanDefinitions(Resource resource) throws BeansException {
    try{
      try(InputStream inputStream = resource.getInputStream()) {
        doLoadBeanDefinitions(inputStream);
      }
    } catch (IOException | ClassNotFoundException e) {
      throw new BeansException("IOException parsing XML document from" + resource, e);
    }
  }

  @Override
  public void loadBeanDefinitions(Resource... resources) throws BeansException {
    for (Resource resource : resources) {
      loadBeanDefinitions(resource);
    }
  }

  @Override
  public void loadBeanDefinitions(String location) throws BeansException {
    ResourceLoader resourceLoader = getResourceLoader();
    Resource resource = resourceLoader.getResource(location);
    loadBeanDefinitions(resource);
  }

  protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException, BeansException {
    Document document = XmlUtil.readXML(inputStream);
    Element root = document.getDocumentElement();
    NodeList childNodes = root.getChildNodes();

    for (int i = 0; i < childNodes.getLength(); i++) {
      // 判断元素
      if(!(childNodes.item(i) instanceof Element)) {
        continue;
      }
      // 判断对象
      if(!"bean".equals(childNodes.item(i).getNodeName())) continue;

      // 解析标签
      Element bean = (Element) childNodes.item(i);
      String id = bean.getAttribute("id");
      String name = bean.getAttribute("name");
      String className = bean.getAttribute("class");

      // 获取Class，方便获取类中的名称
      Class<?> clazz = Class.forName(className);
      // 优先级id > name
      String beanName = StrUtil.isNotEmpty(id) ? id : name;

      if(StrUtil.isEmpty(beanName)) {
        beanName = StrUtil.lowerFirst(clazz.getSimpleName());
      }

      // 定义Bean
      BeanDefinition beanDefinition = new BeanDefinition(clazz);

      // 获取属性并填充
      for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
        if(!(bean.getChildNodes().item(j) instanceof Element)) continue;
        if (!"property".equals(bean.getChildNodes().item(j).getNodeName())) continue;

        // 解析标签
        Element property =(Element) bean.getChildNodes().item(j);
        String attrName = property.getAttribute("name");
        String attrValue = property.getAttribute("value");
        String attrRef = property.getAttribute("ref");

        // 获取属性值：引入对象，值对象
        Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;

        // 创建属性值
        PropertyValue propertyValue = new PropertyValue(attrName, value);
        beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
      }
      if (getRegistry().containsBeanDefinition(beanName)) {
        throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
      }
      // 注册 BeanDefinition
      getRegistry().registerBeanDefinition(beanName, beanDefinition);
    }

  }
}

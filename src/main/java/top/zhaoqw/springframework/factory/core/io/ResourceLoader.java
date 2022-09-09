package top.zhaoqw.springframework.factory.core.io;

/**
 * 资源加载器
 *
 * @author zhaoqw
 * @date 2022/09/09
 */
public interface ResourceLoader {

  /**
   * Pseudo URL prefix for loading from the class path: "classpath:"
   */
  String CLASSPATH_URL_PREFIX = "classpath:";

  Resource getResource(String location);
}

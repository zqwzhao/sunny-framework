package top.zhaoqw.springframework.factory.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源接口
 *
 * @author zhaoqw
 * @date 2022/09/09
 */
public interface Resource {

  InputStream getInputStream() throws IOException;

}

package top.zhaoqw.springframework.factory.core.io;

import cn.hutool.core.lang.Assert;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author zhaoqw
 * @date 2022/09/09
 */
public class UrlResource implements Resource {
  private final URL url;

  public UrlResource(URL url) {
    Assert.notNull(url, "Url must not be null");
    this.url = url;
  }

  @Override
  public InputStream getInputStream() throws IOException {
    URLConnection urlConnection = this.url.openConnection();
    try {
      return urlConnection.getInputStream();
    } catch (IOException exception) {
      if (urlConnection instanceof HttpURLConnection) {
        ((HttpURLConnection) urlConnection).disconnect();
      }
      throw  exception;
    }
  }
}

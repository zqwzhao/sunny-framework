package top.zhaoqw.springframework.factory.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhaoqw
 * @date 2022/09/09
 */
public class FileSystemResource implements Resource {
  private final File file;

  private final String path;

  public FileSystemResource(File file) {
    this.file = file;
    this.path = file.getPath();
  }

  public FileSystemResource(File file, String path) {
    this.file = file;
    this.path = path;
  }

  @Override
  public InputStream getInputStream() throws IOException {
    return new FileInputStream(path);
  }

  public final String getPath() {
    return this.path;
  }
}

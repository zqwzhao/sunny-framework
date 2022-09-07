package top.zhaoqw.springframework.factory.beans;

/**
 * @author zhaoqw
 * @date 2022/09/07
 */
public class PropertyValue {
  private final String name;
  private final Object value;

  public PropertyValue(String name, Object value) {
    this.name = name;
    this.value = value;
  }

  public String getName() {
    return name;
  }

  public Object getValue() {
    return value;
  }
}

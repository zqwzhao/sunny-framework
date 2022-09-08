package top.zhaoqw.springframework.factory.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * 属性列表
 *
 * @author zhaoqw
 * @date 2022/09/07
 */
public class PropertyValues {
  private final List<PropertyValue> propertyValueList = new ArrayList<>();

  public void addPropertyValue(PropertyValue propertyValue) {
    this.propertyValueList.add(propertyValue);
  }

  public PropertyValue[] getPropertyValues() {
    return this.propertyValueList.toArray(new PropertyValue[0]);
  }

  public PropertyValue getPropertyValue(String propertyName) {
    for (PropertyValue propertyValue : this.propertyValueList) {
      if (propertyValue.getName().equals(propertyName)) {
        return propertyValue;
      }
    }
    return null;
  }
}

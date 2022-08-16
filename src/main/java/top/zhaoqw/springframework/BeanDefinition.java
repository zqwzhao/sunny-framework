package top.zhaoqw.springframework;

/**
 * Bean定义初步
 *
 * @author zhaoqw
 * @date 2022/8/6
 */
public class BeanDefinition {
    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }

}

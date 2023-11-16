package com.itranswarp.sunny.context;

import com.itranswarp.sunny.annotation.Component;
import com.itranswarp.sunny.annotation.Order;
import com.itranswarp.sunny.annotation.Primary;
import com.itranswarp.sunny.exception.BeanCreationException;
import com.itranswarp.sunny.exception.BeanDefinitionException;
import com.itranswarp.sunny.io.PropertyResolver;
import com.itranswarp.sunny.utils.ClassUtils;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author zhaoqw
 * @date 2023/11/10
 */
public class AnnotationConfigApplicationContext {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final PropertyResolver propertyResolver;
    protected final Map<String, BeanDefinition> beans;

    public AnnotationConfigApplicationContext(Class<?> configClass, PropertyResolver propertyResolver) {
        this.propertyResolver = propertyResolver;
        // 扫描获取所有Bean的Class类型:
        final Set<String> beanClassNames = scanForClassNames(configClass);

        // 创建Bean的定义:
        this.beans = createBeanDefinitions(beanClassNames);
    }

    private Map<String, BeanDefinition> createBeanDefinitions(Set<String> beanClassNames) {
        Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
        for (String beanClassName : beanClassNames) {
            // 获取class
            Class<?> clazz = null;
            try {
                clazz = Class.forName(beanClassName);
            } catch (ClassNotFoundException e) {
                throw new BeanCreationException(e);
            }
            // 注解，枚举，接口，Record档案类跳过
            if (clazz.isAnnotation() || clazz.isEnum() || clazz.isInterface() || clazz.isRecord()) {
                continue;
            }
            // 是否标注了@Component
            Component component = ClassUtils.findAnnotation(clazz, Component.class);
            if (component != null) {
                logger.atDebug().log("found component: {}", clazz.getName());
                int modifiers = clazz.getModifiers();
                // 类的修饰符判断
                if (Modifier.isAbstract(modifiers)) {
                    throw new BeanDefinitionException("@Component class " + clazz.getName() + " must not be abstract.");
                }
                if (Modifier.isPrivate(modifiers)) {
                    throw new BeanDefinitionException("@Component class " + clazz.getName() + " must not be private.");
                }

                String beanName = ClassUtils.getBeanName(clazz);
                BeanDefinition beanDefinition = new BeanDefinition(beanName, clazz, getSuitableConstructor(clazz), getOrder(clazz), clazz.isAnnotationPresent(Primary.class),
                        // named init / destroy method:
                        null, null,
                        // init method:
                        ClassUtils.findAnnotationMethod(clazz, PostConstruct.class),
                        // destroy method:
                        ClassUtils.findAnnotationMethod(clazz, PreDestroy.class));
                addBeanDefinitions(beanDefinitionMap, beanDefinition);
                logger.atDebug().log("define bean: {}", beanDefinition);

            }
        }
        return beanDefinitionMap;
    }

    private void addBeanDefinitions(Map<String, BeanDefinition> beanDefinitionMap, BeanDefinition beanDefinition) {
        if (beanDefinitionMap.put(beanDefinition.getName(), beanDefinition) != null) {
            throw new BeanDefinitionException("Duplicate bean name: " + beanDefinition.getName());
        }
    }

    /**
     * Get order by:
     *
     * <code>
     * @Order(100)
     * @Component
     * public class Hello {}
     * </code>
     */
    int getOrder(Class<?> clazz) {
        Order order = clazz.getAnnotation(Order.class);
        return order == null ? Integer.MAX_VALUE : order.value();
    }

    /**
     * Get order by:
     *
     * <code>
     * @Order(100)
     * @Bean
     * Hello createHello() {
     *     return new Hello();
     * }
     * </code>
     */
    int getOrder(Method method) {
        Order order = method.getAnnotation(Order.class);
        return order == null ? Integer.MAX_VALUE : order.value();
    }

    private Constructor getSuitableConstructor(Class<?> clazz) {
        Constructor<?>[] cons = clazz.getConstructors();
        if (cons.length == 0) {
            cons = clazz.getDeclaredConstructors();
            if (cons.length != 1) {
                throw new BeanDefinitionException("More than one constructor found in class " + clazz.getName() + ".");
            }
        }
        if (cons.length != 1) {
            throw new BeanDefinitionException("More than one public constructor found in class " + clazz.getName() + ".");
        }
        return cons[0];
    }

    private Set<String> scanForClassNames(Class<?> configClass) {
        return null;
    }
}

package com.itranswarp.sunny.context;

import com.itranswarp.sunny.annotation.Bean;
import com.itranswarp.sunny.annotation.Component;
import com.itranswarp.sunny.annotation.ComponentScan;
import com.itranswarp.sunny.annotation.Configuration;
import com.itranswarp.sunny.annotation.Import;
import com.itranswarp.sunny.annotation.Order;
import com.itranswarp.sunny.annotation.Primary;
import com.itranswarp.sunny.exception.BeanCreationException;
import com.itranswarp.sunny.exception.BeanDefinitionException;
import com.itranswarp.sunny.exception.BeanNotOfRequiredTypeException;
import com.itranswarp.sunny.exception.NoUniqueBeanDefinitionException;
import com.itranswarp.sunny.io.PropertyResolver;
import com.itranswarp.sunny.io.ResourceResolver;
import com.itranswarp.sunny.utils.ClassUtils;
import jakarta.annotation.Nullable;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
                Configuration configuration = ClassUtils.findAnnotation(clazz, Configuration.class);
                if (configuration != null) {
                    scanFactoryMethods(beanName, clazz, beanDefinitionMap);
                }
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

    /**
     * 按照ComponentScan和Order标注的value，查找出对应的类的全路径名
     *
     * @param configClass 配置类
     * @return 全类名Set集合
     */
    private Set<String> scanForClassNames(Class<?> configClass) {
        // 获取要扫描的packageName
        ComponentScan scan = ClassUtils.findAnnotation(configClass, ComponentScan.class);
        final String[] scanPackages = scan == null || scan.value().length == 0 ? new String[] {
            configClass.getPackage().getName()} : scan.value();

        logger.atInfo().log("component scan in packages: {}", Arrays.toString(scanPackages));

        // 去重
        Set<String> classNameSet = new HashSet<>();
        for (String scanPackage : scanPackages) {
            // 扫描package
            logger.atDebug().log("scan package: {}", scanPackage);
            var rr = new ResourceResolver(scanPackage);
            List<String> classList = rr.scan(resource -> {
                String name = resource.name();
                if (name.endsWith(".class")) {
                    return name.substring(0, name.length() - 6)
                            .replace("/",".")
                            .replace("\\",".");
                }
                return null;
            });
            if (logger.isDebugEnabled()) {
                classList.forEach((className) -> {
                    logger.debug("class found by component scan: {}", className);
                });
            }
            classNameSet.addAll(classList);
        }

        // 查找@Import(Xyz.class):
        Import importConfig = configClass.getAnnotation(Import.class);
        if (importConfig != null) {
            for (Class<?> importConfigClass : importConfig.value()) {
                String importClassName = importConfigClass.getName();
                if (classNameSet.contains(importClassName)) {
                    logger.warn("ignore import: " + importClassName + " for it is already been scanned.");
                } else {
                    logger.debug("class found by import: {}", importClassName);
                    classNameSet.add(importClassName);
                }
            }
        }
        return classNameSet;
    }

    /**
     * Scan factory method that annotated with @Bean:
     *
     * <code>
     * &#64;Configuration
     * public class Hello {
     *     @Bean
     *     ZoneId createZone() {
     *         return ZoneId.of("Z");
     *     }
     * }
     * </code>
     */
    void scanFactoryMethods(String factoryBeanName, Class<?> clazz, Map<String, BeanDefinition> defs) {
        for (Method method : clazz.getDeclaredMethods()) {
            Bean bean = method.getAnnotation(Bean.class);
            if (bean != null) {
                int mod = method.getModifiers();
                if (Modifier.isAbstract(mod)) {
                    throw new BeanDefinitionException("@Bean method " + clazz.getName() + "." + method.getName() + " must not be abstract.");
                }
                if (Modifier.isFinal(mod)) {
                    throw new BeanDefinitionException("@Bean method " + clazz.getName() + "." + method.getName() + " must not be final.");
                }
                if (Modifier.isPrivate(mod)) {
                    throw new BeanDefinitionException("@Bean method " + clazz.getName() + "." + method.getName() + " must not be private.");
                }
                Class<?> beanClass = method.getReturnType();
                if (beanClass.isPrimitive()) {
                    throw new BeanDefinitionException("@Bean method " + clazz.getName() + "." + method.getName() + " must not return primitive type.");
                }
                if (beanClass == void.class || beanClass == Void.class) {
                    throw new BeanDefinitionException("@Bean method " + clazz.getName() + "." + method.getName() + " must not return void.");
                }

                var def = new BeanDefinition(ClassUtils.getBeanName(method), beanClass, factoryBeanName, method, getOrder(method),
                        method.isAnnotationPresent(Primary.class),
                        // init method:
                        bean.initMethod().isEmpty() ? null : bean.initMethod(),
                        // destroy method:
                        bean.destroyMethod().isEmpty() ? null : bean.destroyMethod(),
                        // @PostConstruct / @PreDestroy method:
                        null, null);
                addBeanDefinitions(defs, def);
                logger.atDebug().log("define bean: {}", def);
            }
        }
    }


    boolean isConfigurationDefinition(BeanDefinition def) {
        return ClassUtils.findAnnotation(def.getBeanClass(), Configuration.class) != null;
    }

    /**
     * 根据Name查找BeanDefinition，如果Name不存在，返回null
     */
    @Nullable
    public BeanDefinition findBeanDefinition(String name) {
        return this.beans.get(name);
    }


    /**
     *  根据Name和Type查找BeanDefinition，如果Name不存在，返回null，如果Name存在，但Type不匹配，抛出异常。
     */
    @Nullable
    public BeanDefinition findBeanDefinition(String name, Class<?> requiredType) {
        BeanDefinition def = findBeanDefinition(name);
        if (def == null) {
            return null;
        }
        //  Type不匹配
        if (!requiredType.isAssignableFrom(def.getBeanClass())) {
            throw new BeanNotOfRequiredTypeException(String.format("Autowire required type '%s' but bean '%s' has actual type '%s'.",
                    requiredType.getName(),
                    name, def.getBeanClass().getName()));
        }
        return def;
    }

    /**
     * 根据Type查找若干个BeanDefinition，返回0个或多个。
     */
    public List<BeanDefinition> findBeanDefinitions(Class<?> type) {
        return this.beans.values().stream()
                // filter by type and sub-type:
                .filter(def -> type.isAssignableFrom(def.getBeanClass()))
                // 排序:
                .sorted().collect(Collectors.toList());
    }

    /**
     * 根据Type查找某个BeanDefinition，如果不存在返回null，如果存在多个返回@Primary标注的一个，
     * 如果有多个@Primary标注，或没有@Primary标注但找到多个，均抛出NoUniqueBeanDefinitionException
     */
    public BeanDefinition findBeanDefinition(Class<?> type) {
        List<BeanDefinition> defs = findBeanDefinitions(type);
        if (defs.isEmpty()) {
            return null;
        }
        if (defs.size() == 1) {
            return defs.get(0);
        }
        List<BeanDefinition> primaryDefs = defs.stream()
                .filter(BeanDefinition::isPrimary).toList();
        if (primaryDefs.size() == 1) {
            return primaryDefs.get(0);
        }
        if (primaryDefs.isEmpty()) {
            throw new NoUniqueBeanDefinitionException(String.format("Multiple bean with type '%s' found, but no @Primary specified.", type.getName()));
        } else {
            throw new NoUniqueBeanDefinitionException(String.format("Multiple bean with type '%s' found, and multiple @Primary specified.", type.getName()));
        }
    }
}

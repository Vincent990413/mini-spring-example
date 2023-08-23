package com.example.minispringexample.beans.factory.config;


import cn.hutool.core.util.ObjectUtil;
import com.example.minispringexample.beans.PropertyValues;
import lombok.Getter;
import lombok.Setter;

/**
 * BeanDefinition 实例用于保存每个 Bean 容器的一些定义信息，
 * 即 Bean 的数据内容，包括：（Class 类型，方法构造参数，是否为单例，PV 属性）
 *
 * @author vincent
 * @since 2023/08/08
 */
@Getter
@Setter
public class BeanDefinition {

    /**
     * 私有构造方法用于创建 {@code BeanDefinition} 实例。
     *
     * @param beanClass      Bean的类。
     * @param propertyValues 关联的属性值。
     */
    private BeanDefinition(Class<?> beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        // 如果没有初始化的 PVS，也不能使其为空
        // 否则后面 GETTER 方法获取时将报错
        this.propertyValues = ObjectUtil.isNotEmpty(propertyValues) ? propertyValues : new PropertyValues();
    }

    /**
     * 工厂方法，用于在不需要 {@link PropertyValues} 的情况下创建一个新的 {@code BeanDefinition} 实例。
     *
     * @param beanClass Bean的类。
     * @return 一个新的 {@code BeanDefinition} 实例。
     */
    public static BeanDefinition newBeanDefinitionNoPV(Class<?> beanClass) {
        return new BeanDefinition(beanClass, null);
    }

    /**
     * 工厂方法，用于创建一个新的 {@code BeanDefinition} 实例，包含给定的Bean类和关联的属性值。
     *
     * @param beanClass      Bean的类。
     * @param propertyValues 关联的属性值。
     * @return 一个新的 {@code BeanDefinition} 实例。
     */
    public static BeanDefinition newBeanDefinition(Class<?> beanClass, PropertyValues propertyValues) {
        return new BeanDefinition(beanClass, propertyValues);
    }


    private final Class<?> beanClass;
    private final PropertyValues propertyValues;
}

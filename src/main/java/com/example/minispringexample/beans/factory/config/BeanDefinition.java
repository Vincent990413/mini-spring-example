package com.example.minispringexample.beans.factory.config;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * BeanDefinition 实例用于保存每个 Bean 容器的
 * 一些定义信息，即 Bean 的数据内容，包括：
 * （Class 类型，方法构造参数，是否为单例）
 *
 * @author vincent
 * @since 2023/08/08
 */
@Getter
@Setter
@AllArgsConstructor
public class BeanDefinition {

    private final Class<?> beanClass;
}

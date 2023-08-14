package com.example.minispringexample.beans.factory;


/**
 * BeanFactory 用于实现 Bean 容器的注册与获取
 *
 * @author vincent
 * @since 2023/08/08
 */
public interface BeanFactory {

    Object getBean(String name);
}

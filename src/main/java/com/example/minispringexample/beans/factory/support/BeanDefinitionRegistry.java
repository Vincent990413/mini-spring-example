package com.example.minispringexample.beans.factory.support;

import com.example.minispringexample.beans.factory.config.BeanDefinition;

/**
 * BeanDefinition 注册表接口，负责注册新的Bean的
 * BeanDefinition 信息
 *
 * @author vincent
 * @since 2023/08/08
 */

public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    String[] getBeanDefinitionNames();

    BeanDefinition getBeanDefinition(String beanName);

    boolean containsBeanDefinition(String beanName);
}

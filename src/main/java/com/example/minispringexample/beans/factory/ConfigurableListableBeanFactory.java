package com.example.minispringexample.beans.factory;

import com.example.minispringexample.beans.factory.config.AutowireCapableBeanFactory;
import com.example.minispringexample.beans.factory.config.BeanDefinition;
import com.example.minispringexample.beans.factory.config.ConfigurableBeanFactory;

/**
 * @description:
 * @version: 1.0
 * @author: Vincent
 * @date: 2023/8/25 10:30
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    BeanDefinition getBeanDefinition(String beanName);
}

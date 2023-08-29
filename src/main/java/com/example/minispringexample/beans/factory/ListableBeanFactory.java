package com.example.minispringexample.beans.factory;

import java.util.Map;

/**
 * @description:
 * @version: 1.0
 * @author: Vincent
 * @date: 2023/8/25 10:32
 */
public interface ListableBeanFactory extends BeanFactory {
    <T> Map<String, T> getBeansOfType(Class<T> type);

    String[] getBeanDefinitionNames();
}

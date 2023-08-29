package com.example.minispringexample.beans.factory.config;

import com.example.minispringexample.beans.factory.BeanFactory;
import com.example.minispringexample.beans.factory.support.HierarchicalBeanFactory;

/**
 * @description:
 * @version: 1.0
 * @author: Vincent
 * @date: 2023/8/25 10:36
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
}

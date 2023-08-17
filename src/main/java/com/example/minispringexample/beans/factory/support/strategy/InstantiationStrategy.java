package com.example.minispringexample.beans.factory.support.strategy;

import com.example.minispringexample.beans.factory.config.BeanDefinition;

/**
 * @description: 该接口可以给 Bean 对象提供，除无参构造函数（即使用 newInstance 反射方法实例化）
 * 之外的其他情况下的 Bean 对象的实例化策略
 * @version: 1.0
 * @author: Vincent
 * @date: 2023/8/14 10:24
 */
public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition);
}

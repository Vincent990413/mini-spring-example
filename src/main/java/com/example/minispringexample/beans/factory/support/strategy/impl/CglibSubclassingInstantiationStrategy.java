package com.example.minispringexample.beans.factory.support.strategy.impl;

import com.example.minispringexample.beans.factory.config.BeanDefinition;
import com.example.minispringexample.beans.factory.support.strategy.InstantiationStrategy;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @description: 使用 CGLIB 动态生成子类的 Bean 对象策略
 * 允许 Spring 在运行时创建代理对象，并通过添加代理逻辑来增强目标对象的功能。
 * @version: 1.0
 * @author: Vincent
 * @date: 2023/8/17 09:55
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(BeanDefinition beanDefinition) {
        Enhancer enhancer = new Enhancer();  // 创建 CGLIB Enhancer 对象
        enhancer.setSuperclass(beanDefinition.getBeanClass());  // 设置目标类作为超类
        enhancer.setCallback((MethodInterceptor)
                (obj, method, argsTemp, proxy) -> proxy.invokeSuper(obj, argsTemp));  // 设置回调函数
        return enhancer.create();  // 创建子类代理对象并返回
    }
}

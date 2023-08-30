package com.example.minispringexample.beans.factory.config;

import com.example.minispringexample.beans.factory.support.HierarchicalBeanFactory;

/**
 * @description: 这接口表示一个可配置的 Bean 工厂，继承自 HierarchicalBeanFactory 和 SingletonBeanRegistry 接口，
 * 允许向工厂中添加用于处理 Bean的后置处理器。通过添加 Bean 后置处理器，可以在 Bean 实例化及依赖注入的过程中
 * 执行自定义的逻辑操作，对 Bean 进行定制化的处理。
 * @version: 1.0
 * @author: Vincent
 * @date: 2023/8/25 10:36
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    /**
     * 向工厂中添加一个 Bean 后置处理器。Bean 后置处理器允许在 Bean 的实例化、依赖注入及初始化等阶段插入自定义的处理逻辑。
     *
     * @param beanPostProcessor 要添加的 Bean 后置处理器
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}


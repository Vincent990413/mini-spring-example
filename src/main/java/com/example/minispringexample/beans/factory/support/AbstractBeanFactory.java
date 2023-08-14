package com.example.minispringexample.beans.factory.support;

import com.example.minispringexample.beans.factory.BeanFactory;
import com.example.minispringexample.beans.factory.config.BeanDefinition;

import java.util.Objects;

/**
 * @description: BeanFactory 接口的抽象实现，提供基本的 Bean 获取逻辑，并定义了用于创建 Bean 和获取 Bean 定义的抽象方法。
 * @version: 1.0
 * @author: Vincent
 * @date: 2023/8/10 10:58
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    /**
     * 根据名称检索 Bean 实例。
     *
     * @param name 要检索的 Bean 的名称。
     * @return 请求的 Bean 实例，如果找不到该 Bean，则返回 null。
     */
    @Override
    public Object getBean(String name) {
        Object singletonBean = getSingletonBean(name);
        if (Objects.nonNull(singletonBean)) {
            return singletonBean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }

    /**
     * 子类需要实现的抽象方法。根据名称和定义创建 Bean 实例。
     *
     * @param beanName       要创建的 Bean 的名称。
     * @param beanDefinition Bean 的定义。
     * @return 创建的 Bean 实例。
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition);

    /**
     * 子类需要实现的抽象方法。根据名称检索 Bean 定义。
     *
     * @param beanName Bean 的名称。
     * @return 与给定名称相关联的 Bean 定义。
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName);
}

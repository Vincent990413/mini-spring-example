package com.example.minispringexample.beans.factory.support;

import com.example.minispringexample.beans.factory.config.BeanDefinition;
import com.example.minispringexample.exceptions.impl.BasicBeansExceptionImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 抽象类， 用于将 Bean 的相关信息添加到工厂中。其中的 doCreateBean 方法可用于子类具体实现。
 * @version: 1.0
 * @author: Vincent
 * @date: 2023/8/10 11:12
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        return doCreateBean(beanName, beanDefinition);
    }

    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Object bean = null;
        try {
            bean = beanClass.newInstance();
        } catch (InstantiationException e) {
            throw BasicBeansExceptionImpl.
                    BEANS_INSTANTIATION_EXCEPTION.newException(e);
        } catch (IllegalAccessException e) {
            throw BasicBeansExceptionImpl.
                    BEANS_ILLEGAL_ACCESS_EXCEPTION.newException(e);
        }

        addSingletonBean(beanName, bean);
        return bean;
    }
}

package com.example.minispringexample.beans.factory.support;

import com.example.minispringexample.beans.exceptions.base.BusinessException;
import com.example.minispringexample.beans.factory.config.BeanDefinition;
import com.example.minispringexample.beans.factory.support.strategy.InstantiationStrategy;
import com.example.minispringexample.beans.factory.support.strategy.impl.SimpleInstantiationStrategy;
import com.example.minispringexample.exceptions.impl.BasicBeansExceptionImpl;

/**
 * @description: 抽象类， 用于将 Bean 的相关信息添加到工厂中。其中的 doCreateBean 方法可用于子类具体实现。
 * @version: 1.0
 * @author: Vincent
 * @date: 2023/8/10 11:12
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    // 默认为 Bean 对象的无参构造方法
    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        return doCreateBean(beanName, beanDefinition);
    }

    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition);
        } catch (Exception e) {
            throw BasicBeansExceptionImpl.BEANS_EXCEPTION.newException(e);
        }

        addSingletonBean(beanName, bean);
        return bean;
    }

    private Object createBeanInstance(BeanDefinition beanDefinition) {
        return getInstantiationStrategy().instantiate(beanDefinition);
    }

    private InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

}

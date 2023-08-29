package com.example.minispringexample.beans.factory.support.strategy.impl;

import com.example.minispringexample.beans.factory.config.BeanDefinition;
import com.example.minispringexample.beans.factory.support.strategy.InstantiationStrategy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.example.minispringexample.beans.exceptions.impl.BasicBeansExceptionImpl;

/**
 * @description:
 * @version: 1.0
 * @author: Vincent
 * @date: 2023/8/14 10:32
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();

        try {
            Constructor<?> declaredConstructor = beanClass.getDeclaredConstructor();
            return declaredConstructor.newInstance();
        } catch (NoSuchMethodException | InstantiationException | InvocationTargetException |
                 IllegalAccessException e) {
            throw BasicBeansExceptionImpl.BEANS_EXCEPTION.newException(e);
        }

    }
}

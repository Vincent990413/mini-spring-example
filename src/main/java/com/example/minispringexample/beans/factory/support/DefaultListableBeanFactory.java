package com.example.minispringexample.beans.factory.support;

import com.example.minispringexample.beans.factory.config.BeanDefinition;
import com.example.minispringexample.exceptions.impl.BasicBeansExceptionImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 该类是默认的 Bean 对象工厂具体实现类；在注册表接口（可以注册 Bean 对象）的基础上，
 * 继承了抽象可装配 Bean 对象工厂类（该类负责具体创建 Bean 对象的过程）
 * @version: 1.0
 * @author: Vincent
 * @date: 2023/8/10 11:44
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {
    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    /**
     * 获取与给定Bean名称相关联的 BeanDefinition。
     *
     * @param beanName 要检索其定义的Bean的名称。
     * @return 指定Bean名称的 BeanDefinition 对象。
     */
    @Override
    protected BeanDefinition getBeanDefinition(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        BasicBeansExceptionImpl
                .BEANS_VALUE_NULL_EXCEPTION
                .assertNotNull(beanDefinition);
        return beanDefinition;
    }

    /**
     * 使用给定的Bean名称注册一个新的BeanDefinition。
     *
     * @param beanName       应注册Bean定义的名称。
     * @param beanDefinition 要注册的 BeanDefinition 对象。
     */
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }
}

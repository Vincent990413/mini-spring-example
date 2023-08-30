package com.example.minispringexample.beans.factory.config;

import com.example.minispringexample.beans.exceptions.base.BusinessException;
import com.example.minispringexample.beans.factory.ConfigurableListableBeanFactory;

/**
 * BeanFactoryPostProcessor 接口用于在 Spring 容器加载 Bean 的定义后，
 * 但在实例化和初始化 Bean 之前，对 BeanFactory 进行后置处理。它允许您对 BeanFactory 进行修改，
 * 如修改 Bean 的定义或对属性进行动态替换。
 * <p>
 * <p>
 * 调用时机： BeanFactoryPostProcessor 的实现类在 Spring 容器加载 Bean 的定义后被调用。
 * 这意味着它们可以修改 Bean 的定义，但无法直接操作 Bean 实例。
 * <p>
 * 重点：BeanFactoryPostProcessor 用于修改 BeanFactory 中的 Bean 定义，影响 Bean 的创建前的配置。
 *
 * @author vincent
 * @since 2023/08/29
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的 BeanDefinition 对象加载完成后，在 Bean 对象的实例化之前，提供修改 BeanDefinition
     * 对象的 PropertyValue 属性值的修改功能
     *
     * @param beanFactory Spring容器的可配置Bean工厂
     * @throws BusinessException 如果在处理过程中发生业务异常
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BusinessException;
}

package com.example.minispringexample.beans.factory;

import com.example.minispringexample.beans.exceptions.base.BusinessException;

/**
 * BeanFactory 接口定义了一种机制，用于实现 Bean 容器的注册和获取。
 * 在 Spring 框架中，BeanFactory 是 IoC（控制反转）容器的基础接口，提供了通过名称获取 Bean 实例的方法。
 * 开发者可以通过实现该接口，定义自己的 Bean 容器，从而实现对 Bean 的生命周期管理和依赖注入。
 *
 * <p>注意：BeanFactory 接口不限于 Spring 框架本身，也可以用于扩展到其他自定义的 IoC 容器实现。
 *
 * <p><strong>使用注意：</strong>
 * <ul>
 *   <li>通过 {@link #getBean(String)} 方法，根据给定的名称获取对应的 Bean 实例。</li>
 *   <li>通过 {@link #getBean(String, Class)} 方法，根据给定的名称和类型获取对应的 Bean 实例。</li>
 * </ul>
 *
 * @author vincent
 * @since 2023/08/08
 */
public interface BeanFactory {

    /**
     * 根据给定的名称获取对应的 Bean 实例。
     *
     * @param name Bean 的名称
     * @return Bean 实例
     * @throws BusinessException 如果获取 Bean 实例时发生业务异常:
     *                           1. Bean 对象为空 2. 获取 Bean 对象发生异常
     */
    Object getBean(String name) throws BusinessException;

    /**
     * 根据给定的名称和类型获取对应的 Bean 实例。
     *
     * @param name         Bean 的名称
     * @param requiredType 所需的 Bean 类型
     * @param <T>          Bean 的类型
     * @return Bean 实例
     * @throws BusinessException 如果获取 Bean 实例时发生业务异常
     */
    <T> T getBean(String name, Class<T> requiredType) throws BusinessException;
}



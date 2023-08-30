package com.example.minispringexample.beans.factory.config;

import com.example.minispringexample.beans.exceptions.base.BusinessException;

/**
 * BeanPostProcessor 接口定义了在 Spring 容器中的 Bean 初始化过程中进行扩展和自定义逻辑的方法。
 * 这些方法允许您在不同的初始化阶段对 Bean 进行处理，以满足特定的需求。
 * <p>
 * 注意：实现该接口的类将会在每个 Bean 的初始化阶段被调用，用于插入自定义逻辑。
 * 这些方法的返回值应该是 Bean 实例本身或经过修改后的 Bean 实例，如果返回 null 则表示没有进行修改。
 *
 * @author Vincent
 * @since 2023/08/29
 */
public interface BeanPostProcessor {

    /**
     * 在 Bean 初始化之前被调用，允许您执行一些自定义逻辑。
     * 通常用于添加前置处理，修改 Bean 实例属性等。
     *
     * @param bean     正在初始化的 Bean 实例
     * @param beanName Bean 的名称
     * @return 修改后的 Bean 实例
     * @throws BusinessException 如果在处理过程中发生业务异常
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BusinessException;

    /**
     * 在 Bean 初始化之后被调用，允许您执行一些自定义逻辑。
     * 通常用于添加后置处理，执行其他初始化操作等。
     *
     * @param bean     已初始化的 Bean 实例
     * @param beanName Bean 的名称
     * @return 修改后的 Bean 实例
     * @throws BusinessException 如果在处理过程中发生业务异常
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BusinessException;
}


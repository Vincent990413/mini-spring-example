package com.example.minispringexample.beans.factory.config;

import com.example.minispringexample.beans.exceptions.base.BusinessException;
import com.example.minispringexample.beans.factory.BeanFactory;

/**
 * Q: 什么是自动装配？
 * A: 自动装配是一种依赖注入的机制，它允许 Spring 框架根据类型和名称自动解析和注入 Bean 之间的依赖关系，
 * 无需手动指定每个 Bean 之间的依赖关系。通过自动装配，Spring 可以根据配置或规则，自动将相互关联的 Bean
 * 注入到需要它们的 Bean 中，从而简化了配置和管理。
 * <p>
 * TODO AutowireCapableBeanFactory 接口继承自 BeanFactory 接口，提供了能够进行自动装配的 Bean 创建能力。在 Spring 框架中，AutowireCapableBeanFactory 扩展了 BeanFactory 的功能，允许开发者通过自动装配的方式创建 Bean。使用自动装配时，需要特别注意 Bean 的依赖关系，确保自动装配的方式能够正确地满足应用程序的需求。
 *
 * @version 1.0
 * @see BeanFactory
 * @since 2023/8/25 10:33
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 在 Bean 初始化之前应用 Bean 后置处理器。
     *
     * @param existingBean 已存在的 Bean 实例
     * @param beanName     Bean 的名称
     * @return 修改后的 Bean 实例
     * @throws BusinessException 如果在处理期间发生异常
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BusinessException;

    /**
     * 在 Bean 初始化之后应用 Bean 后置处理器。
     *
     * @param existingBean 已存在的 Bean 实例
     * @param beanName     Bean 的名称
     * @return 修改后的 Bean 实例
     * @throws BusinessException 如果在处理期间发生异常
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BusinessException;

    // TODO 后续添加关于 autowire 的相关方法
}



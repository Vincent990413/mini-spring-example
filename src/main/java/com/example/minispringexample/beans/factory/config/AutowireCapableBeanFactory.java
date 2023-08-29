package com.example.minispringexample.beans.factory.config;

import com.example.minispringexample.beans.factory.BeanFactory;

/**
 * Q: 什么是自动装配？
 * A: 根据类型和名称自动解析和注入依赖关系，而无需手动指定每个 Bean 之间的依赖关系。
 * <p>
 * AutowireCapableBeanFactory 接口继承自 BeanFactory 接口，提供了能够进行自动装配的 Bean 创建能力。
 * 在 Spring 框架中，AutowireCapableBeanFactory 扩展了 BeanFactory 的功能，允许开发者通过自动装配的方式创建 Bean。
 *
 * <p>自动装配是一种依赖注入的方式，Spring 可以自动解析和注入 Bean 之间的依赖关系，从而简化了配置和管理。
 * AutowireCapableBeanFactory 接口提供了一些方法，用于创建和初始化 Bean 并处理其依赖关系。
 *
 * <p>注意：使用自动装配时，需要特别注意 Bean 的依赖关系，确保自动装配的方式能够正确地满足应用程序的需求。
 *
 * @version 1.0
 * @see BeanFactory
 * @since 2023/8/25 10:33
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
}


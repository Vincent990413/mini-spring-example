package com.example.minispringexample.beans.factory.support;

import com.example.minispringexample.beans.exceptions.base.BusinessException;
import com.example.minispringexample.beans.exceptions.base.IBusinessAssertException;
import com.example.minispringexample.beans.exceptions.impl.BasicBeansExceptionImpl;
import com.example.minispringexample.beans.factory.config.BeanDefinition;
import com.example.minispringexample.beans.factory.config.ConfigurableBeanFactory;

import java.util.Objects;

/**
 * 抽象的 BeanFactory 实现类，提供基本的 Bean 获取逻辑，并定义了用于创建 Bean 和获取 Bean 定义的抽象方法。
 *
 * <p>继承自 DefaultSingletonBeanRegistry，实现了 ConfigurableBeanFactory 接口。
 * 该类封装了 Bean 的创建和获取过程，支持单例模式的管理，并提供了扩展点供子类实现。
 *
 * @author vincent
 * @version 1.0
 * @date 2023/8/10 10:58
 * @see DefaultSingletonBeanRegistry
 * @see ConfigurableBeanFactory
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    /**
     * 根据名称检索 Bean 实例。
     *
     * @param name 要检索的 Bean 的名称。
     * @return 请求的 Bean 实例，如果找不到该 Bean，则返回 null。
     * @throws BusinessException 当获取 Bean 实例时发生业务异常。
     */
    @Override
    public Object getBean(String name) throws BusinessException {
        Object singletonBean = getSingletonBean(name);
        if (Objects.nonNull(singletonBean)) {
            return singletonBean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }

    /**
     * 根据名称和类型获取 Bean 实例。
     * <p>
     * 注意，这里为了更好的处理异常情况，与原开源项目不同，并不使用直接
     * 强制转换的方式。
     *
     * @param name         要检索的 Bean 的名称。
     * @param requiredType 所需的 Bean 类型。
     * @param <T>          Bean 的类型。
     * @return 请求的 Bean 实例。
     * @throws BusinessException 当获取 Bean 实例时发生业务异常。
     */
    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BusinessException {
        try {
            // 不使用 (T) getBean(name) 的方式
            return requiredType.cast(getBean(name));
        } catch (ClassCastException e) {
            throw BasicBeansExceptionImpl.BEANS_CLASS_NOT_FOUND_EXCEPTION.newException(e);
        }
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

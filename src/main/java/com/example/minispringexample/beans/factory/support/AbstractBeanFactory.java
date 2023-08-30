package com.example.minispringexample.beans.factory.support;

import com.example.minispringexample.beans.exceptions.base.BusinessException;
import com.example.minispringexample.beans.exceptions.base.IBusinessAssertException;
import com.example.minispringexample.beans.exceptions.impl.BasicBeansExceptionImpl;
import com.example.minispringexample.beans.factory.config.BeanDefinition;
import com.example.minispringexample.beans.factory.config.BeanPostProcessor;
import com.example.minispringexample.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * 抽象的 Bean 工厂类，用于定义了获取和创建 Bean 的基本结构和方法。
 * 通过继承 DefaultSingletonBeanRegistry 类和实现 ConfigurableBeanFactory 接口，
 * 提供了对 Bean 的管理和配置的能力。
 *
 * @version 1.0
 * @see DefaultSingletonBeanRegistry
 * @see ConfigurableBeanFactory
 * @since 2023/8/10 10:58
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    // 用于存储 Bean 后置处理器的列表
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

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
     * 注意，这里为了更好的处理异常情况，与原开源项目不同，并不使用直接强制转换的方式。
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
            // 不使用 (T) getBean(name) 的方式，而是通过强制类型转换来获取
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

    /**
     * 添加 Bean 后置处理器到列表中。
     *
     * @param beanPostProcessor 要添加的 Bean 后置处理器。
     */
    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        // 首先移除已有的同类型 Bean 后置处理器，然后添加新的
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * 获取所有注册的 Bean 后置处理器的列表。
     *
     * @return 所有注册的 Bean 后置处理器。
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }
}

package com.example.minispringexample.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.minispringexample.beans.PropertyValue;
import com.example.minispringexample.beans.PropertyValues;
import com.example.minispringexample.beans.exceptions.base.BusinessException;
import com.example.minispringexample.beans.factory.config.AutowireCapableBeanFactory;
import com.example.minispringexample.beans.factory.config.BeanDefinition;
import com.example.minispringexample.beans.factory.config.BeanPostProcessor;
import com.example.minispringexample.beans.factory.config.BeanReference;
import com.example.minispringexample.beans.factory.support.strategy.InstantiationStrategy;
import com.example.minispringexample.beans.factory.support.strategy.impl.SimpleInstantiationStrategy;
import com.example.minispringexample.beans.exceptions.impl.BasicBeansExceptionImpl;

import java.util.List;

/**
 * @description: 抽象类， 用于将 Bean 的相关信息添加到工厂中。其中的 doCreateBean 方法可用于子类具体实现。
 * @version: 1.0
 * @author: Vincent
 * @date: 2023/8/10 11:12
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory
        implements AutowireCapableBeanFactory {

    // 默认为 Bean 对象的无参构造方法
    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        return doCreateBean(beanName, beanDefinition);
    }

    /**
     * 创建一个 Bean 实例，填充属性并初始化。
     *
     * @param beanName       Bean 的名称
     * @param beanDefinition Bean 定义
     * @return 创建并初始化后的 Bean 实例
     */
    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        Object bean = null;
        try {
            // 实例化 Bean 对象
            bean = createBeanInstance(beanDefinition);

            // 为 Bean 对象填充属性
            populatePropertyValues(beanName, bean, beanDefinition);

            // 填充属性之后 执行
            // 其对应 BeanPostProcessor 初始化前
            // bean 的初始化方法
            // 以及初始化后
            // 最终返回新的 bean 对象
            bean = initializeBean(beanName, bean, beanDefinition);

        } catch (Exception e) {
            throw BasicBeansExceptionImpl.BEANS_EXCEPTION.newException(e);
        }

        addSingletonBean(beanName, bean);
        return bean;
    }

    /**
     * 初始化 Bean，包括执行初始化方法和应用后置处理器。
     *
     * @param beanName       Bean 的名称
     * @param bean           Bean 实例
     * @param beanDefinition Bean 定义
     * @return 初始化后的 Bean 实例
     */
    protected Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 初始化前
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        //TODO 此处执行 bean 的初始化 init-method 方法
        invokeInitMethods(beanName, wrappedBean, beanDefinition);

        // 初始化后
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        return wrappedBean;
    }


    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BusinessException {
        Object beanBefore = existingBean;
        // 父类的方法
        List<BeanPostProcessor> beanPostProcessors = getBeanPostProcessors();

        // 可能存在多个定制化的处理器 逐个进行处理
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            Object currentBean = beanPostProcessor.postProcessBeforeInitialization(beanBefore, beanName);
            if (ObjectUtil.isEmpty(currentBean)) {
                return beanBefore;
            }

            beanBefore = currentBean;
        }
        return beanBefore;
    }

    /**
     * 执行 Bean 的初始化方法。
     *
     * @param beanName       Bean 的名称
     * @param wrappedBean    初始化前的 Bean 实例
     * @param beanDefinition Bean 定义
     */
    protected void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) {
        System.out.println("执行bean[" + beanName + "]的初始化方法");
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BusinessException {
        Object beanAfter = existingBean;
        // 父类的方法
        List<BeanPostProcessor> beanPostProcessors = getBeanPostProcessors();

        // 可能存在多个定制化的处理器 逐个进行处理
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            Object currentBean = beanPostProcessor.postProcessAfterInitialization(beanAfter, beanName);
            if (ObjectUtil.isEmpty(currentBean)) {
                return beanAfter;
            }

            beanAfter = currentBean;
        }
        return beanAfter;
    }

    /**
     * 将属性值填充到 Bean 实例中。
     *
     * @param beanName       Bean 的名称
     * @param bean           Bean 实例
     * @param beanDefinition Bean 的定义
     */
    protected void populatePropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            // 目标 Bean 对象的域列表 当不为空时才注入属性
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            if (BeanUtil.isEmpty(propertyValues)) {
                System.out.println("检测到 PropertyValues 为空！");
                return;
            }

            PropertyValue[] propertyValuesArray = propertyValues.getPropertyValues();
            for (PropertyValue propertyValue : propertyValuesArray) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                // 如果该 Bean 对象的属性是另一个 Bean
                // 即如果是引用类型
                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    // 则将 value 转换为具体的 Bean 实例
                    value = getBean(beanReference.getBeanName());
                }

                // 利用反射设置域值
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw BasicBeansExceptionImpl.BEANS_POPULATE_EXCEPTION.newException(e);
        }


    }

    /**
     * 创建 Bean 实例。
     *
     * @param beanDefinition Bean 的定义
     * @return 创建的 Bean 实例
     */
    private Object createBeanInstance(BeanDefinition beanDefinition) {
        return getInstantiationStrategy().instantiate(beanDefinition);
    }

    /**
     * 获取实例化策略。
     *
     * @return 当前使用的实例化策略
     */
    private InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }


    /**
     * 设置实例化策略。
     *
     * @param instantiationStrategy 实例化策略
     */
    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

}

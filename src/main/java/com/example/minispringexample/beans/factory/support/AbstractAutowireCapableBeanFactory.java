package com.example.minispringexample.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ArrayUtil;
import com.example.minispringexample.beans.PropertyValue;
import com.example.minispringexample.beans.PropertyValues;
import com.example.minispringexample.beans.exceptions.base.BusinessException;
import com.example.minispringexample.beans.factory.config.BeanDefinition;
import com.example.minispringexample.beans.factory.config.BeanReference;
import com.example.minispringexample.beans.factory.support.strategy.InstantiationStrategy;
import com.example.minispringexample.beans.factory.support.strategy.impl.SimpleInstantiationStrategy;
import com.example.minispringexample.exceptions.impl.BasicBeansExceptionImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @description: 抽象类， 用于将 Bean 的相关信息添加到工厂中。其中的 doCreateBean 方法可用于子类具体实现。
 * @version: 1.0
 * @author: Vincent
 * @date: 2023/8/10 11:12
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    // 默认为 Bean 对象的无参构造方法
    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        return doCreateBean(beanName, beanDefinition);
    }

    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Object bean = null;
        try {
            // 实例化 Bean 对象
            bean = createBeanInstance(beanDefinition);

            // 为 Bean 对象填充属性
            populatePropertyValues(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw BasicBeansExceptionImpl.BEANS_EXCEPTION.newException(e);
        }

        addSingletonBean(beanName, bean);
        return bean;
    }

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

    private Object createBeanInstance(BeanDefinition beanDefinition) {
        return getInstantiationStrategy().instantiate(beanDefinition);
    }

    private InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

}

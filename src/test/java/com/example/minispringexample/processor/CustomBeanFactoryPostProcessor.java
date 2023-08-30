package com.example.minispringexample.processor;

import com.example.minispringexample.beans.PropertyValue;
import com.example.minispringexample.beans.PropertyValues;
import com.example.minispringexample.beans.exceptions.base.BusinessException;
import com.example.minispringexample.beans.factory.ConfigurableListableBeanFactory;
import com.example.minispringexample.beans.factory.config.BeanDefinition;
import com.example.minispringexample.beans.factory.config.BeanFactoryPostProcessor;

/**
 * CustomBeanFactoryPostProcessor 是一个自定义的 BeanFactoryPostProcessor 实现，
 * 用于在 Spring 容器加载 Bean 的定义后，在实例化和初始化 Bean 之前，对特定 BeanDefinition 进行属性修改。
 *
 *
 * 通过该实现，可以动态地向指定的 BeanDefinition 添加新的属性，下方代码说明添加：version 属性值为 1.0.
 *
 * @author vincent
 * @since 2023/08/29
 */
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    /**
     * 在容器加载 Bean 的定义后，在实例化和初始化 Bean 之前，对特定 BeanDefinition 进行属性修改。
     * 通过该方法，可以向指定的 BeanDefinition 添加新的属性。
     *
     * @param beanFactory the bean factory to post-process
     * @throws BusinessException 如果在属性修改过程中出现业务异常
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BusinessException {
        // 获取目标 BeanDefinition 对象
        BeanDefinition personBeanDefinition = beanFactory.getBeanDefinition("helloServiceImpl");
        PropertyValues propertyValues = personBeanDefinition.getPropertyValues();

        // 为目标 BeanDefinition 添加新的属性值
        String propertyName = "version";
        String propertyValue = "1.0";
        PropertyValue newVersionPropertyValue = new PropertyValue(propertyName, propertyValue);
        propertyValues.addPropertyValue(newVersionPropertyValue);
    }
}



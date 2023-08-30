package com.example.minispringexample.processor;

import com.example.minispringexample.beans.exceptions.base.BusinessException;
import com.example.minispringexample.beans.factory.config.BeanPostProcessor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author vincent
 * @since 2023/08/29
 */
public class CustomPermissionCheckBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BusinessException {
        // 假设这里在 Bean 的初始化之前 做一些权限检查（当然，这里只是模拟即可）
        if ("basicServiceImpl".equals(beanName)) {
            System.out.println("对 basicServiceImpl 进行权限检查...");
            System.out.println("该 Bean 对象的检查通过！");
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BusinessException {
        return bean;
    }
}



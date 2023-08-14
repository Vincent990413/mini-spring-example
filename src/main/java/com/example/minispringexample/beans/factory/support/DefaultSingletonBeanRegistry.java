package com.example.minispringexample.beans.factory.support;

import com.example.minispringexample.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 默认的单例 Bean 对象的注册表类，实现对单例 Bean 对象的存取操作。
 * @version: 1.0
 * @author: Vincent
 * @date: 2023/8/9 17:11
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjectMap = new HashMap<>();

    /**
     * @param beanName bean 对象的 name 属性
     * @return
     */
    @Override
    public Object getSingletonBean(String beanName) {
        return singletonObjectMap.get(beanName);
    }

    /**
     * 让子类对添加单例 Bean 对象这个操作进行扩展
     *
     * @param beanName        bean 对象的 name 属性
     * @param singletonObject 单例对象
     */
    protected void addSingletonBean(String beanName, Object singletonObject) {
        singletonObjectMap.put(beanName, singletonObject);
    }
}

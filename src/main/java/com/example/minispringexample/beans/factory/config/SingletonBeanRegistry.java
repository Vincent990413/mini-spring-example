package com.example.minispringexample.beans.factory.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 单例 Bean 对象的注册表，默认情况下 Bean 对象都是单例对象。
 * @version: 1.0
 * @author: Vincent
 * @date: 2023/8/9 17:06
 */
public interface SingletonBeanRegistry {
    /**
     * 根据 Bean 对象的 name 获得这个 Bean 对象
     *
     * @param beanName bean 对象的 name 属性
     * @return 返回这个 Bean 对象
     */
    Object getSingletonBean(String beanName);
}

package com.example.minispringexample.beans.factory.support;

import com.example.minispringexample.core.io.Resource;
import com.example.minispringexample.core.io.ResourceLoader;

/**
 * @description: 这个接口定义了一个能够读取和注册 BeanDefinition 的功能。BeanDefinition 是一个用于描述 Bean 的元数据的对象，
 * 包括了 Bean 的类名、属性、作用域等信息。实现这个接口的类可以从不同的资源（如 XML 文件、注解等）中读取 BeanDefinition，
 * 并将其注册到一个 BeanDefinitionRegistry 中。
 * @version: 1.0
 * @author: Vincent
 * @date: 2023/8/23 15:19
 */
public interface BeanDefinitionReader {

    /**
     * 获取用于注册 BeanDefinition 的注册表。
     *
     * @return BeanDefinitionRegistry，用于注册不同 Bean 的定义。
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * 获取用于加载资源的资源加载器。
     *
     * @return ResourceLoader，用于从不同位置加载资源文件。
     */
    ResourceLoader getResourceLoader();

    /**
     * 从指定的资源位置加载并注册 BeanDefinition。
     *
     * @param location 资源的位置，可以是文件路径、类路径等。
     */
    void loadBeanDefinitions(String location);

    /**
     * 从给定的资源对象加载并注册 BeanDefinition。
     *
     * @param resource 要加载的资源对象，可以是文件、类路径资源等。
     */
    void loadBeanDefinitions(Resource resource);

    /**
     * 从一组资源位置加载并注册 BeanDefinition。
     *
     * @param locations 资源位置的数组，可以是多个文件路径、类路径等。
     */
    void loadBeanDefinitions(String[] locations);
}

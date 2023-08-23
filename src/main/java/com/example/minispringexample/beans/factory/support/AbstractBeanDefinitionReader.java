package com.example.minispringexample.beans.factory.support;

import com.example.minispringexample.core.io.DefaultResourceLoader;
import com.example.minispringexample.core.io.Resource;
import com.example.minispringexample.core.io.ResourceLoader;
import lombok.Setter;

/**
 * @description: 这是一个抽象类 AbstractBeanDefinitionReader，它实现了 BeanDefinitionReader 接口的一部分方法，
 * 并提供了一些通用的实现细节。它主要用于从不同的资源位置加载 BeanDefinition，并将它们注册到 BeanDefinitionRegistry 中。
 * @version: 1.0
 * @author: Vincent
 * @date: 2023/8/23 15:22
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    @Setter
    private ResourceLoader resourceLoader;

    /**
     * 使用给定的 BeanDefinitionRegistry 构造一个 AbstractBeanDefinitionReader 对象，
     * 并使用默认的 DefaultResourceLoader。
     *
     * @param registry BeanDefinitionRegistry 对象，用于注册 BeanDefinition。
     */
    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    /**
     * 使用给定的 BeanDefinitionRegistry 和 ResourceLoader 构造一个 AbstractBeanDefinitionReader 对象。
     *
     * @param registry       BeanDefinitionRegistry 对象，用于注册 BeanDefinition。
     * @param resourceLoader ResourceLoader 对象，用于加载资源。
     */
    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    /**
     * 获取用于注册 BeanDefinition 的注册表。
     *
     * @return BeanDefinitionRegistry，用于注册不同 Bean 的定义。
     */
    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    /**
     * 获取用于加载资源的资源加载器。
     *
     * @return ResourceLoader，用于从不同位置加载资源文件。
     */
    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    /**
     * 根据传入的一组资源位置，逐个加载并注册 BeanDefinition。
     *
     * @param locations 资源位置的数组，可以是多个文件路径、类路径等。
     */
    @Override
    public void loadBeanDefinitions(String[] locations) {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }
}

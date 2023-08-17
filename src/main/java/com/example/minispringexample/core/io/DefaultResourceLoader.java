package com.example.minispringexample.core.io;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 默认的资源加载器实现，用于根据资源的路径获取相应的资源实例。
 *
 * @version: 1.0
 * @author: Vincent
 * @date: 2023/8/17 15:28
 */
public class DefaultResourceLoader implements ResourceLoader {
    public static final String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 根据给定的资源路径获取相应的资源实例。
     *
     * @param location 资源路径，可以是类路径下的路径、URL 或文件系统路径。
     * @return 对应的资源实例。
     */
    @Override
    public Resource getResource(String location) {
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            // 当作 ClassPath 来处理
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            try {
                // 尝试当作 URL 来处理
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException ex) {
                // 当作文件系统下的资源处理
                return new FileSystemResource(location);
            }
        }
    }
}


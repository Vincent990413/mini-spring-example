package com.example.minispringexample.core.io;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 这个类表示一个 URL 资源，用于封装对 URL 类型资源的访问。
 *
 * @author Vincent
 * @version 1.0
 * @description 提供对 URL 资源的访问方法。
 * @date 2023/8/17 15:13
 */
@AllArgsConstructor
public class UrlResource implements Resource {
    private final URL url;

    /**
     * 获取 URL 资源的输入流。通过该输入流可以读取 URL 资源的内容。
     *
     * @return 资源的输入流。
     * @throws IOException 当访问 URL 资源时发生 IO 异常时抛出。
     */
    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection conn = this.url.openConnection();
        return conn.getInputStream();
    }
}


package com.example.minispringexample.core.io;

import cn.hutool.core.bean.BeanUtil;
import lombok.AllArgsConstructor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 这个类表示一个类路径资源，用于封装对类路径中的文件资源的访问。
 *
 * @author Vincent
 * @version 1.0
 * @description 提供对类路径资源的访问方法。
 * @date 2023/8/17 15:13
 */
@AllArgsConstructor
public class ClassPathResource implements Resource {
    private final String path;

    /**
     * 获取类路径资源的输入流。通过该输入流可以读取类路径资源的内容。
     *
     * @return 资源的输入流。
     * @throws IOException 当访问类路径资源时发生 IO 异常时抛出，或者资源不存在时抛出 FileNotFoundException。
     */
    @Override
    public InputStream getInputStream() throws IOException {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(this.path);

        if (BeanUtil.isEmpty(resourceAsStream)) {
            throw new FileNotFoundException(this.path + " cannot be opened because it does not exist");
        }
        return resourceAsStream;
    }
}

package com.example.minispringexample.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源接口，用于封装不同类型资源的访问方法。
 *
 * @author Vincent
 * @version 1.0
 * @description 提供对各种类型资源的访问方法。
 * @date 2023/8/17 15:08
 */
public interface Resource {
    /**
     * 获取资源的输入流。通过该输入流可以读取资源的内容。
     *
     * @return 资源的输入流。
     * @throws IOException 当访问资源时发生IO异常时抛出。
     */
    InputStream getInputStream() throws IOException;
}


package com.example.minispringexample.core.io;

import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

/**
 * 这个类表示一个文件系统资源，用于封装对文件系统中文件的访问。
 *
 * @author Vincent
 * @version 1.0
 * @description 提供对文件系统资源的访问方法。
 * @date 2023/8/17 15:13
 */
@AllArgsConstructor
public class FileSystemResource implements Resource {

    private final String filePath;

    /**
     * 获取文件系统资源的输入流。通过该输入流可以读取文件系统资源的内容。
     *
     * @return 资源的输入流。
     * @throws IOException 当访问文件系统资源时发生 IO 异常时抛出，或者资源不存在时抛出 FileNotFoundException。
     */
    @Override
    public InputStream getInputStream() throws IOException {
        try {
            Path path = new File(this.filePath).toPath();
            return Files.newInputStream(path);
        } catch (NoSuchFileException ex) {
            throw new FileNotFoundException(ex.getMessage());
        }
    }
}


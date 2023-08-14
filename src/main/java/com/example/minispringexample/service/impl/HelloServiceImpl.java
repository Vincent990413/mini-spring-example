package com.example.minispringexample.service.impl;

import com.example.minispringexample.service.HelloService;

/**
 * @description:
 * @version: 1.0
 * @author: Vincent
 * @date: 2023/8/14 09:39
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("hello");
    }
}

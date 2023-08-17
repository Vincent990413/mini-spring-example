package com.example.minispringexample.service.impl;

import com.example.minispringexample.service.HelloService;
import lombok.*;

/**
 * @description:
 * @version: 1.0
 * @author: Vincent
 * @date: 2023/8/14 09:39
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HelloServiceImpl implements HelloService {

    // 这个是需要填充进来的属性 如果不声明无法使用反射注入
    private String info;

    private BasicServiceImpl basicService;

    @Override
    public void sayHello() {
        System.out.println("hello");
    }
}

package com.example.minispringexample.beans;

import lombok.Getter;

/**
 * @description: 这是存储 Bean 对象属性的键值对，譬如
 * <code>
 *     public class Person {
 *         private String name; // 这里的name为key 具体的值为value
 *     }
 * </code>
 * @version: 1.0
 * @author: Vincent
 * @date: 2023/8/17 10:16
 */
@Getter
public class PropertyValue {
    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }
}

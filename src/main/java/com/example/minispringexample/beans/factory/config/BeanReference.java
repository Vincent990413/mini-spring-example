package com.example.minispringexample.beans.factory.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 这个类表示一个对其他Bean的引用。它包含了引用的Bean的名称。
 *
 * @author Vincent
 * @version 1.0
 * @description 用于表示对其他Bean的引用。
 * @date 2023/8/17 11:28
 */
@AllArgsConstructor
@Getter
public class BeanReference {
    private final String beanName;
}

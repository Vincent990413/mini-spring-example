package com.example.minispringexample.beans;

import lombok.Getter;
import sun.management.snmp.AdaptorBootstrap;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: PropertyValues 类利用一个 List 列表存储 Bean 对象额外的属性，譬如：
 * <code>
 *     public class Person {
 *         private String name;
 *         private Integer age;
 *     }
 * </code>
 * @version: 1.0
 * @author: Vincent
 * @date: 2023/8/17 10:08
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue propertyValue) {
        propertyValueList.add(propertyValue);
    }

    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue propertyValue : this.propertyValueList) {
            if (propertyValue.getName().equals(propertyName)) {
                return propertyValue;
            }
        }
        return null;
    }
}

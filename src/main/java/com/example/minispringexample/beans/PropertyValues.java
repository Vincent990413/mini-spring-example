package com.example.minispringexample.beans;


import java.util.ArrayList;
import java.util.List;

/**
 * @description: PropertyValues 类利用一个 List 列表存储 Bean 对象额外的属性，譬如：
 * <code>
 * public class Person {
 * private String name;
 * private Integer age;
 * }
 * </code>
 * @version: 1.0
 * @author: Vincent
 * @date: 2023/8/17 10:08
 */
public class PropertyValues {
    // 用于存储属性值的列表
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    /**
     * 添加一个属性值到属性值列表。
     * 如果已存在同名属性，则进行覆盖。
     *
     * @param propertyValue 要添加的属性值。
     */
    public void addPropertyValue(PropertyValue propertyValue) {
        // 如果有则覆盖
        for (int i = 0; i < propertyValueList.size(); i++) {
            PropertyValue currPV = this.propertyValueList.get(i);
            if (propertyValue.getName().equals(currPV.getName())) {
                this.propertyValueList.set(i, propertyValue);
                return;
            }
        }

        // 否则添加到列表
        this.propertyValueList.add(propertyValue);
    }

    /**
     * 获取所有的属性值数组。
     *
     * @return 所有的属性值数组。
     */
    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    /**
     * 根据属性名获取对应的属性值。
     *
     * @param propertyName 要查找的属性名。
     * @return 对应属性名的属性值，如果找不到则返回 null。
     */
    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue propertyValue : this.propertyValueList) {
            if (propertyValue.getName().equals(propertyName)) {
                return propertyValue;
            }
        }
        return null;
    }
}

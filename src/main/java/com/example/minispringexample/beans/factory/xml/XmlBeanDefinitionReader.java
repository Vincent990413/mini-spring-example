package com.example.minispringexample.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.example.minispringexample.beans.PropertyValue;
import com.example.minispringexample.beans.PropertyValues;
import com.example.minispringexample.beans.factory.config.BeanDefinition;
import com.example.minispringexample.beans.factory.config.BeanReference;
import com.example.minispringexample.beans.factory.support.AbstractBeanDefinitionReader;
import com.example.minispringexample.beans.factory.support.BeanDefinitionRegistry;
import com.example.minispringexample.core.io.Resource;
import com.example.minispringexample.core.io.ResourceLoader;
import com.example.minispringexample.exceptions.impl.BasicBeansExceptionImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * @description: 这是一个 XmlBeanDefinitionReader 类，继承了 AbstractBeanDefinitionReader 抽象类，
 * 用于从 XML 配置文件中读取 BeanDefinition 并将其注册到 BeanDefinitionRegistry 中。
 * @version: 1.0
 * @author: Vincent
 * @date: 2023/8/23 15:29
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public static final String BEAN_ELEMENT = "bean";
    public static final String PROPERTY_ELEMENT = "property";
    public static final String ID_ATTRIBUTE = "id";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String CLASS_ATTRIBUTE = "class";
    public static final String VALUE_ATTRIBUTE = "value";
    public static final String REF_ATTRIBUTE = "ref";

    /**
     * 使用给定的 BeanDefinitionRegistry 构造一个 XmlBeanDefinitionReader 对象。
     *
     * @param registry BeanDefinitionRegistry 对象，用于注册 BeanDefinition。
     */
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    /**
     * 使用给定的 BeanDefinitionRegistry 和 ResourceLoader 构造一个 XmlBeanDefinitionReader 对象。
     *
     * @param registry       BeanDefinitionRegistry 对象，用于注册 BeanDefinition。
     * @param resourceLoader ResourceLoader 对象，用于加载资源。
     */
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    /**
     * 从指定的资源位置加载并注册 BeanDefinition。
     *
     * @param location 资源的位置，可以是文件路径、类路径等。
     */
    @Override
    public void loadBeanDefinitions(String location) {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    /**
     * 从给定的资源对象加载并注册 BeanDefinition。
     *
     * @param resource 要加载的资源对象，可以是文件、类路径资源等。
     */
    @Override
    public void loadBeanDefinitions(Resource resource) {
        try (InputStream inputStream = resource.getInputStream()) {
            doLoadBeanDefinition(inputStream);
        } catch (IOException e) {
            throw BasicBeansExceptionImpl.BEANS_XML_PARSE_EXCEPTION.newException(e);
        }
    }

    /**
     * 解析输入流中的 XML 配置，生成并注册相应的 BeanDefinition 对象。
     *
     * @param inputStream 包含 XML 配置的输入流。
     */
    protected void doLoadBeanDefinition(InputStream inputStream) {
        Document document = XmlUtil.readXML(inputStream);
        Element rootElement = document.getDocumentElement();
        NodeList childNodes = rootElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (childNodes.item(i) instanceof Element) {
                if (childNodes.item(i).getNodeName().equals(BEAN_ELEMENT)) {
                    // 解析 Bean 标签
                    Element bean = (Element) childNodes.item(i);
                    String id = bean.getAttribute(ID_ATTRIBUTE);
                    String name = bean.getAttribute(NAME_ATTRIBUTE);
                    String className = bean.getAttribute(CLASS_ATTRIBUTE);

                    Class<?> clazz = null;
                    try {
                        clazz = Class.forName(className);
                    } catch (ClassNotFoundException e) {
                        throw BasicBeansExceptionImpl.BEANS_CLASS_NOT_FOUND_EXCEPTION.newException(e);
                    }

                    // bean 名称：id 值优先于 name
                    String beanName = StrUtil.isNotEmpty(id) ? id : name;

                    // 如果 id 值与 name 值都为空
                    if (StrUtil.isEmpty(beanName)) {
                        beanName = StrUtil.lowerFirst(clazz.getSimpleName());
                    }

                    // 初始化 BeanDefinition 对象并最终注册这个对象
                    BeanDefinition beanDefinition = BeanDefinition.newBeanDefinitionNoPV(clazz);

                    for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                        if (bean.getChildNodes().item(j) instanceof Element) {
                            if (PROPERTY_ELEMENT.equals(bean.getChildNodes().item(j).getNodeName())) {
                                // 解析 property 标签
                                Element propertyElement = (Element) bean.getChildNodes().item(j);
                                String nameAttribute = propertyElement.getAttribute(NAME_ATTRIBUTE);
                                String valueAttribute = propertyElement.getAttribute(VALUE_ATTRIBUTE);
                                String refAttribute = propertyElement.getAttribute(REF_ATTRIBUTE);

                                // name 属性不可为空
                                if (StrUtil.isEmpty(nameAttribute)) {
                                    throw BasicBeansExceptionImpl.BEANS_XML_NAME_ATTRIBUTE_EMPTY_EXCEPTION.newException();
                                }

                                // 这里对 ref 引用类型做额外的处理
                                Object value = valueAttribute;
                                if (StrUtil.isNotEmpty(refAttribute)) {
                                    value = new BeanReference(refAttribute);
                                }
                                PropertyValue propertyValue = new PropertyValue(nameAttribute, value);

                                // 添加 BeanDefinition 的键值对属性
                                beanDefinition.getPropertyValues()
                                        .addPropertyValue(propertyValue);
                            }
                        }
                    }

                    // 不能根据 BeanName 重复注册
                    if (getRegistry().containsBeanDefinition(beanName)) {
                        throw BasicBeansExceptionImpl.BEANS_XML_BEAN_NAME_ATTRIBUTE_DUPLICATE_EXCEPTION.newException();
                    }

                    // 读取完所有的 XML 标签并整合成 BeanDefinition 后
                    // 注册这个 BeanDefinition
                    getRegistry().registerBeanDefinition(beanName, beanDefinition);
                }
            }
        }
    }
}

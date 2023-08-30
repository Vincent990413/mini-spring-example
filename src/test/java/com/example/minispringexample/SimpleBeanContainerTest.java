package com.example.minispringexample;

import com.example.minispringexample.beans.PropertyValue;
import com.example.minispringexample.beans.PropertyValues;
import com.example.minispringexample.beans.factory.config.BeanDefinition;
import com.example.minispringexample.beans.factory.config.BeanReference;
import com.example.minispringexample.beans.factory.support.DefaultListableBeanFactory;
import com.example.minispringexample.beans.factory.xml.XmlBeanDefinitionReader;
import com.example.minispringexample.processor.CustomBeanFactoryPostProcessor;
import com.example.minispringexample.processor.CustomLoggingBeanPostProcessor;
import com.example.minispringexample.processor.CustomPermissionCheckBeanPostProcessor;
import com.example.minispringexample.service.impl.BasicServiceImpl;
import com.example.minispringexample.service.impl.HelloServiceImpl;
import org.junit.Test;


public class SimpleBeanContainerTest {

    @Test
    public void testWithBasicPV() {
        // 将 HelloService Bean 对象定义（可以看做是相关信息） 注册到工厂中
        // 注意，其实我们放在工厂中的，是 BeanDefinition 而不只是 Bean 对象本身
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // Bean 需要填充的属性值集合
        PropertyValues propertyValues = new PropertyValues();
        PropertyValue propertyValue = new PropertyValue("info", new String("这是一串无用的信息"));
        propertyValues.addPropertyValue(propertyValue);

        BeanDefinition beanDefinition = BeanDefinition.newBeanDefinition(HelloServiceImpl.class, propertyValues);
        beanFactory.registerBeanDefinition("helloService", beanDefinition);

        // 在这里可以手动设置实例化 Bean 对象的策略
        // beanFactory.setInstantiationStrategy();

        // 然后从工厂中取出来
        HelloServiceImpl helloService = (HelloServiceImpl) beanFactory.getBean("helloService");
        helloService.sayHello();
    }


    @Test
    public void testWithRef() {
        // 将 HelloService Bean 对象定义（可以看做是相关信息） 注册到工厂中
        // 注意，其实我们放在工厂中的，是 BeanDefinition 而不只是 Bean 对象本身
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // -----------  BasicService 的 Bean 定义
        BeanDefinition beanDefOfBasicService = BeanDefinition.newBeanDefinitionNoPV(BasicServiceImpl.class);
        beanFactory.registerBeanDefinition("basicService", beanDefOfBasicService);


        // -----------  HelloService 的 Bean 定义
        // 注意这里填充的属性 必须与其类定义中的属性一一对应
        PropertyValues propertyValues = new PropertyValues();
        PropertyValue infoValue = new PropertyValue("info", new String("这是一串无用的信息"));

        // 这里注意 HelloServiceImpl 依赖于 BasicServiceImpl
        // 使用 BeanReference 来指定为 Bean 对象的引用
        PropertyValue basicServiceValue = new PropertyValue("basicService", new BeanReference("basicService"));
        propertyValues.addPropertyValue(infoValue);
        propertyValues.addPropertyValue(basicServiceValue);

        BeanDefinition beanDefinition = BeanDefinition.newBeanDefinition(HelloServiceImpl.class, propertyValues);
        beanFactory.registerBeanDefinition("helloService", beanDefinition);

        // 在这里可以手动设置实例化 Bean 对象的策略
        // beanFactory.setInstantiationStrategy();

        // 然后从工厂中取出来
        HelloServiceImpl helloService = (HelloServiceImpl) beanFactory.getBean("helloService");
        helloService.sayHello();
    }

    @Test
    public void contextLoads() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");



        HelloServiceImpl helloServiceImpl = (HelloServiceImpl) beanFactory.getBean("helloServiceImpl");
        helloServiceImpl.sayHello();
    }

    @Test
    public void customBeanFactoryPostProcessor() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        CustomBeanFactoryPostProcessor customBeanFactoryPostProcessor = new CustomBeanFactoryPostProcessor();
        customBeanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        CustomPermissionCheckBeanPostProcessor customPermissionCheckBeanPostProcessor = new CustomPermissionCheckBeanPostProcessor();
        CustomLoggingBeanPostProcessor customLoggingBeanPostProcessor = new CustomLoggingBeanPostProcessor();
        beanFactory.addBeanPostProcessor(customPermissionCheckBeanPostProcessor);
        // beanFactory.addBeanPostProcessor(customLoggingBeanPostProcessor);

        BasicServiceImpl basicServiceImpl = (BasicServiceImpl) beanFactory.getBean("basicServiceImpl");

    }
}

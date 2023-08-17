package com.example.minispringexample;

import com.example.minispringexample.beans.factory.config.BeanDefinition;
import com.example.minispringexample.beans.factory.support.DefaultListableBeanFactory;
import com.example.minispringexample.service.impl.HelloServiceImpl;
import org.junit.Test;


public class SimpleBeanContainerTest {

    @Test
    public void contextLoads() {
        // 将 HelloService Bean 对象定义（可以看做是相关信息） 注册到工厂中
        // 注意，其实我们放在工厂中的，是 BeanDefinition 而不只是 Bean 对象本身
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = BeanDefinition.newBeanDefinitionNoPV(HelloServiceImpl.class);

//                new BeanDefinition(HelloServiceImpl.class, null);

        beanFactory.registerBeanDefinition("helloService", beanDefinition);

        // 在这里可以手动设置实例化 Bean 对象的策略
        // beanFactory.setInstantiationStrategy();
        // 然后从工厂中取出来
        HelloServiceImpl helloService = (HelloServiceImpl) beanFactory.getBean("helloService");
        helloService.sayHello();
    }

}

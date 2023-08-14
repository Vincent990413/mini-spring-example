package com.example.minispringexample;

import com.example.minispringexample.beans.factory.BeanFactory;
import com.example.minispringexample.beans.factory.config.BeanDefinition;
import com.example.minispringexample.beans.factory.support.DefaultListableBeanFactory;
import com.example.minispringexample.service.HelloService;
import com.example.minispringexample.service.impl.HelloServiceImpl;
import org.junit.Test;


import static org.assertj.core.api.Assertions.assertThat;


public class SimpleBeanContainerTest {

    @Test
    public void contextLoads() {
        // 将 HelloService Bean 对象定义（可以看做是相关信息） 注册到工厂中
        // 注意，其实我们放在工厂中的，是 BeanDefinition 而不只是 Bean 对象本身
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(HelloServiceImpl.class);
        beanFactory.registerBeanDefinition("helloService", beanDefinition);

        // 然后从工厂中取出来
        HelloServiceImpl helloService = (HelloServiceImpl) beanFactory.getBean("helloService");
        helloService.sayHello();
    }

}

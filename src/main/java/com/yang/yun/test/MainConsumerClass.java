package com.yang.yun.test;

import com.yang.yun.service.OrderService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @Author: yhy
 * @Date: 2018/8/30 10:12
 * @Version 1.0
 */
public class MainConsumerClass {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("consumer.xml");
        OrderService bean = ioc.getBean(OrderService.class);
        bean.init("5");
        System.out.println("-----调用完成----");
        System.in.read();
    }
}

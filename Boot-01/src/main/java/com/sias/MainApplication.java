package com.sias;

import com.sias.config.MyConfig;
import com.sias.demo.Pet;
import com.sias.demo.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Edgar
 * @create 2022-02-20 13:01
 */

/*
 * 一.SpringBootApplication是告诉Spring这个是一个SpringBoot应用
 *    是一个主程序入口
 *    是可以理解成，这是一个Tomcat服务器，是可以知己在浏览器上进行访问的，直接去运行，main方法，就是执行服务器*/

@SpringBootApplication//这个注解是默认包含下面的三个注解，这个一个注解固定扫描的包的范围不可以改变
//@SpringBootConfiguration
//@EnableAutoConfiguration
//@ComponentScan，扫描包的范围可以改变
public class MainApplication {


    public static void main(String[] args) {
        //1.这是一个主程序入口，调用方法，第一个参数的意思是让这个类跑起来，在加上这个Main方法
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

        //2.查看容器里面的组件（组件就是Bean容器中管理的对象和属性的值）
        String[] names = run.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }

        //3.从容器中获取组件
        Pet tom1 = run.getBean("tom", Pet.class);
        Pet tom2 = run.getBean("tom", Pet.class);
        System.out.println("组件：" + (tom1 == tom2));

        String[] type = run.getBeanNamesForType(User.class);
        for (String s : type) {
            System.out.println(s);
        }

        /*
         * 4.得到配置类，本身也是组件，配置类，里面也是有方法是可以去调用的
         * 01.Myconfig本身是一个代理对象（不写proxyBeanMethods = true注解基础上，本身也是一个ture）
         *
         * 02.(proxyBeanMethods = true)这个注解写的话，会检查，
         *    不写的话，默认的也是true，SpringBoot也会检查
         *    代理对象中是否含有组件user01，没有的话，自动创建
         *    保持组件单实例（就是在加上true 的话，有的话，调用组件，没有的话，去创建一个）
         *
         * 03.(proxyBeanMethods = false)
         *    false的话，这个配置类就不是一个代理对象，多次的调用就不相等
         */
        MyConfig bean = run.getBean(MyConfig.class);
        System.out.println(bean);
        User user = bean.user01();
        User user1 = bean.user01();
        System.out.println(user == user1);
        System.out.println("我是分割线======================");

        User user01 = run.getBean("user01", User.class);
        Pet tom = run.getBean("tom", Pet.class);
        System.out.println("用户的宠物：" + (user01.getPet() == tom));


        //4.查看组件是否存在
        boolean shl = run.containsBean("shl");
        System.out.println("查看容器是是否有这个组件：" + shl);

        boolean animal = run.containsBean("animal");
        System.out.println("查看容器是是否有animal组件：" + animal);
    }


}

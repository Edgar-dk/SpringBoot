package com.sias.controller;

import com.sias.demo.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Edgar
 * @create 2022-02-20 13:06
 */
/*
 *
 * 跳转页面的程序*/

/*
 * 一：@ResponseBody把返回的字符串返回给浏览器，例如其中的一个方法的字符串："hello,SpringBoot2";因为你把注解加在了类上
 *    @Controller//Bean管理去创建这个对象
 * 二：这个类是一个把数据传输到浏览器上的
 *
 * */

@RestController//功能是和上面两个一样
public class HelloController {


    //1.首页面的测试
    @RequestMapping("/Hello")
    public String Hello() {
        return "hello,SpringBoot2";
    }

    //2.application.properties文件中加载数据
    @Autowired//注入Car这个类,里面的数据和属性以及方法都是可以使用的
    private Car car;

    @RequestMapping("/car")
    public Car car() {
        return car;//直接返回的是toString方法
    }
}


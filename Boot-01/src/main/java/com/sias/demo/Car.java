package com.sias.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Edgar
 * @create 2022-02-24 17:13
 */

/*
 * 一：Component注解，是把这个类放在了容器中了，是让SpringBoot来管理
 *    去管理，这个类去创建对象和为属性赋值
 *二：ConfigurationProperties(prefix = "mycar")，是放在容器中，才可以使用这个注解
 *    是把application.properties文件中的数据加载进来，前面是名字，后面的是值
 *    注意：这个是可以理解成是一个JavaBean的数据的，就是用了一个注解的方式和application.properties这个文件进行了绑定
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Component，使用第二种方式，需要把前面的注解注释掉，方可使用@EnableConfigurationProperties(Car.class)
@ConfigurationProperties(prefix = "mycar")
public class Car {
    private String brand;
    private Integer prices;
}

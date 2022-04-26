package com.sias.config;

import com.sias.demo.Animal;
import com.sias.demo.Car;
import com.sias.demo.Pet;
import com.sias.demo.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Edgar
 * @create 2022-02-20 17:51
 */

/*
 * 一：配置的组件是单实例的，就是定义一个，是可以获取一个的（不写注解的话，本身也是ture）
 * 二：配置类，本身也是一个组件，是用来管理类的创建和属性的赋值
 * 三：proxyBeanMethods：(不写这个注解的话，默认是true)
 *     Full（proxyBeanMethods = true），Myconfig是代理对象，会按照配置类中的组件来调用（单实例）
 *     lite (proxyBeanMethods = false)，Myconfig不是一个代理对象，在容器中配置类也不会保持代理对象
 *          每次的调用都会产生一个新的对象
 *     目的：组件依赖，就类似的User依赖的Pet组件（需要里面的信息）
 * 四：@ImportResource("classpath:bean1.xml")//加载配置文件导入Spring的配置文件，使其生效
 * 五：Import({User.class})
 *    可以导入组件，下面的都是一些自定义的组件, 导入之后，就可以，使用这个组件的内容，名字是全类名的方式
 *    这个是直接导入类就可以了，什么的都不需要做，
 * 六：@EnableConfigurationProperties(Car.class)
 *    是把这个类导入配置类中，让SpringBoot来管理他
 * */

@Import({User.class})
@Configuration//告诉SpringBoot这个类是一个配置类 ==配置文件（Spring中的Bean文件），(proxyBeanMethods = true)
@ImportResource("classpath:bean1.xml")//加载配置文件
//@ConditionalOnMissingBean(name = "shl")//在没有shl的时候，其他的才装配在这个配置类上
@EnableConfigurationProperties(Car.class)
public class MyConfig {

    //1.是可以在方法上加Bean的，用Bean的方式去管理对象（就是去创建对象，和为对象中的属性赋值）
    @Bean("tom")//是自定义的名字，不写的话，是以方法的名字作为id
    public Pet tomcatPet() {
        return new Pet("tomcat1111111");
    }


    //2.解释：在方法上加注解的话，是给这个方法所对应的对象加上具体的值，方法的名字作为id，放回的值，就是组件的类型
    //就是User这个类型，具体的值，是在放回的地方去写的值，有一点需要注意的是，作为的组件就是在Bean里面去创建的对象和管理的类的属性
    /*
     * 01.在加上proxyBeanMethods这个注解，是true的时候，在主方法去调用的时候，
     *    是找到这个配置类中组件注册的方法，是按照注册好去去调用的，就是按照new
     *    好的去调用，ture的话，虽然是按照组件，注册好的方法（中含有注册好的对象）
     *    去调用那个对象，会一串的执行组件中的穿起来的对象。这样执行的速度，没有false快
     * 02.是false的话，去找到这个方法，之后，再去创建一个对象，不按照
     *    new好的对象调用，false的话，调用的速度是快的，
     * */
    @Bean
    public User user01() {
        User zhangsan = new User("zhangsan", 18);
        zhangsan.setPet(tomcatPet());
        return zhangsan;
    }

    //3.查看是否有组件，这个需要看笔记的分析
//    @ConditionalOnBean(name = "to")
    @Bean
    public Animal animal() {
        return null;
    }
}

package com.example.demo;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/* 各种注解
  1、@controller 控制器（注入服务）
  2、@service 服务（注入dao）
  3、@repository dao（实现dao访问）
  4、@component （把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class=""/>）*/
@SpringBootApplication
@EnableTransactionManagement   //开启事务管理
@MapperScan("com.example.demo.dao")//与dao层的@Mapper二选一写上即可(主要作用是扫包)
@EnableCaching  //开启缓存支持
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean(destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }
}
package com.example.demo.config;

import org.omg.CORBA.Any;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/****************************************************
 * 创建人：  @author     
 * 创建时间: /
 * 项目名称: 
 * 文件名称: 
 * 文件描述: @Description
 * 公司名称: 深圳市赢和信息技术有限公司
 *
 * All rights Reserved, Designed By 深圳市赢和信息技术有限公司
 * @Copyright:2016-
 *
 ********************************************************/
//从Spring3.0，@Configuration用于定义配置类，可替换xml配置文件，被注解的类内部包含有一个或多个被@Bean注解的方法，
// 这些方法将会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext类进行扫描，
// 并用于构建bean定义，初始化Spring容器。
@Configuration
@EnableSwagger2  //开启swagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("demo-swagger")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                .build();

        return docket;
    }

    //配置Swagger的信息：apiInfo
    private ApiInfo apiInfo(){
        //作者信息
        Contact DEFAULT_CONTACT = new Contact("刘洋", "https://blog.csdn.net/", "aowu_ly@163.com");
        return new ApiInfo("Api Documentation", "Api Documentation", "1.0", "urn:tos", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());

    }
}

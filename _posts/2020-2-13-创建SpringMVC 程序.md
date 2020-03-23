---
layout: post
author: LIU,HONGYANG
tags: [JavaEE]
---





本节由本人学习书籍《SpringMVC+Mybatis企业应用实战》所写



- Domain Object(领域对象) 层。此层由一系列的POJO, 普通的、传统的Java对象组成，这些对象是该系统的Domain Object(领域对象)，往往包含了各自所需实现的业务逻辑方法。

- DAO(Data Access Object, 数据访问对象)层。此层由一些列的DAO组件组成，这些DAO实现了对数据库的创建、查询、更新和删除(CRUD)等原子操作
- Service(业务逻辑)层。此层由一些列的业务逻辑对象组成，这些业务逻辑对象实现了系统所需要的业务逻辑方法。
- Controller(控制器)层。此层由一些列控制器组成，这些控制器用于拦截用户请求，并调用业务逻辑组件的业务逻辑方法。
- View(表现)层



![image-20200212201636454](https://tva1.sinaimg.cn/large/0082zybpgy1gbtvrj4da3j30qs0isn0b.jpg) 



Mybatis作为持久层框架，其主要思想是将程序中的大量SQL语句剥离出来，配置在配置文件中，实现SQL的灵活配置。

>  SpringMVC核心组件：

- DispatcherServlet:前置控制器，是整个流程控制的核心，控制其它组件的执行，进行统一调度，降低组件之间的耦合性，相当于总指挥。

- Handler处理器，完成具体的业务逻辑，相当于Servlet或Action.
- HandlerMapping: DispatcherServlet接收请求后，通过HandlerMapping将请求映射到不同的Handler.
- HandlerInterceptor:处理器拦截器，是一个接口，如果需要完成一些拦截操作，可以实现该接口。
- HandlerExecutionChian:处理器执行链，包括两部分内容Handler和HandlerInterceptor(系统会有一个默认的HandlerInterceptor, 如果需要额外的设置拦截，可以添加拦截器)
- HandlerAdapter：处理适配器，Handler执行业务方法之前，需要进行一系列的操作，包括表单数据的验证、数据的转换、将表单数据封装到JavaBean等。这些操作都是由HandlerAdapter完成，开发者只需将精力集中在业务逻辑上，DispatcherServlet通过HandlerAdapter执行不同的Adapter.
- ModelAndView:装载了模型数据和视图信息，作为Handler的处理结果，返回给DispatcherServlet.
- ViewResolver:视图解析器，DispatcherServlet通过它将逻辑视图解析为物理视图，最终将渲染结果给客户端。

> SpringMVC工作流程



![image-20200213114722772](https://tva1.sinaimg.cn/large/0082zybpgy1gbumnz1a31j312q0k6q5i.jpg)

![image-20200213114855986](https://tva1.sinaimg.cn/large/0082zybpgy1gbumpklb6dj31au0ku497.jpg)



> 使用



- 创建Maven工程，pom.xml

```xml
 <dependencies>
      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.0.11.RELEASE</version>
      </dependency>
  </dependencies>
```





- 在web.xml中配置dispatcherServlet

```xml
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
  <display-name>Archetype Created Web Application</display-name>

  <servlet>
    <servlet-name>dispatcherServlet</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:springmvc.xml</param-value>
    </init-param>
  </servlet>

  <servlet-mapping>
    <servlet-name>dispatcherServlet</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>

</web-app>

```





- 创建Handler

```java
package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloHandler {

    @RequestMapping("index")
    public String index(){
        System.out.println("执行了index...");
        return "index";
    }
    
}
```




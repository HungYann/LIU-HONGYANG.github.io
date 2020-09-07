---
layout: post
author: LIU,HONGYANG
tags: [JavaEE]
---



>  SpringMVC应用开发步骤

1. 导入需要的Maven文件

   在使用Maven开发Spring项目的过程中，第一步是使用maven依赖导入相关的jar包。如下所示，导入基础的springframework包

   ```xml
   <dependencies>
         <dependency>
           <groupId>org.springframework</groupId>
           <artifactId>spring-webmvc</artifactId>
           <version>5.0.11.RELEASE</version>
         </dependency>
     </dependencies>
   ```



2.配置web.xml文件

在web.xml文件中定义前端控制器DispatcherServlet来拦截用户请求



```xml
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
```



3.`springmvc.xml`定义处理用户请求的Handle类，可以实现Controller接口或使用@Controller注解。

在SpringMVC框架中，控制器实际上由两部分组成，即拦截所有用户请求和处理请求的通用代码都由前端控制器DispatcherServlet完成，而实际上业务控制（如调用后台业务逻辑代码，返回处理结果等）则由Controller控制。



4.配置Handle。配置哪个请求对应哪个Controller进行处理，从而让前端控制器根据该配置来创建合适的Controller实例，并调用该Controller的业务控制方法。

```java
@Controller
public class HelloController{
	@RequestMapping("/hello")
		public ModelAndView hello(){
		...	
		}
}
```

上面定义了请求，如果请求是`hello`,则采用约定的方式来规定用户请求地址和Handle之间的对应关系。



5.编写视图资源

Handle处理请求结束之后，通常会返回一个ModelAndView对象，对象包含Model和View,这个视图名就代表需要显示的无力视图资源。



>  SpringMVC运行原理



![20160702175028069](https://tva1.sinaimg.cn/large/0082zybpgy1gbur80tiv0j30nw0evq4p.jpg)

- 用户发送请求，请求被前端控制器DispatcherServlet拦截。
- DispatcherServlet对请求的URL进行解析，解析成URI。然后根据URI调用HandlerMapping获取Handler和HandlerIntercepter对象，并封装成HandlerExecutorChain返回给DispatcherServlet.
- DispatcherServlet根据获得的Handler，选择一个合适的HandlerAdapter执行Handler。HandlerAdapter符合单一职责原则和适配器模式。
- 在执行Handler这一步，Spring会做一些比如，消息转换，数据转换，数据格式化以及数据验证操作。 

- Handler执行完成后，向DispatcherServlet返回ModelAndView对象，ModelAndView对象包含视图名或视图名和模型。
- 根据返回的ModelAndView对象，选择一个合适的ViewResolver（视图解析器）返回给DispatcherServlet.
- ViewResolver结合Model和View来渲染视图。




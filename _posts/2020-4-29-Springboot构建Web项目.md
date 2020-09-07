---
layout: post
author: LIU,HONGYANG
tags: [Springboot]
---



## 使用Springboot构建Web项目



> 使用**Spring Initializer** 创建项目



- 注意创建过程中，项目的具体存储路径
- 若`main`文件夹下面没有指明源码路径，自己一定要明确指明



![image-20200429151042560](https://tva1.sinaimg.cn/large/007S8ZIlgy1geanmz99a9j30u00we77s.jpg)



> pom.xml文件配置



在springboot中， `spring-boot-starter`文件自动帮助引用与springboot相关的配置文件

其中典型的配置文件如下图：



```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.2.6.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.hongyang</groupId>
    <artifactId>demo</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>demo</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>

        <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.8</version>
            <scope>provided</scope>
        </dependency>


        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>


        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>


    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>

```



- `spring-boot-starter-parent`

- `lombok`  :pojo的一个工具集

-  `thymeleaf`  ：前端模版

- `test`  :测试模版

- `web `：创建web项目



> 创建目录结构



![image-20200429151839285](https://tva1.sinaimg.cn/large/007S8ZIlgy1geanv73m7pj30ui0ngjte.jpg)



1.首先注意的是pojo层，该层和数据库匹配，因此改写改层来适配数据库

```java
package com.hongyang.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    private Integer id;
    private String department;
}

```

其中

@Data

@AllArgsConstructor

@NoArgsConstructor

注解自动构造getter,setter方法





2.然后是dao层，连接数据库



由于为了简化代码，并未添加数据库的相关访问方式，一般会用Mybatis

```java
package com.hongyang.demo.dao;

import com.hongyang.demo.pojo.Department;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DepartmentDao {
    public static Map<Integer, Department> departments = null;

    static {
        departments = new HashMap<Integer,Department>();

        departments.put(1,new Department(1,"数据部1"));
        departments.put(2,new Department(2,"数据部2"));
        departments.put(3,new Department(3,"数据部3"));
        departments.put(4,new Department(4,"数据部4"));
        departments.put(5,new Department(5,"数据部5"));
        departments.put(6,new Department(6,"数据部6"));

    }

    //或者所有部门信息
    public Collection<Department> getDepartments(){
        return departments.values();
    }

    //获取部门信息
    public Department getDepartmentById(Integer id){
        return departments.get(id);
    }

}


```





3.第三层是service层

用来处理业务逻辑，但是并未写



4.controller层

用来接受，转发消息

注意Model使用来存储设置对象的

```java
package com.hongyang.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping("/user/login")
    public String login(@RequestParam String username,
                      @RequestParam String password,
                      Model model,HttpSession session
    )
    {
            if(!StringUtils.isEmpty(username)&&"123".equals(password))
            {
                session.setAttribute("loginUser",username);
                return "dashboard";
            }else{
                model.addAttribute("msg","wrong username or password");
                return "index";
            }
    }
}

```



5.config层

配置相关的信息，比如拦截器或者权限验证



> 最终效果



![Screenshot 2020-04-29 at 13.19.37](https://tva1.sinaimg.cn/large/007S8ZIlgy1geao5rhld1j316n0u0jw5.jpg)



> 下一步改进方向



1.添加elasticSearch

2.使用layui模版，会参考B站上的博客项目

3.继续学习Springboot的使用方法


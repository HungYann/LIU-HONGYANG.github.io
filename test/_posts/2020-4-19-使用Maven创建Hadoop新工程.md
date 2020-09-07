---
layout: post
author: LIU,HONGYANG
tags: [Hadoop]
---



本文讲述使用Maven创建Hadoop新工程的方法，



目录结构如下：



![Screenshot 2020-04-19 at 22.27.07](https://tva1.sinaimg.cn/large/007S8ZIlgy1gdzggyf1dqj30py0mmmz6.jpg)



第一步：选择Maven工程，选择模版webapp

![image-20200419224229823](https://tva1.sinaimg.cn/large/007S8ZIlgy1gdzghxcc1uj31ry0u0tix.jpg)



第二步：创建成功后，



手动创建一个模仿整体的目录结构，模仿javaweb01:



![image-20200419224334688](https://tva1.sinaimg.cn/large/007S8ZIlgy1gdzgj23q1hj30hk0kc0u6.jpg)





然后在`pom.xml`中添加依赖:



```xml
 <dependencies>

        <dependency>
            <groupId>org.apache.hadoop</groupId>
            <artifactId>hadoop-client</artifactId>
            <version>2.7.7</version>
        </dependency>


        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
            <version>2.8.2</version>
        </dependency>


        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.1.0</version>
            <scope>provided</scope>
        </dependency>

        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.2</version>
            <scope>provided</scope>
        </dependency>

        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
            <scope>runtime</scope>
        </dependency>

    </dependencies>

```



在log4j.resources资源目录下，添加如下代码：



```
log4j.rootLogger=INFO,stdout
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d %p [%c] - %m%n
log4j.appender.logfile=org.apache.log4j.FileAppender
log4j.appender.logfile.File=target/spring.log
log4j.appender.logfile.layout=org.apache.log4j.PatternLayout
log4j.appender.logfile.layout.ConversionPattern=%d %p [%c] - %m%n
```

